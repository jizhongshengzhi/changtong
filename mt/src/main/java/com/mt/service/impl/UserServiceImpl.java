package com.mt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.dao.UserDao;
import com.mt.domain.User;
import com.mt.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public Boolean getUser(User user) {
		User userInfo = userDao.getUser(user);
		if(userInfo == null){
			return false;
		}
		return true;
	}

	@Override
	public void register(User user) {
		userDao.register(user);
	}
	
}
