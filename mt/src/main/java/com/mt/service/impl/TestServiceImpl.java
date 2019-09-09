package com.mt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.dao.TestDao;
import com.mt.service.TestService;
@Service("testService")
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testDao;
	
	@Override
	public List<String> getTestDb() {
		return testDao.getTestDb();
	}

}
