package com.fyft.wx.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fyft.core.util.CryptHelper;
import com.fyft.core.util.EncryptionUtil;
import com.fyft.core.util.ReturnJsonUtil;
import com.fyft.wx.bean.User;
import com.fyft.wx.service.AuthService;

/**
 *<p>Title: AuthController.java</p>
 *<p>Description: 登录注销等</p>
 *<p>CreateDate: 2017年5月2日</p>
 *@author shen
 *@version v1.0
 */

@RestController
public class AuthController {
	
	private final static Logger LOG = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		String userCode = request.getParameter("userCode");
		String passWord = request.getParameter("passWord");

		User user = authService.getUserByCode(userCode);
		if(null == user){
			return ReturnJsonUtil.error("登录失败,用户不存在");
		}
		
		if(!StringUtils.equals(user.getUserPwd(), CryptHelper.getInstance().encryptSHA1(passWord))){
			return ReturnJsonUtil.error("登录失败,密码不正确");//sysadmin test1234
		}
		
		Map<String, Object> map = new HashMap<>();
		try {
			String createToken = EncryptionUtil.createToken(user.getUserType(), null, passWord, user.getUserId().toString());
			map.put("token", createToken);
			LOG.info(createToken);
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnJsonUtil.error("登录失败,系统异常");
		}
		return ReturnJsonUtil.successMap(map, "登录成功");
		//return ReturnJsonUtil.success("登录成功");
	}
	
	
}
