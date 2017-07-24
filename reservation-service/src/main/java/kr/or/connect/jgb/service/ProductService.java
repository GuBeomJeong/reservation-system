package kr.or.connect.jgb.service;

import java.util.List;


import kr.or.connect.jgb.domain.Product;
import kr.or.connect.jgb.domain.ProductImage;
import kr.or.connect.jgb.domain.vo.ProductDetailVO;
import kr.or.connect.jgb.domain.vo.ProductMainVO;
import kr.or.connect.jgb.domain.vo.ProductReserveVO;

public interface ProductService {
	public Product get(int id);
	public List<ProductMainVO> getAllByCategory(int id,int lastProductId);
	public List<ProductMainVO> getAll(int lastProductId);
    public Product addProduct(Product product);
    public ProductDetailVO getDetail(int productId);
    public int delete(int id);
    public int update(Product product);
    public ProductReserveVO getReserve(int productId);
}
