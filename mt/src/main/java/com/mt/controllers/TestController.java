package com.mt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mt.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestService testService;
	
	@RequestMapping("testMain")
	@ResponseBody
	public String testMain(){
		String str = "-----------------------进来了！！！-------------------";
		System.out.println(str);
		return str;
	}
	
	@RequestMapping("testDb")
	@ResponseBody
	public List<String> testDb(){
		List<String> str = testService.getTestDb();
		return str;
	}
}
