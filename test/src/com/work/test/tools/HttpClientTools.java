package com.work.test.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * HTTP工具箱 
 * 
 * @author leizhimin 2009-6-19 16:36:18
 */
public final class HttpClientTools {
	private static Log log = LogFactory.getLog(HttpClientTools.class);

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String urlNameString = url + "?" + param;
		String result = sendGet(urlNameString);
		return result;
	}

	public static String sendGet(String urlNameString) {
		String result = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		// FIXME 有缺陷，post请求时，如果request又要获取parameter又要获取post的流数据，会出现拿不到数据的情况
		// 建议改用下面的doPost方法，或使用HttpUtils类
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的HTML
	 */
	public static String doPost(String url, Map<String, String> params) {
		String response = null;
		HttpClient client = new HttpClient();
		HttpMethod method = new PostMethod(url);
		// 设置Http Post数据
		if (params != null) {
			HttpMethodParams p = new HttpMethodParams();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				p.setParameter(entry.getKey(), entry.getValue());
			}
			method.setParams(p);
		}
		try {
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				response = method.getResponseBodyAsString();
			}
		} catch (IOException e) {
			log.error("执行HTTP Post请求" + url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
		}

		return response;
	}

	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的HTML
	 */
	public static String doPost(String url, String params) {
		String response = null;
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		try {
			// 设置Http Post数据
			if (StringUtils.isNotEmpty(params)) {
				RequestEntity entity = new StringRequestEntity(params, "text/xml", "iso-8859-1");  
				method.setRequestEntity(entity);  
			}
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				response = method.getResponseBodyAsString();
			}
		} catch (IOException e) {
			log.error("执行HTTP Post请求" + url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
		}
		
		return response;
	}
	
	public static void main(String[] args) {

		// // 如果不设置，只要代理IP和代理端口正确,此项不设置也可以
		// System.getProperties().setProperty("http.proxyHost", "10.22.40.32");
		// System.getProperties().setProperty("http.proxyPort", "8080");
		// // 判断代理是否设置成功
		// // 发送 GET 请求
		// System.out.println(sendGet("http://www.baidu.com",
		// "param1=xxx&param2=yyy"));
		// 发送 POST 请求
		String url = "http://localhost:8080/momr/third/chinaMobileMM/notifyReceive";
		// String url =
		// "http://pay.ireader.com:32000/momr/third/chinaMobileMM/notifyReceive";
		String data = "<?xml version=\"1.0\" encoding=\"GBK\"?><ServiceWebTransfer2APReq><APTransactionID>1512446323</APTransactionID><APId>10959</APId><ServiceId>20472</ServiceId><ServiceType>32</ServiceType><ChannelId>10960</ChannelId><APContentId>ireader</APContentId><APUserId>MTIzNDU2</APUserId><OrderType>0</OrderType><Actiontime>2014-06-23 11:45:03</Actiontime><ServiceAction>0</ServiceAction><method /><signMethod>DSA</signMethod><sign>MC0CFGQhO9W781kv75ppz5mxexHUx7vmAhUAlJYzqZzqAoZ6BRomlflQrQJjWlw=</sign><Msisdn>23412056497</Msisdn><Province>10</Province><Backup1 /><Backup2 /></ServiceWebTransfer2APReq>";
		String xmlString = "<?xml version=\"1.0\" encoding=\"GBK\"?><VertifyUserState2APReq><APTransactionID>1512446319</APTransactionID><APId>10959</APId><ServiceId>20472</ServiceId><ServiceType>32</ServiceType><ChannelId>10960</ChannelId><APContentId>ireader</APContentId><APUserId>MTIzNDU2</APUserId><OrderType>0</OrderType><Actiontime>2014-06-20 17:39:39</Actiontime><method /><signMethod>DSA</signMethod><sign>MCwCFB1SmfqzurFZ5ZZUWWaXVV2DJwpeAhQvyBTWCN8Uj4/VJC2ueYecA7rFmg==</sign><Msisdn>23412056497</Msisdn><Province>10</Province><Backup1 /><Backup2 /></VertifyUserState2APReq>";

		String x = sendPost(url, data);
		System.out.println(x);
	}

}