package com.jizhongshengzhi.changTong.service;

import com.jizhongshengzhi.changTong.entity.User;
import com.jizhongshengzhi.changTong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description:
 * @Author: chenjie
 **/
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser() {
        User user = new User();
        user.setUserName("testName");
        user.setPassword("123");
        userRepository.save(user);
        return user;
    }
}
