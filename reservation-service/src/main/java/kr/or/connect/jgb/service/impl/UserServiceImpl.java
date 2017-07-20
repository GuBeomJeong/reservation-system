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
	public boolean isRegistration(String email) {
		if(userDao.checkUser(email)==1) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public int addUser(NaverLoginUserInfo userInfo) {
		Users user = new Users();
		user.saveUserInfo(userInfo);
		return userDao.insert(user);
	}
	
	
}
