package com.fyft.core.xss;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * XSS攻击拦截器
 * 
 * @author ccn
 *
 */
public class XSSFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURL().toString();
		if (url.contains(".action") || url.contains(".do")|| url.endsWith("jsp")) {
			req = new ScriptRequestWarpper(req); // 处理javascript注入代码
		}
		chain.doFilter(req, response);
	}

	@Override
	public void destroy() {

	}

}
