package com.fyft.wx.bean;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import com.fyft.core.util.EncryptionUtil;
import com.fyft.wx.exception.AuthTokenException;
import com.fyft.wx.service.AuthService;

public class AuthUser{
	
	private static ApplicationContext applicationContext;//启动类set入，调用下面set方法
	
	public static void setApplicationContext(ApplicationContext context) {
		// TODO Auto-generated method stub
		applicationContext = context;
	}
	
	String userId;//
	String name;//
	String phone;//
	String password;//
	String url;//
	String type;//用户类型

	/**
	 * 克隆
	 * 
	 * @param source
	 * @return
	 */
	public AuthUser clone() {
		AuthUser clone = new AuthUser();
		try {
			clone.setName(this.getName());
			clone.setPhone(this.getPhone());
			clone.setPassword(this.getPassword());
			clone.setUrl(this.getUrl());
			clone.setType(this.getType());
			clone.setUserId(this.getUserId());
		} catch (Exception e) {
		}
		return clone;
	}

	/**
	 * 辅助保存登陆用户
	 */
	private static ThreadLocal<AuthUser> local = new ThreadLocal<AuthUser>();

	/**
	 * set 入用户
	 * 
	 * @param user
	 */
	public static void setLocal(AuthUser user) {
		local.set(user);
	}

	/**
	 * get 出用户
	 * 
	 * @return
	 */
	public static AuthUser getLocal() {
		return local.get();
	}

	/**
	 * 保存授权用户信息
	 */
	public static final String REQUEST_USER_ATTR = "AuthUser";

	/**
	 * 授权认证token，header
	 */
	public static final String REQUEST_AUTH_HEADER = "TokenID";

	public AuthUser() {
	}

	/**
	 * 工厂方法
	 * 
	 * @param tokenId
	 * @return
	 * @throws AuthTokenException
	 */
	public static AuthUser valueOf(String tokenId) throws AuthTokenException {
		try {
			String source = EncryptionUtil.desDecryption(tokenId);
			String[] parts = source.split(";");
			AuthUser user = new AuthUser();
			user.setType(parts[0]);
			user.setPhone(parts[1]);
			user.setPassword(parts[2]);
			user.setUserId(parts[3]);
			return user;

		} catch (Exception e) {
			throw new AuthTokenException(tokenId, e);
		}
	}
	
	/**
	 * 验证用户授权信息
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean validate(AuthUser u) throws Exception {
		AuthService authService  = (AuthService)applicationContext.getBean(AuthService.class);
		User user = authService.getUserById(u.getUserId());
		if(StringUtils.isNoneBlank(user.getUserCode())){
			AuthUser vo = u.clone();
			vo.setUserId(user.getUserCode());
			vo.setName(user.getUserName());
			vo.setPhone(null);
			vo.setPassword(user.getUserPwd());
			vo.setUrl(null);
			vo.setType(user.getUserType());
			AuthUser.setLocal(vo);
			return true;
		} else 
			return false;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
