package com.fyft.core.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fyft.core.context.FyftContext;
import com.fyft.core.logger.CoreLogger;


public abstract class ServiceRegistor implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent context) {
		List<ServicePack> packs = registedServicePack();
		if(packs!=null){
			for(int i = 0;i<packs.size();i++){
				ServicePack pack = packs.get(i);
				StringBuilder sb = new StringBuilder();
				sb.append("\n");
				sb.append("==========================================="+"\n");
				sb.append("开始卸载服务\n");
				sb.append("服务标识："+pack.getServiceId()+"\n");
				sb.append("服务名称："+pack.getServiceName()+"\n");
//				sb.append("服务入口："+pack.getServiceEndPoint()+"\n");
//				sb.append("服务实现："+pack.getServiceClass()+":"+pack.getServiceMethod()+"\n");
//				sb.append("服务描述："+pack.getServiceDesc()+"\n");
				sb.append("==========================================="+"\n");
				CoreLogger.logger(this).info(sb.toString());
				FyftContext.servies.remove(pack.getServiceId());
			}
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent ctxe) {
		List<ServicePack> packs = registedServicePack();
		ServletContext ctx = ctxe.getServletContext();
		String path = ctx.getContextPath();
		
		if(packs!=null){
			for(int i = 0;i<packs.size();i++){
				ServicePack pack = packs.get(i);
				pack.setContextPath(path);
				pack.setPrivateEndPoint(pack.getServiceEndPoint());
				//pack.setServiceEndPoint("http://"+ServerConfig.HOST+":"+ServerConfig.PORT+pack.getServiceEndPoint());
				pack.setServiceEndPoint(pack.getServiceEndPoint());
				StringBuilder sb = new StringBuilder();
				sb.append("\n\n");
				sb.append("==========================================="+"\n");
				sb.append("开始加载服务\n");
				sb.append("服务标识："+pack.getServiceId()+"\n");
				sb.append("服务名称："+pack.getServiceName()+"\n");
				sb.append("服务描述："+pack.getServiceDesc()+"\n");
				sb.append("服务入口："+pack.getServiceEndPoint()+"\n");
				sb.append("服务实现："+pack.getServiceClass()+":"+pack.getServiceMethod()+"\n");
				sb.append("服务规范：服务实现约束如下：\npublic String "+pack.getServiceMethod()+"(String input){\n return output;\n}\n");
				sb.append("==========================================="+"\n");
				CoreLogger.logger(this).info(sb.toString());
				FyftContext.servies.put(pack.getServiceId(), pack);
			}
		}
	}
	
	protected abstract List<ServicePack> registedServicePack();

}
