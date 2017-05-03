package com.fyft.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;


/**
 *<p>Title: AdminApplication.java</p>
 *<p>Description: 后台管理应用入口</p>
 *<p>CreateDate: 2017年5月3日</p>
 *@author shen
 *@version v1.0
 */
@SpringBootApplication
public class AdminApplication implements EmbeddedServletContainerCustomizer {
	
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer config) {
		config.setPort(8001);
	}
	
}
