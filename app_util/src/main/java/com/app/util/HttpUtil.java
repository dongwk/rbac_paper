package com.app.util;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {

	private final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
    private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static SSLConnectionSocketFactory sslsf = null;
    private static PoolingHttpClientConnectionManager cm = null;
    private static SSLContextBuilder builder = null;
    static {
        try {
            builder = new SSLContextBuilder();
            // 全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            });
            sslsf = new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register(HTTP, new PlainConnectionSocketFactory())
                    .register(HTTPS, sslsf)
                    .build();
            cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(200);//max connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private static HttpClient getHtttpClient(){
		HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(cm).build();
		return httpClient;
	}
	public static String doJsonPost(String url, Map<String, Object> request, Map<String, String> header) {
		return doJsonPost(url, JsonUtil.toJson(request), header);
	}
	
	public static String doJsonPost(String url, Map<String, Object> request) {
		return doJsonPost(url, JsonUtil.toJson(request), null);
	}
	
	public static String doJsonPost(String url, String request) {
		return doJsonPost(url, request, null);
	}

	public static String doJsonPost(String url, String request, Map<String, String> header) {
		HttpClient httpClient = null;
		httpClient = getHtttpClient();
		HttpPost post = new HttpPost(url);
		HttpResponse response = null;
		if (header != null){
			for (Entry<String, String> entry:header.entrySet()) {
				post.setHeader(entry.getKey(), entry.getValue());				
			}
		}
		try {
			logger.info("Resquest start -------------->");
			logger.info("Resquest url: "+url);
			logger.info("Resquest content: "+request);
			StringEntity se = new StringEntity(StringUtils.defaultString(request), "UTF-8");
			se.setContentType("application/json");
			post.setEntity(se);
			response = httpClient.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(response.getEntity(), "UTF-8");
				logger.info("Response content: \n" + result);  
				return result;
			}else{
				logger.info("Resquest : "+response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Resquest exception: "+ExceptionUtils.getStackTrace(e));
		}

		return null;
	}
	
	public static String doGetObj(String url, Map<String, Object> params, Map<String, String> header) {
		return doRequset("Get", url, toStrMap(params), header);
	}
	
	public static String doGetObj(String url, Map<String, Object> params) {
		return doRequset("Get", url, toStrMap(params));
	}
	
	public static String doPostObj(String url, Map<String, Object> params) {
		return doRequset("Post", url, toStrMap(params));
	}
	
	public static String doGet(String url, Map<String, String> params) {
		return doRequset("Get", url, params);
	}
	
	public static String doPost(String url, Map<String, String> params) {
		return doRequset("Post", url, params);
	}
	
	public static String doRequset(String method, String url, Map<String, String> params) {
		return doRequset(method, url, params, null);
	}
	
	public static String doRequset(String method, String url, Map<String, String> params, Map<String, String> header) {
		HttpClient httpClient = getHtttpClient();
		HttpResponse response = null;
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Entry<String, String> entry : params.entrySet()) {
			if (entry.getKey() != null) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
			}
		}
		
		try {
			HttpRequestBase httpRequestBase = null;
			if ("Post".equals(method)){
				HttpPost httpPost = new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
				httpRequestBase = httpPost;
			}else if("Get".equals(method)){
	            url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(nvps, "UTF-8"));
				HttpGet httpGet = new HttpGet(url);
				httpRequestBase = httpGet;
			}

			if (header != null){
				for (Entry<String, String> entry:header.entrySet()) {
					httpRequestBase.setHeader(entry.getKey(), entry.getValue());				
				}
			}

			response = httpClient.execute(httpRequestBase);
			
			if (response.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(response.getEntity(), "UTF-8");
				logger.info("Response content: \n" + result);  
				return result;
			}else{
				logger.info("Resquest : "+response);
			}
		} catch (IOException e) {
			
		} 

		return null;
	}

	public static HttpResponse doPost(String url, String request) {
		HttpClient httpClient = getHtttpClient();
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "text/xml;charset=UTF-8");
		HttpResponse response = null;
		try {
			StringEntity se = new StringEntity(request, "UTF-8");
			se.setContentType("text/xml;charset=UTF-8");
			se.setContentEncoding("UTF-8");
			post.setEntity(se);
			response = httpClient.execute(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public static String doPostXml(String url, String request) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		HttpResponse response = null;
		try {
			StringEntity se = new StringEntity(request, "GBK");
			se.setContentType("text/xml;charset=GBK");
			se.setContentEncoding("GBK");
			post.setEntity(se);
			response = httpClient.execute(post);
			if(response != null && response.getStatusLine().getStatusCode() == 200){
				String responseXml = EntityUtils.toString(response.getEntity(), "GBK");
				return responseXml;
			}else{
				logger.error("doPostXml not 200:"+response.getStatusLine().getStatusCode()+"\n"+response+"\n"+EntityUtils.toString(response.getEntity(), "utf-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("doPostXml error:"+ExceptionUtils.getStackTrace(e));
		} finally {
			try {
				if (response != null && response.getEntity() != null && response.getEntity().getContent() != null){
					response.getEntity().getContent().close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static HttpResponse doGet(String url) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("Content-Type", "text/xml;charset=UTF-8");
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpget);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public static String doStringGet(String url) {
		String result = "";
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("Content-Type", "text/xml;charset=UTF-8");
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpget);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static Map<String, String> toStrMap(Map<String, Object> map){
		if (map == null) return null;
		Map<String, String> toMap = new LinkedHashMap<>(map.size());
		for (Entry<String, Object> entry : map.entrySet()) {
			toMap.put(entry.getKey(), entry.getValue() != null ? entry.getValue().toString():null);
		}
		return toMap;
	}
}
