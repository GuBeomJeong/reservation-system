package kr.or.connect.jgb.service;

import kr.or.connect.jgb.domain.Users;
import kr.or.connect.jgb.domain.dto.NaverLoginUserInfo;

public interface UserService {
	public Users get(int userId);
	public int isRegistered(String email);
	public Users addNaverUser(NaverLoginUserInfo userInfo);
	
}
