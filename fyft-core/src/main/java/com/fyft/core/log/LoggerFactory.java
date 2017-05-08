package com.fyft.core.log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;


public class LoggerFactory {

	
	private static LoggerFactory _inst;
	private static Map<String,Logger> loggers = new HashMap<String,Logger>();
	private LoggerFactory(){};
	public static LoggerFactory getInstance(){
		if(_inst == null){
			_inst = new LoggerFactory();
		}
		return _inst;
	}
	
	private static String getloggerFile(String appName){
		//String home = ServerConfig.getEacHome()+"/eac";
		//String home = ServerConfig.getEacHome();
		String home = System.getProperty("catalina.base");//tomcat
	    String filename = home+"/logs/"+appName+".log";
	    return filename;
	}
	public Logger getLogger(String appName){
		
		Logger logger = loggers.get(appName);
		if(logger!=null){
			return logger;
		}
		RollingFileAppender appender = null;
		try {
			appender = new RollingFileAppender(new FileLayout(appName),
					getloggerFile(appName), true);
			appender.setMaxBackupIndex(3);
			appender.setMaximumFileSize(52428800L);
			appender.setEncoding("UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger = Logger.getLogger(appName);
		logger.removeAllAppenders();
		logger.addAppender(appender);
		loggers.put(appName, logger);
		return logger;
	}
	public static Map<String, Logger> getLoggers() {
		return loggers;
	}
}
