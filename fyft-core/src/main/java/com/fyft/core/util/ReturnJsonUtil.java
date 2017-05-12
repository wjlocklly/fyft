package com.fyft.core.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 *<p>Title: ReturnJsonUtil.java</p>
 *<p>Description: 返回Json格式提示信息</p>
 *<p>CreateDate: 2017年5月2日</p>
 *@author shen
 *@version v1.0
 */
public class ReturnJsonUtil {

	public static String jsonString(String msg){
		if(StringUtils.isBlank(msg)){
			return new JSONObject().toJSONString();
		}
		return JSONObject.toJSON(ReturnMapUtils.success(msg)).toString();
	}
	
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
