package com.mt.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface TestDao {

	List<String> getTestDb();

}
