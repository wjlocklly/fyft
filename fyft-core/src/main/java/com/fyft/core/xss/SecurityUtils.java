package com.fyft.core.xss;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author K
 * 
 */
public class SecurityUtils {

	public static String clearHtml(String value) {
		if (value != null) {
			StringBuffer sb = new StringBuffer();
			char cs[]=value.toCharArray();
			for (char c : cs) {
				if (c == '<') {
					sb.append("&lt;");
				} else if (c == '>') {
					sb.append("&gt;");
				} else {
					sb.append(c);
				}
			}
			return sb.toString();
		}
		return value;
	}
	
	public static String clearHtmlExceptBR(String value) {
		if (value != null) {
			StringBuffer sb = new StringBuffer();
			char cs[]=value.toCharArray();
			int end=cs.length-3;
			int curPos=0;
			for (;curPos<cs.length;curPos++) {
				char c=cs[curPos];
				if (c == '<') {
					if(curPos<end&&(cs[curPos+1]=='b'||cs[curPos+1]=='B')&&(cs[curPos+2]=='r'||cs[curPos+2]=='R')&&cs[curPos+3]=='>'){
						sb.append("<br>");
						curPos+=3;
					}else{
						sb.append("&lt;");
					}
				} else if (c == '>') {
					sb.append("&gt;");
				} else {
					sb.append(c);
				}
			}
			
			return sb.toString();
		}
		return value;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String src = "<p><img src=\"httpuQmd-jpg\"/>ssss<img/><img/><script>alert(1);</script></p>";
		System.out.println(src);
		System.out.println(clearJs(src));
		System.out.println(clearHtmlExceptBR("<br>dddddddd<html>dddd<br>DD<BR>DD<b>"));
		System.out.println(cleanScriptInject(src));
	}

	public static String clearJs(String value) {
		String cleanValue = null;
		if (value != null) {
			cleanValue = Normalizer.normalize(value, Normalizer.Form.NFD);

			// Avoid null characters
//			cleanValue = cleanValue.replaceAll("\\", "＼");
			cleanValue = cleanValue.replaceAll("\u000b", "");
			cleanValue = cleanValue.replaceAll("\0", "");
			cleanValue = cleanValue.replaceAll("%00", "");
			
			// Avoid anything between script tags
			Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

			// Remove any lonesome </script> tag
			scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

			// Remove any lonesome <script ...> tag
			scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
					| Pattern.DOTALL);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

			// Avoid eval(...) expressions
			scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
					| Pattern.DOTALL);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
			// Avoid expression(...) expressions
			scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
					| Pattern.DOTALL);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

			// Avoid javascript:... expressions
			scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
			
			scriptPattern = Pattern.compile("onerror(.*?)=", Pattern.CASE_INSENSITIVE);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
			
			// Avoid vbscript:... expressions
			scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

			// Avoid onload= expressions
			scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
					| Pattern.DOTALL);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
			// System.out.println("value3:"+cleanValue);
		}
		return cleanValue;
	}

	/**
	 * 清除javascript注入代码
	 * 
	 * @return
	 */
	public static String cleanScriptInject(String src) {
		if (src == null)
			return null;
		String regexStart = "<script[^>]*>";
		StringBuffer sb = new StringBuffer();
		Pattern p = Pattern.compile(regexStart, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(src);
		while (m.find()) {
			m.appendReplacement(sb, m.group().replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
		}
		m.appendTail(sb);
		src = sb.toString();
		sb.delete(0, sb.length());

		String regexEnd = "</script[^>]*>";
		p = Pattern.compile(regexEnd, Pattern.CASE_INSENSITIVE);
		m = p.matcher(src);
		while (m.find()) {
			m.appendReplacement(sb, m.group().replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	public static String xssEncode(String s){
		if (s == null || s.isEmpty()) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '>':
				sb.append('＞');// 全角大于号
				break;
			case '<':
				sb.append('＜');// 全角小于号
				break;
			case '\'':
				sb.append('‘');// 全角单引号
				break;
			case '\"':
				sb.append('“');// 全角双引号
				break;
			case '&':
				sb.append('＆');// 全角
				break;
			case '\\':
				sb.append('＼');// 全角斜线
				break;
			case '#':
				sb.append('＃');// 全角井号
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}
	
	
}
