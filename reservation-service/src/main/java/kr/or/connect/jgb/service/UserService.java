package kr.or.connect.jgb.service;

import kr.or.connect.jgb.domain.Users;
import kr.or.connect.jgb.domain.dto.NaverLoginUserInfo;

public interface UserService {
	public boolean isRegistration(String email);
	public int addUser(NaverLoginUserInfo userInfo);
}
