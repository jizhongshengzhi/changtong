package com.jizhongshengzhi.changTong.controller;


import com.jizhongshengzhi.changTong.entity.User;
import com.jizhongshengzhi.changTong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description: 用户
 * @Author: chenjie
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public User getUser() {
        return userService.getUser();
    }
}
