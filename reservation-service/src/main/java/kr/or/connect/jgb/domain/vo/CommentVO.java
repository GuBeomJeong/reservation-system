package kr.or.connect.jgb.domain.vo;


import java.util.List;

public class CommentVO {
	int id;
	double score;
	String userName;
	String comment;
	String createDate;
	String endDate;
	List<Integer> filesId;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Integer> getFilesId() {
		return filesId;
	}
	public void setFilesId(List<Integer> filesId) {
		this.filesId = filesId;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	

}
