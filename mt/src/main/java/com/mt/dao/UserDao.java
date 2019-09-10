package com.mt.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.mt.domain.User;

@MapperScan
public interface UserDao {

	User getUser(User user);

	void register(User user);
	
}
