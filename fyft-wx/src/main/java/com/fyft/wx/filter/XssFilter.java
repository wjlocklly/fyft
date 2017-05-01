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

import com.fyft.core.xss.ScriptRequestWarpper;


/**
 *<p>Title: XssFilter.java</p>
 *<p>Description: 脚本过滤</p>
 *<p>CreateDate: 2017年5月1日</p>
 *@author shen
 *@version v1.0
 */
@WebFilter(filterName="xssFilter", urlPatterns="/*")
public class XssFilter implements Filter {

    /**
     * Default constructor. 
     */
    public XssFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		//String url = req.getRequestURL().toString();
		//if (url.contains(".action") || url.contains(".do")|| url.endsWith("jsp")) {
			req = new ScriptRequestWarpper(req); // 处理javascript注入代码
		//}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
