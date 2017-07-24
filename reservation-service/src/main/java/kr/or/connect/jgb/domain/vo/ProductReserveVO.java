package kr.or.connect.jgb.domain.vo;

import java.util.List;

import kr.or.connect.jgb.domain.ProductPrice;

public class ProductReserveVO {
	ProductDetailVO productDetail;
	List<ProductPrice> productPrices;
	
	public ProductDetailVO getProductDetail() {
		return productDetail;
	}
	public void setProductDetailVO(ProductDetailVO productDetail) {
		this.productDetail = productDetail;
	}
	public List<ProductPrice> getProductPrices() {
		return productPrices;
	}
	public void setProductPrice(List<ProductPrice> productPrices) {
		this.productPrices = productPrices;
	}
	
	
}
