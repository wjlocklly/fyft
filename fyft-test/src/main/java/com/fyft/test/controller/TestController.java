package com.fyft.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *<p>Title: TestControllrt.java</p>
 *<p>Description: TODO</p>
 *<p>CreateDate: 2017年5月10日</p>
 *@author shen
 *@version v1.0
 */
@RestController
public class TestController {
	
	@RequestMapping("test")
	public String index() {
		return "testfdgfdg";
	}
}
