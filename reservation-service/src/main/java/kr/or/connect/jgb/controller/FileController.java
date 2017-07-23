package kr.or.connect.jgb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.jgb.domain.Files;
import kr.or.connect.jgb.service.CategoryService;
import kr.or.connect.jgb.service.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@Controller
@RequestMapping("/files")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
    private String baseDir = "c:" + File.separator + "files" + File.separator; // c:\temp 디렉토리를 미리 만들어둔다.

    @GetMapping
    public String fileform(){
        return "files";
    }

    @PostMapping
    public String create(
            @RequestParam("title") String title,
            @RequestParam("file") MultipartFile file,
            @RequestParam("productId") int productId){

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
                fileService.addFile(newFile,productId);
                
                
            }catch(Exception ex){
                ex.printStackTrace();
            }
             // for
        } // if

        return "redirect:/files";
    }

    @GetMapping(path="/{id}")
    public void downloadReservationUserCommentImage(
            @PathVariable(name="id") int id,
            HttpServletResponse response
    ){ 	
    	Files file = fileService.get(id);
    	
        String originalFilename = file.getFileName();
        String contentType = file.getContentType();
        int fileSize = file.getFileLength();
        
        String saveFileName = file.getSaveFileName();

        response.setHeader("Content-Disposition", "inline; filename=\"" + originalFilename + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Length", ""+ fileSize);
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        java.io.File readFile = new java.io.File(saveFileName);
        if(!readFile.exists()){ // 파일이 존재하지 않다면
            throw new RuntimeException("file not found");
        }

        FileInputStream fis = null;
        try{
            fis = new FileInputStream(readFile);
            FileCopyUtils.copy(fis, response.getOutputStream()); // 파일을 저장할때도 사용할 수 있다.
            response.getOutputStream().flush();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }finally {
            try {
                fis.close();
            }catch(Exception ex){
                // 아무것도 하지 않음 (필요한 로그를 남기는 정도의 작업만 함.)
            }
        }

    }

}