package com.fyft.wx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fyft.wx.interceptor.MyInterceptor;

/**
 *<p>Title: MyWebAppConfigurer.java</p>
 *<p>Description: web配置</p>
 *<p>CreateDate: 2017年5月10日</p>
 *@author shen
 *@version v1.0
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/*");
		super.addInterceptors(registry);
	}

}
