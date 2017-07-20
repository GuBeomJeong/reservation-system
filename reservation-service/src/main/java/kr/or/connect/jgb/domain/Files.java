package kr.or.connect.jgb.domain;


public class Files {
	private int id;
	private int userId;
	private String fileName;
	private String saveFileName;
	private int fileLength;
	private String contentType;
	private int deleteFlag;
	private String createDate;
	private String endDate;
	
	public Files() {
		
	}
	
	public Files(String fileName,String saveFileName,int fileLength,String contentType){
		this.userId = 1;
		this.fileName = fileName;
		this.saveFileName = saveFileName;
		this.fileLength = fileLength;
		this.contentType = contentType;
	
//		long time = System.currentTimeMillis(); 
//
//		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd");
//
//		String date = dayTime.format(new Date(time));
		
		this.createDate = "2017-7-14";
		this.endDate = "2017-7-14";
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public int getFileLength() {
		return fileLength;
	}
	public void setFileLength(int fileLength) {
		this.fileLength = fileLength;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
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
