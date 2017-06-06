package com.fyft.wx.service;

import com.fyft.wx.bean.User;

/**
 *<p>Title: AuthService.java</p>
 *<p>Description: 认证接口</p>
 *<p>CreateDate: 2017年6月5日</p>
 *@author shen
 *@version v1.0
 */
public interface AuthService {
	
	public User getUserByCode(String userCode);
}
