package com.fyft.core.service;

/**
 * 
 * <p>Title:服务包</p>
 * <p>Description:</p>
 * <p>Company:广州平云</p>
 * @author lyp
 * @date 2016年4月13日 上午9:18:26
 * @version v1.0
 */
public class ServicePack {

	/**
	 * 服务Id，全局唯一
	 */
	private String serviceId;
	/**
	 * 服务名称
	 */
	private String serviceName;
	/**
	 * 服务类全路径
	 */
	private String serviceClass;
	/**
	 * 服务类方法名
	 * 方法签名为：public String xxx(String str){}
	 * 输入和输出参数均为String
	 */
	private String serviceMethod;
	/**
	 * 服务描述
	 */
	private String serviceDesc;
	
	/**
	 * 服务入口地址
	 */
	private String serviceEndPoint;
	/**
	 * 服务所在模块上下文
	 */
	private String contextPath;
	
	/**
	 * 服务相对路径入口地址
	 */
	private String privateEndPoint;
	
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceClass() {
		return serviceClass;
	}
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}
	public String getServiceMethod() {
		return serviceMethod;
	}
	public void setServiceMethod(String serviceMethod) {
		this.serviceMethod = serviceMethod;
	}
	public String getServiceDesc() {
		return serviceDesc;
	}
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	public String getServiceEndPoint() {
		return serviceEndPoint;
	}
	public void setServiceEndPoint(String serviceEndPoint) {
		this.serviceEndPoint = serviceEndPoint;
	}
	
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	public String getPrivateEndPoint() {
		return privateEndPoint;
	}
	public void setPrivateEndPoint(String privateEndPoint) {
		this.privateEndPoint = privateEndPoint;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((serviceId == null) ? 0 : serviceId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServicePack other = (ServicePack) obj;
		if (serviceId == null) {
			if (other.serviceId != null)
				return false;
		} else if (!serviceId.equals(other.serviceId))
			return false;
		return true;
	}
}
