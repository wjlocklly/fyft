package com.fyft.wx.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.fyft.wx.bean.AuthUser;
import com.fyft.wx.controller.AuthController;
import com.fyft.wx.exception.AuthTokenException;
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

	private final static Logger LOG = LoggerFactory.getLogger(LoginFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req =  (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		String token = null;
		Enumeration<String> headerNames = req.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String key = headerNames.nextElement();
			if(StringUtils.equals(key, "token")){
				token = req.getHeader("token");
				break;
			}
		}
		
		/*****此处处理token的问题*****/
		if(StringUtils.isBlank(token)){
			res.sendRedirect("/");
		}
		
		try {
			AuthUser user = AuthUser.valueOf(token);
			if(user.validate(user)){
				//request.setAttribute(AuthUser.REQUEST_USER_ATTR, user);//是否必要(Q)
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", false);
			jsonObject.put("msg", "token验证失败！");
			writeJSONObject((HttpServletResponse)response, jsonObject);
			return;
		}
		
		//chain.doFilter(request, response);
	}
	
	protected void writeJSONObject(HttpServletResponse response,
			JSONObject jsonObject) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");   
		response.setCharacterEncoding("UTF-8");   
		response.setHeader("Pragma", "No-cache");   
		response.setHeader("Cache-Control", "no-cache");   
		response.setDateHeader("Expires", 0);   

		PrintWriter pw = response.getWriter();
		pw.write(jsonObject.toString());
		pw.flush();
		pw.close();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
