package com.fyft.wx.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fyft.core.util.ReturnJsonUtil;

/**
 *<p>Title: AuthController.java</p>
 *<p>Description: 登录注销等</p>
 *<p>CreateDate: 2017年5月2日</p>
 *@author shen
 *@version v1.0
 */

@RestController
public class AuthController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		String userCode = request.getParameter("userCode");
		String passWord = request.getParameter("passWord");
		
		System.out.println("login: " + userCode + "  "+ passWord);
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from blog_user");
		System.out.println(list);
		
		if(StringUtils.equals(userCode, "admin") &&  StringUtils.equals(passWord, "123")){
			return ReturnJsonUtil.success("登录成功").toString();
			//return "{success:true}";
		}else
			//return "{success:false}";
			return ReturnJsonUtil.error("登录失败").toString();
	}
}
