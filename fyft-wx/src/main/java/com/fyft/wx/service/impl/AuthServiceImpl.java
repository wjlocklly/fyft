package com.fyft.wx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyft.wx.bean.User;
import com.fyft.wx.dao.AuthDao;
import com.fyft.wx.service.AuthService;

/**
 *<p>Title: AuthServiceImpl.java</p>
 *<p>Description: TODO</p>
 *<p>CreateDate: 2017年6月5日</p>
 *@author shen
 *@version v1.0
 */
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthDao authDao;
	
	@Override
	public User getUserByCode(String userCode) {
		return authDao.getUserByCode(userCode);
	}

	@Override
	public User getUserById(String userId) {
		return authDao.getUserById(userId);
	}

}
