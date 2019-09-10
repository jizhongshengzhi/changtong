package com.mt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mt.common.utils.StringUtil;
import com.mt.domain.User;
import com.mt.service.UserService;
import com.mt.vo.Result;

@Controller
@RequestMapping("login")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("login")
	@ResponseBody
	public Result login(User user){
		
		if(StringUtil.isBlank(user.getUserName())){
			return Result.error("用户名不能为空！");
		}
		if(StringUtil.isBlank(user.getPassword())){
			return Result.error("密码不能为空！");
		}
		try {
			Boolean isLogin = userService.getUser(user);
			if(isLogin){
				return Result.success();
			}
			return Result.error("账号密码不正确");
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("程序异常");
		}
	}
	
	@RequestMapping("register")
	@ResponseBody
	public Result register(@RequestBody User user){
		
		if(StringUtil.isBlank(user.getUserName())){
			return Result.error("用户名不能为空！");
		}
		if(StringUtil.isBlank(user.getPassword())){
			return Result.error("密码不能为空！");
		}
		if(user.getGender() == null){
			return Result.error("性别不能为空！");
		}
		if(user.getBirthday() == null){
			return Result.error("出生日期不能为空！");
		}
		
		try {
			User checkUserName = new User();
			checkUserName.setUserName(user.getUserName());
			Boolean check = userService.getUser(checkUserName);
			if(check){
				return Result.error("用户名已存在！");
			}
			userService.register(user);
			return Result.success(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("程序异常");
		}
	}
	
}
