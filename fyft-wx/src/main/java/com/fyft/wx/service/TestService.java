package com.fyft.wx.service;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.pool.DruidDataSource;
import com.fyft.core.datasuorce.TargetDataSource;

/**
 *<p>Title: TestService.java</p>
 *<p>Description: TODO</p>
 *<p>CreateDate: 2017年5月16日</p>
 *@author shen
 *@version v1.0
 */
@Service
public class TestService {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	DruidDataSource dataSource;
	
	
	@Transactional
	@TargetDataSource(name="ds1")
	public String mysqlTest(){
		//List<Map<String, Object>> list = jdbcTemplate.queryForList("select t.*, t.rowid from blog t");
		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from blog");
		return "test";
	}
}
