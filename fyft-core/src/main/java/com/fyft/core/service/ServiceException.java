package com.fyft.core.service;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private String msg;
	private static final long serialVersionUID = -5251173889483346586L;

	public ServiceException(String msg){
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}
}
