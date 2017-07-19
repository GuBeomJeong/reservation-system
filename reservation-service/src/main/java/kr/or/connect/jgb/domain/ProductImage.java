package kr.or.connect.jgb.domain;

public class ProductImage {
	int id;
	int productId;
	int fileId;
	int type;
	
	public ProductImage(int productId,int fileId) {
		this.productId = productId;
		this.fileId = fileId;
		this.type = 0;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
