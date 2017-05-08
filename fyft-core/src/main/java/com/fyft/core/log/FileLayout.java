package com.fyft.core.log;

import java.util.Date;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

import com.fyft.core.util.DateTimeUtil;


public class FileLayout extends Layout {

	static String dateFormat = "yyyy-MM-dd hh:mm:ss,SSS";
	private String header;
	public FileLayout(String header){
		this.header = header;
	}
	@Override
	public void activateOptions() {

	}

	@Override
	public String format(LoggingEvent event) {
		 Date date = new Date(event.timeStamp);
		    String msg = event.getRenderedMessage();
		    if (msg == null) msg = "";
		    StringBuffer buf = new StringBuffer(msg.length() + 100);
		    buf.append(DateTimeUtil.formatDate(date, dateFormat)).append("\t");
		    buf.append(event.getLevel()).append("\t");
		    buf.append("["+header+"]");
		    buf.append(msg).append("\r\n");
		    if (event.getThrowableStrRep() != null) {
		      String[] rows = event.getThrowableStrRep();
		      for (int i = 0; i < rows.length; i++) {
		        buf.append(rows[i]).append("\r\n");
		      }
		    }
		    return buf.toString();
	}

	@Override
	public boolean ignoresThrowable() {
		return false;
	}

}
