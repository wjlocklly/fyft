package com.fyft.wx.logger;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *<p>Title: WxLogger.java</p>
 *<p>Description: TODO</p>
 *<p>CreateDate: 2017年5月9日</p>
 *@author shen
 *@version v1.0
 */
public class WxLogger {

	public static Logger logger(Object object){
		return LoggerFactory.getLogger(object.getClass().getName());
	}
	
}
