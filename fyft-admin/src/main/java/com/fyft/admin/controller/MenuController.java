package com.fyft.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fyft.admin.logger.AdminLogger;
import com.fyft.core.util.ReturnJsonUtil;

/**
 *<p>Title: MenuController.java</p>
 *<p>Description: 菜单</p>
 *<p>CreateDate: 2017年5月10日</p>
 *@author shen
 *@version v1.0
 */
@RestController
@RequestMapping("menu")
public class MenuController {
	
	@RequestMapping("/menuList")
	public String getMenuList(){
		AdminLogger.logger(this).info("get menuList");
		//return null;
		return ReturnJsonUtil.success("test").toString();
	}
}
