package com.mt.service;

import com.mt.domain.User;

public interface UserService {

	Boolean getUser(User user);

	void register(User user);

}
