package kr.or.connect.jgb.domain.vo;

import java.util.List;

public class ProductDetailVO {
	int id;
	String name;
	String description;
	String placeLot;
	String placeStreet;
	String tel;
	String email;
	String homepage;
	String event;
	String content;
	String salesFlag;
	String salesStart;
	String salesEnd;
	List<Integer> filesId;
	int commentCount;
	double commentAverage;
		
	public String getSalesFlag() {
		return salesFlag;
	}
	public void setSalesFlag(String salesFlag) {
		this.salesFlag = salesFlag;
	}
	public String getSalesStart() {
		return salesStart;
	}
	public void setSalesStart(String salesStart) {
		this.salesStart = salesStart;
	}
	public String getSalesEnd() {
		return salesEnd;
	}
	public void setSalesEnd(String salesEnd) {
		this.salesEnd = salesEnd;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public double getCommentAverage() {
		return commentAverage;
	}
	public void setCommentAverage(double commentAverage) {
		this.commentAverage = commentAverage;
	}
	public String getPlaceLot() {
		return placeLot;
	}
	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}
	public String getPlaceStreet() {
		return placeStreet;
	}
	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public void setFilesId(List<Integer> filesId) {
		this.filesId = filesId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public List<Integer> getFilesId() {
		return filesId;
	}
	public void setfilesId(List<Integer> filesId) {
		this.filesId = filesId;
	}
	

}
