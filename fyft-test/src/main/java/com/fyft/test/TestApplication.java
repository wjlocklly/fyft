package com.fyft.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

/**
 *<p>Title: TestApplication.java</p>
 *<p>Description: TODO</p>
 *<p>CreateDate: 2017年5月10日</p>
 *@author shen
 *@version v1.0
 */
@SpringBootApplication
public class TestApplication implements EmbeddedServletContainerCustomizer{
	
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer config) {
		config.setPort(8003);
	}
	
}
