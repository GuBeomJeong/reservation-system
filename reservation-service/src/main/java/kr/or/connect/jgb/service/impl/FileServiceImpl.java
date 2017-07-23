package kr.or.connect.jgb.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.jgb.dao.FileDao;
import kr.or.connect.jgb.dao.ProductDao;
import kr.or.connect.jgb.domain.Files;
import kr.or.connect.jgb.domain.ProductImage;
import kr.or.connect.jgb.service.FileService;


@Service
public class FileServiceImpl implements FileService {
	
	
	private FileDao fileDao;
	private ProductDao productDao;
	
    private String baseDir = "c:" + File.separator + "files" + File.separator; // c:\temp 디렉토리를 미리 만들어둔다.

	
	@Autowired
	FileServiceImpl(FileDao fileDao,ProductDao productDao){
		this.fileDao = fileDao;
		this.productDao = productDao;
	}

	@Override
	public Files get(int id) {
		// TODO Auto-generated method stub
		return fileDao.selectById(id);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Files files) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void inputFile(String title,MultipartFile file,int productId) {
		if(file != null){

            // windows 사용자라면 "c:\temp\년도\월\일" 형태의 문자열을 구한다.
            String formattedDate = baseDir + new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd").format(new Date());
            File f = new File(formattedDate);
            if(!f.exists()){ // 파일이 존재하지 않는다면
                f.mkdirs(); // 해당 디렉토리를 만든다. 하위폴더까지 한꺼번에 만든다.
            }
  
            String contentType = file.getContentType();
            String name = file.getName();
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();

            String uuid = UUID.randomUUID().toString(); // 중복될 일이 거의 없다.
            String saveFileName = formattedDate + File.separator + uuid; // 실제 저장되는 파일의 절대 경로

            // 아래에서 출력되는 결과는 모두 database에 저장되야 한다.
            // pk 값은 자동으로 생성되도록 한다.
            System.out.println("title :" + title);
            System.out.println("contentType :" + contentType);
            System.out.println("name :" + name);
            System.out.println("originalFilename : " + originalFilename);
            System.out.println("size : " + size);
            System.out.println("saveFileName : " + saveFileName);

            // 실제 파일을 저장함.
            // try-with-resource 구문. close()를 할 필요가 없다. java 7 이상에서 가능
            try(
                    InputStream in = file.getInputStream();
                    FileOutputStream fos = new FileOutputStream(saveFileName)){
                int readCount = 0;
                byte[] buffer = new byte[512];
                while((readCount = in.read(buffer)) != -1){
                    fos.write(buffer,0,readCount);
                }
                Files newFile = new Files(title,saveFileName,(int)size,contentType);
                addFile(newFile,productId);
                
                
            }catch(Exception ex){
                ex.printStackTrace();
            }
             // for
        } // if
	}

	@Override
	@Transactional(readOnly = false)
	public Files addFile(Files file,int productId) {
		int fileId = fileDao.insert(file);
        file.setId(fileId);
        
        ProductImage productImage = new ProductImage(productId,fileId);
        
        productDao.insertImage(productImage);
        
        return file;
        
	}

	@Override
	public List<Integer> getByProduct(int productId) {
		
		return fileDao.selectByProduct(productId);
	}
	
}
