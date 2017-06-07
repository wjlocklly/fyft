package com.fyft.wx.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.fyft.wx.bean.User;
import com.fyft.wx.dao.AuthDao;

/**
 *<p>Title: AuthDaoImpl.java</p>
 *<p>Description: 用户基本操作</p>
 *<p>CreateDate: 2017年6月5日</p>
 *@author shen
 *@version v1.0
 */
@Service
public class AuthDaoImpl implements AuthDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public User getUserByCode(String userCode) {
		String sql = "select * from BLOG_USER where USER_CODE=?";
		List<User> list = jdbcTemplate.query(sql, new Object[]{ userCode }, 
				new BeanPropertyRowMapper<User>(User.class));
		if(list.size() > 0){
			return list.get(0);
		}else
			return null;
	}
	
	public User getUserById(String userId){
		String sql = "select * from BLOG_USER where USER_ID=?";
		List<User> list = jdbcTemplate.query(sql, new Object[]{ userId }, 
				new BeanPropertyRowMapper<User>(User.class));
		if(list.size() > 0){
			return list.get(0);
		}else
			return null;
	}

}
