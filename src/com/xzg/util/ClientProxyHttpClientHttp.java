package com.xzg.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.xzg.swing.Main;

public class ClientProxyHttpClientHttp {
	// 代理服务器
	//final static String proxyHost = "tunnel.qingtingip.com";
	//final static Integer proxyPort = 8080;

	private static HttpHost proxy = null;

	private static RequestConfig reqConfig = null;

	static {
		proxy = new HttpHost(Main.addr, Main.prot, "http");
		reqConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(10000) // 设置连接超时时间
				.setSocketTimeout(10000) // 设置读取超时时间
				.setExpectContinueEnabled(false).setProxy(proxy)
				.setCircularRedirectsAllowed(true) // 允许多次重定向
				.build();
	}

	public static void main(String[] args) {
		//doGetRequest();
		String param = "responsible_id=1&store_id=0&token=ce6c922e7565cb6351b79288840b6fda9ae9688a&hehe_show=417****5ok6766&user_id=390076";
		String result = doPostRequest("http://zqdn.781z.cn/api/user/getmrpeanutlist",TT.setConfig(param));
		System.out.println(result);
	}

	public static void doGetRequest() {
		// 目标地址
		String targetUrl = "http://httpbin.org/get";

		try {
			// 设置url参数 (可选)
			Map<String, String> urlParams = new HashMap<>();
			urlParams.put("uid", "1234567");
			String paramStr = EntityUtils.toString(new UrlEncodedFormEntity(paramsAdapter(urlParams), "UTF-8"));
			HttpGet httpGet = new HttpGet(targetUrl + "?" + paramStr);
			String result = doRequest(httpGet);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String doPostRequest(String url,Map<String,String> formParams) {
		String result = null;
		try {
			// 要访问的目标页面
			HttpPost httpPost = new HttpPost(url);
			
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(paramsAdapter(formParams), "utf-8");
			httpPost.setEntity(uefEntity);
			result = doRequest(httpPost);
			//System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * 设置请求头
	 * 
	 * @param httpReq
	 */
	private static void setHeaders(HttpRequestBase httpReq) {
		httpReq.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36");
		//httpReq.addHeader("xxx", "xxx");
	}

	/**
	 * 执行请求
	 * 
	 * @param httpReq
	 * @return
	 */
	public static String doRequest(HttpRequestBase httpReq) {
		String result = new String();
		httpReq.setConfig(reqConfig);
		try {
			// 设置请求头
			setHeaders(httpReq);
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			// 执行请求
			CloseableHttpResponse httpResp = httpClient.execute(httpReq);
			
			// 保存Cookie
			
			// 获取http code
			int statusCode = httpResp.getStatusLine().getStatusCode();
			System.out.println(statusCode);
			
			HttpEntity entity = httpResp.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}

			httpResp.close();
			httpClient.close();
			httpReq.abort();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * 参数适配器，将系统定义的请求参数转换成HttpClient能够接受的参数类型
	 * 
	 * @param params
	 *            系统定义的请求参数
	 * @return HttpClient要求的参数类型
	 */
	private static List<NameValuePair> paramsAdapter(Map<String, String> map) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		for (Entry<String, String> entry : map.entrySet()) {
			nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return nvps;
	}

}