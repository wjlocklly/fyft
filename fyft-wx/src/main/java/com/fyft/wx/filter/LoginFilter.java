package com.fyft.wx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.fyft.wx.logger.WxLogger;

/**
 *<p>Title: LoginFilter.java</p>
 *<p>Description: 登录过滤</p>
 *<p>CreateDate: 2017年5月30日</p>
 *@author shen
 *@version v1.0
 */
@WebFilter(filterName="loginFilter", urlPatterns={
		"/index/*",
		"/other/*",
})
public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req =  (HttpServletRequest)request;
		WxLogger.logger(this).info("--->"+req.getRequestURI());
		
		/*****此处处理token的问题*****/
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
