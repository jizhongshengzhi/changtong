package com.mt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("testMain")
	@ResponseBody
	public String testMain(){
		String str = "-----------------------�������˹�������-------------------";
		System.out.println(str);
		return str;
	}
	
}
