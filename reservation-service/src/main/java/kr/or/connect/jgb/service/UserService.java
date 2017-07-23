package kr.or.connect.jgb.service;

import kr.or.connect.jgb.domain.Users;
import kr.or.connect.jgb.domain.dto.NaverLoginUserInfo;

public interface UserService {
	public boolean isRegistered(String email);
	public Users addNaverUser(NaverLoginUserInfo userInfo);
}
