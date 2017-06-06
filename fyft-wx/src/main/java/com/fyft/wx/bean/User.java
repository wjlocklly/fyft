package com.fyft.wx.bean;
/**
 *<p>Title: User.java</p>
 *<p>Description: 用户</p>
 *<p>CreateDate: 2017年6月5日</p>
 *@author shen
 *@version v1.0
 */
public class User {
	
	private Integer userId;
	private String userCode;
	private String userName;
	private String userPwd;
	private String userType;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userCode=" + userCode + ", userName=" + userName + ", userPwd=" + userPwd
				+ "]";
	}
	
}
