package com.fyft.admin.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *<p>Title: AdminLogger.java</p>
 *<p>Description: 日志</p>
 *<p>CreateDate: 2017年5月10日</p>
 *@author shen
 *@version v1.0
 */
public class AdminLogger {
	public static Logger logger(Object object){
		return LoggerFactory.getLogger(object.getClass().getName());
	}
}
