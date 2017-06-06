package com.fyft.wx.dao;

import com.fyft.wx.bean.User;

/**
 *<p>Title: AuthDao.java</p>
 *<p>Description: TODO</p>
 *<p>CreateDate: 2017年6月5日</p>
 *@author shen
 *@version v1.0
 */
public interface AuthDao {

	public User getUserByCode(String userCode);
	
}
