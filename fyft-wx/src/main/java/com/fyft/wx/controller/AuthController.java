package com.fyft.wx.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		String userCode = request.getParameter("userCode");
		String passWord = request.getParameter("passWord");
		System.out.println("login: " + userCode + "  "+ passWord);
		if(StringUtils.equals(userCode, "admin") &&  StringUtils.equals(passWord, "123")){
			return ReturnJsonUtil.success("登录成功").toString();
			//return "{success:true}";
		}else
			//return "{success:false}";
			return ReturnJsonUtil.error("登录失败").toString();
	}
}
