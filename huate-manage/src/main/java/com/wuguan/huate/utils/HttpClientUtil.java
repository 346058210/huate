package com.wuguan.huate.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;
public class HttpClientUtil {

	public static String doPost(String url, Map<String, String> headers, Map<String, Object> params) {
		String result = null;
		CloseableHttpResponse response = null;
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		if (headers != null) {
			for (Entry<String, String> header : headers.entrySet()) {
				post.setHeader(header.getKey(), header.getValue());

			}
		}
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000).setConnectionRequestTimeout(1000).setSocketTimeout(10000).setRedirectsEnabled(true).build();
		post.setConfig(requestConfig);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		post.setHeader("accept-encoding", "gzip, deflate");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (params != null) {
			for (Entry<String, Object> param : params.entrySet()) {
				nvps.add(new BasicNameValuePair(param.getKey(), (String) param.getValue()));
			}
		}
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps,Consts.UTF_8));
			response = client.execute(post);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (client != null) {
					client.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String doGet(String url, Map<String, String> headers, Map<String, Object> params) {
		String result = null;
		String pm = null;
		CloseableHttpResponse response = null;
		HttpGet get;
		CloseableHttpClient client = HttpClientBuilder.create().build();
		StringBuffer sb = new StringBuffer();
		if (params != null) {
			sb.append("?");
			for (Entry<String, Object> param : params.entrySet()) {
				sb.append(param.getKey() + "=" + param.getValue() + "&");
			}
			pm = sb.substring(0, sb.length() - 1);
			get = new HttpGet(url + pm);
		}else {
			get = new HttpGet(url);
		}
		if (headers != null) {
			for (Entry<String, String> header : headers.entrySet()) {
				get.setHeader(header.getKey(), header.getValue());

			}
		}
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000).setConnectionRequestTimeout(1000).setSocketTimeout(10000).setRedirectsEnabled(true).build();
		get.setConfig(requestConfig);
		get.setHeader("Content-Type", "application/json;charset=utf8");

		try {
			response = client.execute(get);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (client != null) {
					client.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 
	* @Title: httpRequest
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param requestUrl 請求地址
	* @param requestMethod 請求方法 post|get
	* @param data 請求參數
	* @return
	 */
	public static String httpRequest(String requestUrl,String requestMethod,String param) {
		StringBuffer buffer=null;
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection connection= (HttpsURLConnection)url.openConnection();
			connection.setRequestMethod(requestMethod);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.connect();
			//向服務器寫內容
			if (null!=param) {
				OutputStream outputStream = connection.getOutputStream();
				outputStream.write(param.getBytes("utf-8"));
				outputStream.close();
			}
			//讀取服務端返回內容
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream, "utf-8");
			BufferedReader buffered = new BufferedReader(reader);
			buffer=new StringBuffer();
			String line=null;
			while ((line = buffered.readLine()) != null) {
				buffer.append(line);
			}
			buffered.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	
	
	
	/**
	 * 
	* @Title: getIpAddr
	* @Description: 獲取IP
	* @param request
	* @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isEmpty(ip)&&!"unKnown".equalsIgnoreCase(ip)) {
			int index = ip.indexOf(",");
			if (index!=-1) {
				return ip.substring(0,index);
			}else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isEmpty(ip)&&!"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
	
    
}
