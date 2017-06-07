package com.fyft.wx.exception;

public class AuthTokenException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "认证token解析失败";
	
	/**
	 * 构造函数
	 * @param message
	 * @param cause
	 */
	public AuthTokenException(String token, Throwable cause) {
		super(MESSAGE + "[" + token + "]", cause);
	}

	/**
	 * 构造函数
	 * @param message
	 */
	public AuthTokenException(String token) {
		super(MESSAGE + "[" + token + "]");
	}
	
}
