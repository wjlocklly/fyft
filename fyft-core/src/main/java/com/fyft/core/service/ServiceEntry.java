package com.fyft.core.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fyft.core.logger.CoreLogger;


public abstract class ServiceEntry extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1255121309823864519L;

	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String asyn = request.getParameter("asyn");
		String serviceClass = request.getParameter("serviceClass");
		String serviceMethod = request.getParameter("serviceMethod");
		//String serviceName = new String(request.getParameter("serviceName").getBytes("iso-8859-1"),"UTF-8");
		final String param = request.getParameter("param");
		if(StringUtils.isBlank(serviceClass)){
			response.getWriter().write("Service Started Successfully!");
			return;
		}
		try {
			CoreLogger.logger(this).debug("开始执行服务："+serviceClass+"."+serviceMethod);
			final Object obj = getServiceClass(serviceClass);
			Class clazz = obj.getClass();
			
			final Method method = clazz.getMethod(serviceMethod, String.class);
			if(StringUtils.equals(asyn, "false")){
				Object result = method.invoke(obj, param);
				response.getWriter().write(String.valueOf(result));
			}else{
				Thread t = new Thread(new Runnable(){
					@Override
					public void run() {
						try{
							method.invoke(obj, param);
							CoreLogger.logger(this).debug("Service asyn execute finished");
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				});
				t.start();
				response.getWriter().write("Service Thread started ...");
			}
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} 
	}
	protected abstract Object getServiceClass(String className) throws ServiceException;
}
