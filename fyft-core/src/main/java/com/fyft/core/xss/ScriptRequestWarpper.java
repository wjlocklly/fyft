package com.fyft.core.xss;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 清除请求参数值中包含有的Javascript注入代码
 * @author ccn
 *
 */
public class ScriptRequestWarpper extends HttpServletRequestWrapper {
	private HttpServletRequest request;

	public ScriptRequestWarpper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		String value = request.getParameter(name);
		//value = StringEscapeUtils.escapeHtml(value);
		value = SecurityUtils.cleanScriptInject(value);
		return value;
	}

	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			//encodedValues[i] = StringEscapeUtils.escapeHtml(values[i]);
			encodedValues[i] = SecurityUtils.cleanScriptInject(values[i]);
		}
		
		return encodedValues;
	}
	public static void main(String[] args) {
		String s = "%22style%3Dfontfamily%3Aexpres%5Csion%28eval%28%2Fale%2F.source%2B%2Frt%2F.source%2B%2F%28455%29%2F.source%29%29%21";
		System.out.println(StringEscapeUtils.escapeHtml3(URLDecoder.decode(s)));
	}
}
