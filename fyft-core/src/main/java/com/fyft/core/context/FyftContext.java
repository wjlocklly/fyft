package com.fyft.core.context;

import java.util.Hashtable;
import java.util.Map;

import com.fyft.core.service.ServicePack;


/**
 *<p>Title: FyftContext.java</p>
 *<p>Description: 上下文，系统静态资源，启动时初始化</p>
 *<p>CreateDate: 2017年5月12日</p>
 *@author shen
 *@version v1.0
 */
public class FyftContext {

	public static Map<String,ServicePack> servies = new Hashtable<String,ServicePack>();
	
}
