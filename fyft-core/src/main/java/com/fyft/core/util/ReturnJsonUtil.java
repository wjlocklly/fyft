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
	
	public  final static String SUCCESS = "success";
	public  final static String MSG = "msg";
	
	public static String jsonString(String msg){
		if(StringUtils.isBlank(msg)){
			return new JSONObject().toJSONString();
		}
		return JSONObject.toJSON(ReturnMapUtils.success(msg)).toString();
	}
	
	public static String success(String msg){
		if(StringUtils.isBlank(msg)){
			return new JSONObject().toJSONString();
		}
		return JSONObject.toJSON(ReturnMapUtils.success(msg)).toString();
	}
	
	public static String successMap(Map<String, Object> result, String msg){
		result.put(SUCCESS, true);
		if(StringUtils.isNoneBlank(msg)){
			result.put(MSG, msg);
		}
		return JSONObject.toJSON(result).toString();
	}
	
	public static String error(String msg){
		if(StringUtils.isBlank(msg)){
			return new JSONObject().toJSONString();
		}
		return JSONObject.toJSON(ReturnMapUtils.error(msg)).toString();
	}
	
	public static String errorMap(Map<String, Object> result, String msg){
		result.put(SUCCESS, false);
		if(StringUtils.isNoneBlank(msg)){
			result.put(MSG, msg);
		}
		return JSONObject.toJSON(result).toString();
	}
	
	public static boolean isSuccess(Map<String,Object> args){
		return ReturnMapUtils.isSuccess(args);
	}
	
}
