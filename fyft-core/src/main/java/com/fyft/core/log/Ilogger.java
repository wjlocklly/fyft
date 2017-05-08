package com.fyft.core.log;

import org.apache.log4j.Logger;


public abstract class Ilogger {

	public Logger getLogger(){
		return LoggerFactory.getInstance().getLogger(getAppName());
	}
	public abstract String getAppName();
}
