package kr.or.connect.jgb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.jgb.dao.CommentDao;
import kr.or.connect.jgb.dao.FileDao;
import kr.or.connect.jgb.dao.ProductDao;
import kr.or.connect.jgb.domain.Product;
import kr.or.connect.jgb.domain.ProductImage;
import kr.or.connect.jgb.domain.dto.CommentCountAvgDTO;
import kr.or.connect.jgb.domain.vo.ProductDetailVO;
import kr.or.connect.jgb.domain.vo.ProductMainVO;
import kr.or.connect.jgb.domain.vo.ProductReserveVO;
import kr.or.connect.jgb.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;
	private FileDao fileDao;
	private CommentDao commentDao;
	
	@Autowired
	public ProductServiceImpl(ProductDao productDao, FileDao fileDao, CommentDao commentDao) {
	    this.productDao = productDao;
	    this.fileDao = fileDao;
	    this.commentDao = commentDao;
	}

	@Override
	public Product get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductMainVO> getAllByCategory(int categoryId,int lastProductId) {
		// TODO Auto-generated method stub
		return productDao.selectAllByCategory(categoryId,lastProductId);
	}

	@Override
	public List<ProductMainVO> getAll(int lastProductId) {
		// TODO Auto-generated method stub
		return productDao.selectAll(lastProductId);
	}

	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional(readOnly = true)
	public ProductDetailVO getDetail(int productId) {
		ProductDetailVO detailVO = productDao.selectDetailById(productId);
		detailVO.setfilesId(fileDao.selectByProduct(productId));
		CommentCountAvgDTO CCA = commentDao.selectConuntAverageByProduct(productId);
		
		detailVO.setCommentAverage(CCA.getAverage());
		detailVO.setCommentCount(CCA.getCount());
		
		return detailVO;
	}
	
	@Override
	@Transactional(readOnly = true)
	public ProductReserveVO getReserve(int productId) {
		ProductDetailVO detailVO = productDao.selectDetailById(productId);
		detailVO.setfilesId(fileDao.selectByProduct(productId));
		
		ProductReserveVO reserveVO = new ProductReserveVO();
		reserveVO.setProductDetailVO(detailVO);
		reserveVO.setProductPrice(productDao.selectPriceByProductId(productId));
		
		return reserveVO;
		
	}
	
	
	
	
}
