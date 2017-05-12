package com.fyft.core.service;

import java.util.HashMap;
import java.util.Map;

import com.fyft.core.context.FyftContext;
import com.fyft.core.util.HttpClientHelper;

/**
 * 
 * <p>Title:服务调用客户端</p>
 * <p>Description:可以实现同步调用或者异步调用</p>
 * <p>Company:广州平云</p>
 * @author lyp
 * @date 2016年4月13日 上午9:48:58
 * @version v1.0
 */
public class ServiceClient {

	 // 创建 HttpParams 以用来设置 HTTP 参数（这一部分不是必需的）  

	private String asyn = "true";//默认异步调用
	private String serviceId;
	public ServiceClient(String serviceId){
		this.serviceId = serviceId;
	}
	public ServiceClient(String serviceId,String asyn){
		this.serviceId = serviceId;
		this.asyn = asyn;
	}
	
	public String invoke(String str) throws ServiceException{
		ServicePack service = FyftContext.servies.get(serviceId);
		if(service == null){
			throw new ServiceException("No Service Found With Service Id: "+serviceId);
		}
		//String endpoint = "http://"+ServerConfig.HOST+":"+ServerConfig.PORT+service.getPrivateEndPoint();
		String endpoint = service.getPrivateEndPoint();
		
		/**httpclient common method**/
		
		Map<String,String> args =  new HashMap<String,String>();
		args.put("serviceClass",service.getServiceClass());
		args.put("serviceMethod",service.getServiceMethod());
		args.put("serviceId",service.getServiceId());
		args.put("serviceName",service.getServiceName());
		args.put("serviceDesc",service.getServiceDesc());
		args.put("asyn",this.asyn);
		args.put("param",str);
		
		String result = HttpClientHelper.post(endpoint, args);
		return result;
		
//		
//		CloseableHttpClient client = HttpClients.createDefault();
//		
//		HttpPost post = new HttpPost(endpoint);
//		
//	
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("serviceClass",service.getServiceClass()));
//		params.add(new BasicNameValuePair("serviceMethod",service.getServiceMethod()));
//		params.add(new BasicNameValuePair("serviceId",service.getServiceId()));
//		params.add(new BasicNameValuePair("serviceName",service.getServiceName()));
//		params.add(new BasicNameValuePair("serviceDesc",service.getServiceDesc()));
//		params.add(new BasicNameValuePair("asyn",this.asyn));
//		params.add(new BasicNameValuePair("param",str));
//		
//		
//		try {
//			HttpEntity reqEntity = new UrlEncodedFormEntity(params,"UTF-8");
//			//HttpEntity reqEntity = EntityBuilder.create()/*.setContentType(ContentType.create("text/plain", "UTF-8"))*/.setParameters(params).build();
//			post.setEntity(reqEntity);
//			
//			RequestConfig defaultRequestConfig = RequestConfig.custom()
//					.setConnectTimeout(30000)
//					.setSocketTimeout(30000)
//					.setConnectionRequestTimeout(30000)
//					.build();
//			post.setConfig(defaultRequestConfig);
//			
//			HttpResponse response = client.execute(post);
//			
//			HttpEntity rspEntity = response.getEntity();
//			String result = EntityUtils.toString(rspEntity,"UTF-8");
//			return result;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ServiceException(e.getMessage());
//		} 
	}
}
