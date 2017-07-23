package kr.or.connect.jgb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.jgb.dao.UserDao;
import kr.or.connect.jgb.domain.Users;
import kr.or.connect.jgb.domain.dto.NaverLoginUserInfo;
import kr.or.connect.jgb.service.UserService;



@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
	    this.userDao = userDao;
	}

	@Override
	public boolean isRegistered(String email) {
		if(userDao.checkUser(email)==1) {
			return true;
		}else {
			return false;
		}		
	}

	@Override
	public Users addNaverUser(NaverLoginUserInfo userInfo) {
		return saveUserInfo(userInfo);
	}
	
	public Users saveUserInfo(NaverLoginUserInfo userInfo) {
		Users user = new Users();
		user.setUsername(userInfo.getName());
		user.setEmail(userInfo.getEmail());
		user.setNickname(userInfo.getNickname());
		user.setAdminFlag(0);
		user.setCreateDate("2017-07-23");

		int i =userInfo.getEmail().indexOf("@"); 
       
        user.setSnsId(userInfo.getEmail().substring(0,i));
        user.setSnsType("naver");
        user.setSnsProfile(userInfo.getProfileImage());
        
        return user;
		
	}
	
	
}
