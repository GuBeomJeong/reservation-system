package kr.or.connect.jgb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.jgb.dao.FileDao;
import kr.or.connect.jgb.dao.ProductDao;
import kr.or.connect.jgb.domain.Files;
import kr.or.connect.jgb.domain.ProductImage;
import kr.or.connect.jgb.service.FileService;


@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	FileDao fileDao;
	
	@Autowired
	ProductDao productDao;

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

	@Override
	@Transactional(readOnly = false)
	public Files addFile(Files file,int productId) {
		// TODO Auto-generated method stub
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
