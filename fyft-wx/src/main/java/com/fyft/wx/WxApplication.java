package com.fyft.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 *<p>Title: WxApplication.java</p>
 *<p>Description: 入口</p>
 *<p>CreateDate: 2017年4月24日</p>
 *@author shen
 *@version v1.0
 */

@SpringBootApplication
@ServletComponentScan
public class WxApplication implements EmbeddedServletContainerCustomizer{
	
	public static void main(String[] args) {
		SpringApplication.run(WxApplication.class, args);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer config) {
		config.setPort(8002);//设置端口
	}
}
