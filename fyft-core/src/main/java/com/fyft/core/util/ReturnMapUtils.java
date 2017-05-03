package com.fyft.core.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
/**
 *<p>Title: ReturnMapUtils.java</p>
 *<p>Description: 返回Map格式提示信息</p>
 *<p>CreateDate: 2017年5月2日</p>
 *@author shen
 *@version v1.0
 */
public class ReturnMapUtils {

	public  final static String SUCCESS = "success";
	public  final static String MSG = "msg";
	public static Map<String,Object> success(String msg){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(SUCCESS, true);
		result.put(MSG, msg);
		return result;
	}
	public static Map<String,Object> error(String msg){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(SUCCESS, false);
		result.put(MSG, msg);
		return result;
	}
	public static boolean isSuccess(Map<String,Object> args){
		return MapUtils.getBoolean(args, ReturnMapUtils.SUCCESS,false);
	}
}
