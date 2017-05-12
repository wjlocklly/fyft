package com.fyft.core.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class HttpClientHelper {

	
	
	
	/**
	 * 
	 * @param url 请求url
	 * @param body 请求内容体
	 * @param keystore p12证书路径
	 * @param pwd 证书密码
	 * @return
	 */
	public static String post(String url,String body,String keystore,String pwd){
		return post(url,body,null,keystore,pwd);
	}
	/**
	 * 
	 * @param url
	 * @param body
	 * @param header 自定义请求头
	 * @return
	 */
	public static String post(String url,String body,Map<String,String> header){
		return post(url,body,header,null,null);
	}
	
	public static String post(String url,String body){
		return post(url,body,null,null,null);
	}
	
	/**以内容的方式提交请求
	 * @param url
	 * @param body
	 * @return
	 */
	public static String post(String url,String body,Map<String,String> header,String keystore,String passwd){
		System.setProperty("jsse.enableSNIExtension", "false");
		CloseableHttpClient httpclient = createClientDefault(url,keystore,passwd);
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(30000)
				.setSocketTimeout(30000)
				.setConnectionRequestTimeout(30000)
				.build();

        
		HttpPost post = new HttpPost(url);
		if(MapUtils.isNotEmpty(header)){
			for(String key:header.keySet()){
				post.addHeader(key, header.get(key));
			}
		}
		post.setConfig(config);
		CloseableHttpResponse response = null;
		
		try {
			HttpEntity reqEntity = EntityBuilder.create().setContentType(ContentType.create("text/html", "UTF-8")).setText(body).build();
			
			post.setEntity(reqEntity);
			response = httpclient.execute(post);
			
		    HttpEntity entity = response.getEntity();
		    
		    if (entity != null) {
		        String str = EntityUtils.toString(entity, "UTF-8");
		        return str;
		    }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    try {
		    	if(response!=null){
		    		response.close();
		    	}
				
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	
	/**以参数的方式提交请求
	 * @param url
	 * @param args,提交的参数，以键值对的方式提交，如果没有参数时，可传空
	 * @return
	 */
	public static String post(String url,Map<String,String> args){
		return post(url,args,null);
	}
	public static String post(String url,Map<String,String> args,Map<String,String> header){
		System.setProperty("jsse.enableSNIExtension", "false");
		CloseableHttpClient httpclient = createClientDefault(url);
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(30000)
				.setSocketTimeout(30000)
				.setConnectionRequestTimeout(30000)
				.build();

		HttpPost post = new HttpPost(url);
		if(MapUtils.isNotEmpty(header)){
			for(String key:header.keySet()){
				post.addHeader(key, header.get(key));
			}
		}
		post.setConfig(config);
		CloseableHttpResponse response = null;
		
		try {
			if(!MapUtils.isEmpty(args)){
				List<NameValuePair> params=new ArrayList<NameValuePair>();
				Set<String> key = args.keySet();
				Iterator<String> iter = key.iterator();
				while(iter.hasNext()){
					String name = iter.next();
					String value = args.get(name);
					params.add(new BasicNameValuePair(name,value));
				}
				HttpEntity reqEntity = new UrlEncodedFormEntity(params,"UTF-8");
				System.out.println("req:"+EntityUtils.toString(reqEntity,"UTF-8"));
				post.setEntity(reqEntity);
			}
			
			response = httpclient.execute(post);
			
		    HttpEntity entity = response.getEntity();
		    
		    if (entity != null) {
		        String str = EntityUtils.toString(entity, "UTF-8");
		        return str;
		    }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    try {
		    	if(response!=null){
		    		response.close();
		    	}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 上传资源接口
	 * @param url
	 * @param path
	 * @return
	 */
	public static String upload(String url , File file) throws HttpException{
		CloseableHttpClient httpclient = createClientDefault(url);
		//HttpHost proxy = new HttpHost("172.32.100.81", 8080, "http");  
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(30000)
				.setSocketTimeout(30000)
				.setConnectionRequestTimeout(30000)
			//	.setProxy(proxy)
				.build();

		HttpPost post = new HttpPost(url);
		post.setConfig(config);
		CloseableHttpResponse response = null;
		
		try {
			HttpEntity reqEntity = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE).addBinaryBody("media", file, ContentType.MULTIPART_FORM_DATA, file.getName()).build();
			post.setEntity(reqEntity);
			response = httpclient.execute(post);
		    HttpEntity entity = response.getEntity();
		    if (entity != null) {
		       String str = EntityUtils.toString(entity, "UTF-8");
		       System.out.println(str);
		       return str;
		    }
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(response!=null){
		    		response.close();
		    	}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 上传资源接口
	 * @param url
	 * @param path
	 * @return
	 */
	public static String download(File file,String url) throws HttpException{
		CloseableHttpClient httpclient = createClientDefault(url);
		RequestConfig config = RequestConfig.custom()
				.build();

		HttpGet post = new HttpGet(url);
		post.setConfig(config);
		CloseableHttpResponse response = null;
		BufferedOutputStream bw = null;
		try {
			response = httpclient.execute(post);
		    HttpEntity entity = response.getEntity();
		    
		    if (entity != null) {
		    	InputStream is = entity.getContent();
		    	if (!file.getParentFile().exists()){
		    		file.getParentFile().mkdirs();  
		    	}
		    	bw = new BufferedOutputStream(new FileOutputStream(file));  
		    	Streams.copy(is, bw, true);
		    }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    try {
		    	if(response!=null){
		    		response.close();
		    	}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static CloseableHttpClient createClientDefault(String url,String keyStorePath,String pwd){
		
		CloseableHttpClient httpClient = null;
		if(!StringUtils.startsWith(url, "https")){
			httpClient = HttpClients.createDefault();
			return httpClient;
		}
		try {
			
			SSLContext sslContext = null;
			KeyStore keyStore  = null;
			if(StringUtils.isNotBlank(keyStorePath)){//需要证书
				keyStore = KeyStore.getInstance("PKCS12");
				FileInputStream instream = null;
		        try {
		        	instream = new FileInputStream(new File(keyStorePath));
		            keyStore.load(instream, pwd.toCharArray());
		            sslContext = SSLContexts.custom().loadKeyMaterial(keyStore,pwd.toCharArray()).build();
		        } catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (CertificateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (UnrecoverableKeyException e) {
					e.printStackTrace();
				} finally {
		        	if(instream!=null){
		        		try {
							instream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
		        		instream = null;
		        	}
		        }
			}else{
				 sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
		                //信任所有
		                public boolean isTrusted(X509Certificate[] chain,
		                                String authType) throws CertificateException {
		                    return true;
		                }
		            }).build();
			}
	 
           
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            return HttpClients.custom().setHostnameVerifier(new AllowAllHostnameVerifier()).setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return  HttpClients.createDefault();
	}
	
	public static CloseableHttpClient createClientDefault(String url){
		return createClientDefault(url,null,null);
	}
	
	public static void main(String[] args) throws HttpException {
		String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQE67zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL1NVaXp1djNtSElSbGZVZmh5R1RfAAIEdcpeVQMEAAAAAA==";
		File file = new File("C:/test/url.png");
		HttpClientHelper.download(file, url);
	}
}
