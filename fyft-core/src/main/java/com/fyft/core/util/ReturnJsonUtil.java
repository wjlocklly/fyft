package com.fyft.core.util;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 *<p>Title: ReturnJsonUtil.java</p>
 *<p>Description: 返回Json格式提示信息</p>
 *<p>CreateDate: 2017年5月2日</p>
 *@author shen
 *@version v1.0
 */
public class ReturnJsonUtil {

	public static Object success(String msg){
		return JSONObject.toJSON(ReturnMapUtils.success(msg));
	}
	
	public static Object error(String msg){
		return JSONObject.toJSON(ReturnMapUtils.error(msg));
	}
	
	public static boolean isSuccess(Map<String,Object> args){
		return ReturnMapUtils.isSuccess(args);
	}
	
}
