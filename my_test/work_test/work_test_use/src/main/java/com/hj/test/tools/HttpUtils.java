package com.hj.test.tools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.NumberUtils;

/**
 * <p>
 * 文件名称: HttpUtils.java
 * </p>
 * <p>
 * 文件描述: HTTP请求工具类
 * </p>
 * <p>
 * 版权所有: 版权所有(C)2014-2020
 * </p>
 * <p>
 * 公 司: 北京掌中浩阅科技有限公司
 * </p>
 * <p>
 * 内容摘要:
 * </p>
 * <p>
 * 其他说明:
 * </p>
 * <p>
 * 完成日期：
 * </p>
 * <p>
 * 修改记录:
 * </p>
 * 
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * 
 * @author Created by WangYu on 14-7-25
 * @version 1.0
 */
public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    // 跳过证书验证重写
    private static TrustManager truseAllManager = new X509TrustManager() {

        public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
            throws CertificateException {
            // 跳过验证，这里什么都不做
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
            throws CertificateException {
            // 跳过验证，这里什么都不做
        }

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            // 跳过验证，这里什么都不做
            return null;
        }
    };

    /**
     * 普通的HTTP请求（不使用代理，默认UTF-8编码）
     * 
     * @param url HTTP请求地址
     * @return HTTP应答内容
     */
    public static String httpGet(String url) {
        int outTime = -1;
        return httpGet(url, null, HttpConstant.DEFAULT_CHARSET, false, outTime);
    }

    /**
     * 普通的HTTPS GET请求（不使用代理，默认UTF-8编码）
     * 
     * @param url HTTPS 请求地址
     * @return HTTPS 应答内容
     */
    public static String httpsGet(String url) {
        int outTime = -1;
        return httpsGet(url, null, HttpConstant.DEFAULT_CHARSET, false, outTime);
    }

    /**
     * HTTP 请求使用系统配置的代理，请求超时时间默认30秒，默认字符集编码:UTF-8
     * 
     * @param url 请求地址
     * @param params 参数
     * @return 应答内容
     */
    public static String httpGetWithProxy(String url, Map<String, String> params) {
        // outTime 超时时间设置为小于0时，使用系统默认的超时时间：30秒
        int outTime = -1;
        return httpGet(url, params, HttpConstant.DEFAULT_CHARSET, true, outTime);
    }

    /**
     * HTTP 请求不使用代理，请求超时时间默认30秒，默认字符集编码:UTF-8
     * 
     * @param url 请求地址
     * @param params 参数
     * @return 应答内容
     */
    public static String httpGetWithOutProxy(String url, Map<String, String> params) {
        // outTime 超时时间设置为小于0时，使用系统默认的超时时间：30秒
        int outTime = -1;
        return httpGet(url, params, HttpConstant.DEFAULT_CHARSET, false, outTime);
    }

    /**
     * HTTP 请求使用系统配置的代理，请求超时时间默认30秒，默认字符集编码:UTF-8
     * 
     * @param url 请求地址
     * @param params 参数
     * @param <T> 泛型
     * @return 应答内容
     */
    public static <T> String httpPostWithProxy(String url, T params) {
        // outTime 超时时间设置为小于0时，使用系统默认的超时时间：30秒
        int outTime = -1;
        return httpPost(url, params, HttpConstant.DEFAULT_CHARSET, true, outTime);
    }

    /**
     * HTTP 请求不使用代理，请求超时时间默认30秒，默认字符集编码:UTF-8
     * 
     * @param url 请求地址
     * @param params 参数
     * @param <T> 泛型
     * @return 应答内容
     */
    public static <T> String httpPostWithOutProxy(String url, T params) {
        // outTime 超时时间设置为小于0时，使用系统默认的超时时间：30秒
        int outTime = -1;
        return httpPost(url, params, HttpConstant.DEFAULT_CHARSET, false, outTime);
    }

    /**
     * HTTP get请求
     * 
     * @param url 请求地址
     * @param params 请求参数
     * @param charSet 字符集编码
     * @param isProxy 是否使用代理，true 使用系统配置的默认代理，false 不使用代理
     * @return http响应信息
     */
    public static String httpGet(String url, Map<String, String> params, String charSet, boolean isProxy, int outTime) {
        if (isProxy) {
            // 使用系统配置的默认代理
            return httpGet(url, params, charSet, null, -1, outTime);
        } else {
            // 不使用代理，设置IP为127.0.0.1，端口大于0
            return httpGet(url, params, charSet, HttpConstant.LOCALHOST, 1, outTime);
        }
    }

    /**
     * HTTPS get请求（无证书验证）
     * 
     * @param url 请求地址
     * @param params 参数（MAP或者String字符串）
     * @param charSet 字符
     * @param isProxy 是否使用代理
     * @return HTTPS 请求应答内容
     */
    public static String httpsGet(String url, Map<String, String> params, String charSet, boolean isProxy, int outTime) {
        if (isProxy) {
            // 使用系统配置的默认代理
            return httpsGet(url, params, charSet, null, -1, outTime);
        } else {
            // 不使用代理，设置IP为127.0.0.1，端口大于0
            return httpsGet(url, params, charSet, HttpConstant.LOCALHOST, 1, outTime);
        }
    }

    /**
     * HTTP POST
     * 
     * @param url 请求地址
     * @param params 参数
     * @param charSet 字符集
     * @param isProxy 是否使用代理，true 使用系统配置的默认代理，false 不使用代理
     * @param <T> 泛型（字符串参数或者Map<String,String>）
     * @return POST 应答内容
     */
    public static <T> String httpPost(String url, T params, String charSet, boolean isProxy, int outTime) {
        if (isProxy) {
            // 使用系统配置的默认代理
            return httpPost(url, params, charSet, null, -1, outTime);
        } else {
            // 不使用代理，设置IP为127.0.0.1，端口大于0
            return httpPost(url, params, charSet, HttpConstant.LOCALHOST, 1, outTime);
        }
    }

    /**
     * HTTPS POST
     * 
     * @param url 请求地址
     * @param params 参数
     * @param charSet 字符集
     * @param isProxy 是否使用代理，true 使用系统配置的默认代理，false 不使用代理
     * @param <T> 泛型（字符串或者Map<String,String>）
     * @return 应答内容
     */
    public static <T> String httpsPost(String url, T params, String charSet, boolean isProxy, int outTime) {
        if (isProxy) {
            // 使用系统配置的默认代理
            return httpsPost(url, params, charSet, null, -1, outTime);
        } else {
            // 不使用代理，设置IP为127.0.0.1，端口大于0
            return httpsPost(url, params, charSet, HttpConstant.LOCALHOST, 1, outTime);
        }
    }

    /**
     * get请求方法base
     * 
     * @param url 请求地址
     * @param params 参数列表
     * @param charSet 字符集编码
     * @param proxyHost 代理服务器IP
     * @param proxyPort 代理服务器端口
     * @return HTTP应答内容
     */
    public static String httpGet(String url, Map<String, String> params, String charSet, String proxyHost,
        int proxyPort, int outTime) {
        // 返回内容
        String resContent = "";
        CloseableHttpResponse response = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 构建HTTP请求参数------------------------------------------------------------------------------>>
        RequestConfig.Builder builder = RequestConfig.custom();
        if (outTime < 0) {
            builder.setConnectTimeout(HttpConstant.DEFAULT_CONNECT_TIMEOUT_TIME);
            builder.setConnectionRequestTimeout(HttpConstant.DEFAULT_CONNECTION_REQUESTTIMEOUT_TIME);
            builder.setSocketTimeout(HttpConstant.DEFAULT_SOCKET_OUTTIME);
        } else {
            builder.setConnectTimeout(outTime);
            builder.setConnectionRequestTimeout(outTime);
            builder.setSocketTimeout(outTime);
        }

        // 设置代理服务器
        HttpHost proxy = getProxy(proxyHost, proxyPort);

        builder.setProxy(proxy);

        RequestConfig config = builder.build();
        HttpGet httpGet = new HttpGet(buildGetParams(url, params, charSet));
        // 设置HTTP请求参数
        httpGet.setConfig(config);
        HttpEntity entity = null;
        try {
            // 设置HTTPHeader 的请求地址，有的需要验证访问地址
            httpGet.addHeader("X-Forwarded-For", InetAddress.getLocalHost().getHostAddress());
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            if (StringUtils.isEmpty(charSet)) {
                resContent = EntityUtils.toString(entity, HttpConstant.DEFAULT_CHARSET);
            } else {
                resContent = EntityUtils.toString(entity, charSet);
            }
        } catch (IOException e) {
            logger.error("http doGet error!");
            e.printStackTrace();
        } finally {
            try {
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                logger.error("http close error:" + e, e);
            }
        }
        return resContent;
    }

    /**
     * get请求方法base--head参数、cookie
     * @param url 请求地址
     * @param params 参数列表
     * @param charSet 字符集编码
     * @param proxyHost 代理服务器IP
     * @param proxyPort 代理服务器端口
     * @param otherParams 需要设置的其他参数
     * @return HTTP应答内容
     */
    public static String httpGet(String url, Map<String, String> params, String charSet, String proxyHost,
        int proxyPort, int outTime, Map<String, String> otherParams) {
        // 返回内容
        String resContent = "";
        CloseableHttpResponse response = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 构建HTTP请求参数------------------------------------------------------------------------------>>
        RequestConfig.Builder builder = RequestConfig.custom();
        if (outTime < 0) {
            builder.setConnectTimeout(HttpConstant.DEFAULT_CONNECT_TIMEOUT_TIME);
            builder.setConnectionRequestTimeout(HttpConstant.DEFAULT_CONNECTION_REQUESTTIMEOUT_TIME);
            builder.setSocketTimeout(HttpConstant.DEFAULT_SOCKET_OUTTIME);
        } else {
            builder.setConnectTimeout(outTime);
            builder.setConnectionRequestTimeout(outTime);
            builder.setSocketTimeout(outTime);
        }

        // 设置代理服务器
        HttpHost proxy = getProxy(proxyHost, proxyPort);

        builder.setProxy(proxy);

        RequestConfig config = builder.build();
        HttpGet httpGet = new HttpGet(buildGetParams(url, params, charSet));
        // 设置HTTP请求参数
        httpGet.setConfig(config);
        HttpEntity entity = null;
        try {
            // 设置HTTPHeader 的请求地址，有的需要验证访问地址
            httpGet.addHeader("X-Forwarded-For", InetAddress.getLocalHost().getHostAddress());
            if (MapUtils.isNotEmpty(otherParams) && otherParams.get("Cookie") != null) {
                httpClient = resolveCookieRejected();
                httpGet.addHeader(new BasicHeader("Cookie", otherParams.get("Cookie")));
            } else if (MapUtils.isNotEmpty(otherParams)) {
                for (String key : otherParams.keySet()) {
                    if (!"Cookie".equals(key)) {
                        httpGet.addHeader(key, otherParams.get(key));
                    }
                }
            }
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            if (StringUtils.isEmpty(charSet)) {
                resContent = EntityUtils.toString(entity, HttpConstant.DEFAULT_CHARSET);
            } else {
                resContent = EntityUtils.toString(entity, charSet);
            }
        } catch (IOException e) {
            logger.error("http doGet error!");
            e.printStackTrace();
        } finally {
            try {
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                logger.error("http close error:" + e, e);
            }
        }
        return resContent;
    }

    /**
     * 解决cookie rejected 问题
     * @author huji
     * @return
     */
    private static CloseableHttpClient resolveCookieRejected() {
        CloseableHttpClient httpClient;
        /*解决cookie rejected 问题--begin*/
        CookieSpecFactory csf = new CookieSpecFactory() {
            public CookieSpec newInstance(HttpParams params) {
                return new BrowserCompatSpec() {
                    @Override
                    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
                        // Oh, I am easy
                    }
                };
            }
        };
        httpClient = new DefaultHttpClient();
        ((AbstractHttpClient) httpClient).getCookieSpecs().register("easy", csf);
        httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, "easy");
//        DefaultHttpParams.getDefaultParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
        /*解决cookie rejected 问题--end*/
        return httpClient;
    }

    /**
     * post请求方法base
     * @author huji
     * @param url
     * @param params
     * @param charSet
     * @param proxyHost
     * @param proxyPort
     * @param outTime
     * @return
     */
    public static <T> String httpPost(String url, T params, String charSet, String proxyHost, int proxyPort, int outTime) {
        String resContent = "";
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 构建HTTP请求参数------------------------------------------------------------------------------>>
        RequestConfig.Builder builder = RequestConfig.custom();
        if (outTime < 0) {
            builder.setConnectTimeout(HttpConstant.DEFAULT_CONNECT_TIMEOUT_TIME);
            builder.setConnectionRequestTimeout(HttpConstant.DEFAULT_CONNECTION_REQUESTTIMEOUT_TIME);
            builder.setSocketTimeout(HttpConstant.DEFAULT_SOCKET_OUTTIME);
        } else {
            builder.setConnectTimeout(outTime);
            builder.setConnectionRequestTimeout(outTime);
            builder.setSocketTimeout(outTime);
        }

        // 设置代理服务器
        HttpHost proxy = getProxy(proxyHost, proxyPort);
        builder.setProxy(proxy);
        RequestConfig config = builder.build();

        // 目标地址
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);
        HttpEntity entity = null;
        try {
            httpPost.addHeader("X-Forwarded-For", InetAddress.getLocalHost().getHostAddress());
            HttpEntity he = getHttpEntity(params, charSet);
            httpPost.setEntity(he);
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            // 显示结果
            if (StringUtils.isNotEmpty(charSet)) {
                resContent = EntityUtils.toString(response.getEntity(), charSet);
            } else {
                resContent = EntityUtils.toString(response.getEntity(), HttpConstant.DEFAULT_CHARSET);
            }
        } catch (Exception e) {
            logger.error("http doPost error!");
            e.printStackTrace();
        } finally {
            try {
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                logger.error("http close error:" + e, e);
            }

        }
        return resContent;
    }
    
    /**
     * post请求方法base--head参数、cookie
     * @author huji
     * @param url
     * @param params
     * @param charSet
     * @param proxyHost
     * @param proxyPort
     * @param outTime
     * @param otherParams
     * @return
     */
    public static <T> String httpPost(String url, T params, String charSet, String proxyHost, int proxyPort, int outTime,Map<String, String> otherParams) {
        String resContent = "";
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        
        // 构建HTTP请求参数------------------------------------------------------------------------------>>
        RequestConfig.Builder builder = RequestConfig.custom();
        if (outTime < 0) {
            builder.setConnectTimeout(HttpConstant.DEFAULT_CONNECT_TIMEOUT_TIME);
            builder.setConnectionRequestTimeout(HttpConstant.DEFAULT_CONNECTION_REQUESTTIMEOUT_TIME);
            builder.setSocketTimeout(HttpConstant.DEFAULT_SOCKET_OUTTIME);
        } else {
            builder.setConnectTimeout(outTime);
            builder.setConnectionRequestTimeout(outTime);
            builder.setSocketTimeout(outTime);
        }
        
        // 设置代理服务器
        HttpHost proxy = getProxy(proxyHost, proxyPort);
        builder.setProxy(proxy);
        RequestConfig config = builder.build();
        
        // 目标地址
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);
        HttpEntity entity = null;
        try {
            httpPost.addHeader("X-Forwarded-For", InetAddress.getLocalHost().getHostAddress());
            if (MapUtils.isNotEmpty(otherParams) && otherParams.get("Cookie") != null) {
                httpClient = resolveCookieRejected();
                httpPost.addHeader(new BasicHeader("Cookie", otherParams.get("Cookie")));
            } else if (MapUtils.isNotEmpty(otherParams)) {
                for (String key : otherParams.keySet()) {
                    httpPost.addHeader(key, otherParams.get(key));
                }
            }
            HttpEntity he = getHttpEntity(params, charSet);
            httpPost.setEntity(he);
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            // 显示结果
            if (StringUtils.isNotEmpty(charSet)) {
                resContent = EntityUtils.toString(response.getEntity(), charSet);
            } else {
                resContent = EntityUtils.toString(response.getEntity(), HttpConstant.DEFAULT_CHARSET);
            }
        } catch (Exception e) {
            logger.error("http doPost error!");
            e.printStackTrace();
        } finally {
            try {
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                logger.error("http close error:" + e, e);
            }
            
        }
        return resContent;
    }

    /**
     * HTTPS POST 方法
     * 
     * @param url 请求地址
     * @param params 参数
     * @param charSet 字符集
     * @param proxyHost 代理主机IP
     * @param proxyPort 代理端口
     * @param outTime 超时时间
     * @param <T> 泛型:字符串或者Map<String,String>
     * @return 应答内容
     */
    public static <T> String httpsPost(String url, T params, String charSet, String proxyHost, int proxyPort,
        int outTime) {
        String resContent = "";
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = getSslHttpClient();

        // 构建HTTP请求参数------------------------------------------------------------------------------>>
        RequestConfig.Builder builder = RequestConfig.custom();
        if (outTime < 0) {
            builder.setConnectTimeout(HttpConstant.DEFAULT_CONNECT_TIMEOUT_TIME);
            builder.setConnectionRequestTimeout(HttpConstant.DEFAULT_CONNECTION_REQUESTTIMEOUT_TIME);
            builder.setSocketTimeout(HttpConstant.DEFAULT_SOCKET_OUTTIME);
        } else {
            builder.setConnectTimeout(outTime);
            builder.setConnectionRequestTimeout(outTime);
            builder.setSocketTimeout(outTime);
        }

        // 设置代理服务器
        HttpHost proxy = getProxy(proxyHost, proxyPort);
        builder.setProxy(proxy);
        RequestConfig config = builder.build();

        // 目标地址
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);
        HttpEntity entity = null;
        try {
            httpPost.addHeader("X-Forwarded-For", InetAddress.getLocalHost().getHostAddress());
            HttpEntity he = getHttpEntity(params, charSet);
            httpPost.setEntity(he);
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            // 显示结果
            if (StringUtils.isNotEmpty(charSet)) {
                resContent = EntityUtils.toString(response.getEntity(), charSet);
            } else {
                resContent = EntityUtils.toString(response.getEntity(), HttpConstant.DEFAULT_CHARSET);
            }
        } catch (Exception e) {
            logger.error("http doPost error!");
            e.printStackTrace();
        } finally {
            try {
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                logger.error("http close error:" + e, e);
            }

        }
        return resContent;
    }

    /**
     * 构建HTTP get请求参数
     * 参数拼接顺序无序
     * 
     * @param params HTTP请求参数
     * @return http请求参数拼接字符串
     */
    @Deprecated
    public static String buildHttpGetParams(String url, Map<String, String> params) {
        StringBuilder condition = new StringBuilder(url);
        if (params != null && !params.isEmpty()) {
            condition.append(HttpConstant.URL_SUFFIX);
            for (Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator(); iterator.hasNext();) {
                Map.Entry<String, String> element = iterator.next();
                condition.append(String.valueOf(element.getKey()));
                condition.append("=");
                condition.append(element.getValue());
                if (iterator.hasNext()) {
                    condition.append("&");
                }
            }
        }
        return condition.toString();
    }

    /**
     * HTTP GET 参数构建
     * 
     * @param url 请求地址
     * @param params 参数
     * @return 构建参数请求串
     */
    @SuppressWarnings("unchecked")
    public static String buildGetParams(String url, Map<String, String> params, String charSet) {
        StringBuilder buffer = new StringBuilder(url);
        String paramString = "";
        if (params != null) {
            List<NameValuePair> paramPairs = getNameValuePairs(params);
            paramString = URLEncodedUtils.format(paramPairs, charSet);
            if (!url.endsWith(HttpConstant.URL_SUFFIX)) {
                buffer.append(HttpConstant.URL_SUFFIX);
            }
            buffer.append(paramString);
        }
        return buffer.toString();
    }

    /**
     * MAP转换键值对List
     * 
     * @param data 参数
     * @return 键值对list
     */
    private static List<NameValuePair> getNameValuePairs(Map<String, String> data) {
        List<NameValuePair> paramPairs = new ArrayList<NameValuePair>();
        if (data != null && !data.isEmpty()) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                BasicNameValuePair param = new BasicNameValuePair(entry.getKey(), entry.getValue());
                paramPairs.add(param);
            }
        }
        return paramPairs;
    }

    /**
     * httppost 参数构建
     * 
     * @param data 参数
     * @param <T> 泛型（字符串或者Map<String,String>）
     * @return 返回HTTTPEntity
     * @throws UnsupportedEncodingException
     */
    private static <T> HttpEntity getHttpEntity(T data, String charSet) throws UnsupportedEncodingException {
        HttpEntity he = null;
        if (data instanceof String) {
            he = new StringEntity((String) data, charSet);

        } else if (data instanceof Map) {
            List<NameValuePair> paramPairs = getNameValuePairs((Map<String, String>) data);
            he = new UrlEncodedFormEntity(paramPairs, charSet);
        }
        return he;
    }

    /**
     * HTTPS get 请求
     * 
     * @param url 请求地址
     * @param params 参数
     * @param charSet 字符集
     * @param proxyHost 代理主机IP
     * @param proxyPort 代理端口
     * @return HTTPS 应答内容
     */
    public static String httpsGet(String url, Map<String, String> params, String charSet, String proxyHost,
        int proxyPort, int outTime) {
        String resContent = "";

        CloseableHttpClient httpClient = getSslHttpClient();

        // 构建HTTP请求参数------------------------------------------------------------------------------>>
        RequestConfig.Builder builder = RequestConfig.custom();
        if (outTime < 0) {
            builder.setConnectTimeout(HttpConstant.DEFAULT_CONNECT_TIMEOUT_TIME);
            builder.setConnectionRequestTimeout(HttpConstant.DEFAULT_CONNECTION_REQUESTTIMEOUT_TIME);
            builder.setSocketTimeout(HttpConstant.DEFAULT_SOCKET_OUTTIME);
        } else {
            builder.setConnectTimeout(outTime);
            builder.setConnectionRequestTimeout(outTime);
            builder.setSocketTimeout(outTime);
        }

        // 设置代理服务器
        HttpHost proxy = getProxy(proxyHost, proxyPort);

        builder.setProxy(proxy);
        RequestConfig config = builder.build();

        HttpGet httpGet = new HttpGet(buildGetParams(url, params, charSet));
        httpGet.setConfig(config);
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            httpGet.addHeader("X-Forwarded-For", InetAddress.getLocalHost().getHostAddress());
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            if (StringUtils.isNotEmpty(charSet)) {
                resContent = EntityUtils.toString(entity, charSet);
            } else {
                resContent = EntityUtils.toString(entity, HttpConstant.DEFAULT_CHARSET);
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            logger.error("http doGet error!");
            e.printStackTrace();
        } finally {
            try {
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                logger.error("http close error:" + e, e);
            }
        }
        return resContent;
    }

    /**
     * 获得SSL协议
     * 
     * @return SSL协议HTTPCLIENT
     */
    private static CloseableHttpClient getSslHttpClient() {
        // TLS：安全传输层协议
        // TLS：Transport Layer Security
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContext.getInstance("TLS");
            // 初始化，这里使用自定义的truseAllManager（跳过所有验证）
            sslcontext.init(null, new TrustManager[]{truseAllManager}, new SecureRandom());

        } catch (Exception e) {
            logger.error("https doGet TLS init!");
            e.printStackTrace();
        }
        return HttpClients.custom().setSslcontext(sslcontext).build();
    }

    /**
     * 获取代理服务器
     * 
     * @param proxyHost 代理服务器IP
     * @param proxyPort 代理服务器端口
     * @return 返回HTTPHOST实体
     */
    public static HttpHost getProxy(String proxyHost, Integer proxyPort) {
        // 如果IP是空或者端口小于等于0则使用默认代理
        if (StringUtils.isEmpty(proxyHost) || proxyPort <= 0) {
            // 使用默认值
            proxyHost = System.getProperty(ConfigManager.sysKeyProxyHost);
            String port = System.getProperty(ConfigManager.sysKeyProxyPort);
            logger.info("getProxy httpProxyHost={},port={}.", proxyHost, port);
            if (StringUtils.isNotEmpty(proxyHost) && StringUtils.isNotEmpty(port)) {
                proxyPort = NumberUtils.parseNumber(port, Integer.class);
            }
            // 如果IP为本地127.0.0.1则不使用代理，返回代理为空
        } else if (HttpConstant.LOCALHOST.equals(proxyHost)) {
            return null;
        }
        return new HttpHost(proxyHost, proxyPort);
    }

    public class HttpConstant {
        /**
         * 默认字符集
         */
        public static final String DEFAULT_CHARSET = "UTF-8";

        /**
         * 请求参数分隔符
         */
        public static final String URL_SUFFIX = "?";

        /**
         * 本地IP
         */
        public static final String LOCALHOST = "127.0.0.1";

        /**
         * HTTP请求链接超时时间（毫秒）
         */
        public static final int DEFAULT_CONNECT_TIMEOUT_TIME = 30 * 1000;

        /**
         * HTTP请求超时时间（毫秒）
         */
        public static final int DEFAULT_CONNECTION_REQUESTTIMEOUT_TIME = 30 * 1000;

        /**
         * SOCKEY 超时时间
         */
        public static final int DEFAULT_SOCKET_OUTTIME = 30 * 1000;

        /**
         * 默认超时时间（30秒）
         */
        public static final int DEFAULT_OUTTIME = 30 * 1000;
    }

    public class ConfigManager {
        public static final String sysKeyProxyHost = "127.0.0.1";

        public static final String sysKeyProxyPort = "80";
    }
}
