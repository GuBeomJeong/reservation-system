package kr.or.connect.jgb.domain.dto;

public class NaverLoginUserResult {
	private String resultCode;
    private String message;
    private NaverLoginUserInfo response;
    
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public NaverLoginUserInfo getResponse() {
		return response;
	}
	public void setResponse(NaverLoginUserInfo response) {
		this.response = response;
	}
    
    
}
