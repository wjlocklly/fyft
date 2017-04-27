package com.fyft.wx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *<p>Title: IndexController.java</p>
 *<p>Description: 主页</p>
 *<p>CreateDate: 2017年4月24日</p>
 *@author shen
 *@version v1.0
 */

@RestController
public class IndexController {
	
	@RequestMapping("test")
	public String name() {
		return "welcome to the FYFT index page.";
	}
}
