package com.spbt.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * *************************************************************************************
 * 请求发送工具类
 *
 * @author yujialin
 */
public final class HttpRequester {

    private static Logger logger = LoggerFactory.getLogger(HttpRequester.class);

    private static final int DEFAULT_CONNECTIONTIMEOUT = 10000;
    private static final int DEFAULT_READTIMEOUT = 10000;
    private static final int DEFAULT_TIMEOUT = 10000;
    private static final int STATUSCODE = 200;

    private HttpRequester() {

    }

    /**
     * ******************************************
     * 以post方式发送请求
     *
     * @param url
     * @param paramMap
     * @param encode
     * @return
     */
    public static String post(String url, Map<String, String> paramMap, String encode) {

        return post(url, paramMap, encode, DEFAULT_CONNECTIONTIMEOUT, DEFAULT_READTIMEOUT);
    }

    /**
     * ********************************
     *
     * @param url
     * @param paramMap
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String post(String url, Map<String, String> paramMap) {

        return post(url, paramMap, HTTP.UTF_8, DEFAULT_CONNECTIONTIMEOUT, DEFAULT_READTIMEOUT);
    }

    /**
     * ********************************
     *
     * @param url
     * @param paramMap
     * @param cookies
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String post(String url, Map<String, String> paramMap, Map<String, String> cookies) {
        return post(url, paramMap, cookies, HTTP.UTF_8, DEFAULT_CONNECTIONTIMEOUT, DEFAULT_READTIMEOUT);
    }

    /**
     * **********************
     *
     * @param url
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String post(String url) {

        return post(url, null, HTTP.UTF_8, DEFAULT_CONNECTIONTIMEOUT, DEFAULT_READTIMEOUT);
    }

    /**
     * *******************************
     *
     * @param url
     * @param encode
     * @return
     */
    public static String post(String url, String encode) {

        return post(url, null, encode, DEFAULT_CONNECTIONTIMEOUT, DEFAULT_READTIMEOUT);
    }

    /**
     * ********************************
     *
     * @param url
     * @param connectiontimeout
     * @param readtimeout
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String post(String url, int connectiontimeout, int readtimeout) {

        return post(url, null, HTTP.UTF_8, connectiontimeout, readtimeout);
    }

    /**
     * *********************************
     *
     * @param url
     * @param encode
     * @param connectiontimeout
     * @param readtimeout
     * @return
     */
    public static String post(String url, String encode, int connectiontimeout, int readtimeout) {

        return post(url, null, encode, connectiontimeout, readtimeout);
    }

    /**
     * *****************************************
     *
     * @param url
     * @param paramMap
     * @param encode
     * @param connectiontimeout
     * @param readtimeout
     * @return
     */
    public static String post(String url, Map<String, String> paramMap, String encode, int connectiontimeout,
            int readtimeout) {
        String resultStr = null;

        HttpClient httpclient = null;
        try {
            HttpPost httppost = new HttpPost(url);
            if (paramMap != null) {
                // 用于存放请求参数
                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, encode);
                httppost.setEntity(entity);
            }
            httppost.setHeader("Connection", "close");
            httpclient = new DefaultHttpClient();
            HttpParams params = httpclient.getParams();
            HttpConnectionParams.setConnectionTimeout(params, connectiontimeout);
            HttpConnectionParams.setSoTimeout(params, readtimeout);
            // 发送post
            HttpResponse httpResponse = httpclient.execute(httppost);
            // 请求
            if (httpResponse.getStatusLine().getStatusCode() == STATUSCODE) {
                resultStr = EntityUtils.toString(httpResponse.getEntity(), encode);
                if (resultStr != null) {
                    resultStr = resultStr.trim();
                }
                EntityUtils.consume(httpResponse.getEntity());
            } else {
                resultStr = httpResponse.getStatusLine().toString();
                logger.error("请求失败，返回：" + resultStr);
            }
        } catch (Exception e) {
            logger.error("调用http失败", e);
        } finally {
            if (httpclient != null) {
                httpclient.getConnectionManager().shutdown();
            }
        }

        return resultStr;
    }

    /**
     * *****************************************
     *
     * @param url
     * @param paramMap
     * @param encode
     * @param connectiontimeout
     * @param readtimeout
     * @return
     */
    public static String post(String url, Map<String, String> paramMap, Map<String, String> cookies, String encode,
            int connectiontimeout, int readtimeout) {
        String resultStr = null;

        DefaultHttpClient httpclient = null;
        try {
            HttpPost httppost = new HttpPost(url);
            if (paramMap != null) {
                // 用于存放请求参数
                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, encode);
                httppost.setEntity(entity);
            }
            httppost.setHeader("Connection", "close");
            httpclient = new DefaultHttpClient();
            if (cookies != null && !cookies.isEmpty()) {
                CookieStore cookieStore = httpclient.getCookieStore();
                for (Map.Entry<String, String> entry : cookies.entrySet()) {
                    BasicClientCookie cookie = new BasicClientCookie(entry.getKey(), entry.getValue());
                    cookieStore.addCookie(cookie);
                }
                httpclient.setCookieStore(cookieStore);
            }
            HttpParams params = httpclient.getParams();
            HttpConnectionParams.setConnectionTimeout(params, connectiontimeout);
            HttpConnectionParams.setSoTimeout(params, readtimeout);
            // 发送post
            HttpResponse httpResponse = httpclient.execute(httppost);
            // 请求
            if (httpResponse.getStatusLine().getStatusCode() == STATUSCODE) {
                resultStr = EntityUtils.toString(httpResponse.getEntity(), encode);
                if (resultStr != null) {
                    resultStr = resultStr.trim();
                }
                EntityUtils.consume(httpResponse.getEntity());
            } else {
                resultStr = httpResponse.getStatusLine().toString();
                logger.error("请求失败，返回：" + resultStr);
            }
        } catch (Exception e) {
            logger.error("调用http失败", e);
        } finally {
            if (httpclient != null) {
                httpclient.getConnectionManager().shutdown();
            }
        }

        return resultStr;
    }

    /**
     * *************************
     *
     * @param url
     * @param encode
     * @return
     * @Description: get方法
     * @author yujialin
     * @date May 31, 2011 3:47:39 PM
     */
    public static String get(String url, String encode) {
        String resultStr = null;

        HttpClient httpclient = null;
        try {
            HttpGet httpget = new HttpGet(url);
            httpclient = new DefaultHttpClient();
            HttpParams params = httpclient.getParams();
            HttpConnectionParams.setConnectionTimeout(params, DEFAULT_TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, DEFAULT_TIMEOUT);
            // 发送post 请求
            HttpResponse httpResponse = httpclient.execute(httpget);
            if (httpResponse.getStatusLine().getStatusCode() == STATUSCODE) {
                resultStr = EntityUtils.toString(httpResponse.getEntity(), encode);
                if (resultStr != null) {
                    resultStr = resultStr.trim();
                }
                EntityUtils.consume(httpResponse.getEntity());
            } else {
                resultStr = httpResponse.getStatusLine().toString();
                logger.error("请求失败，返回：" + resultStr);
            }
        } catch (Exception e) {
            logger.error("调用http失败", e);
        } finally {
            if (httpclient != null) {
                httpclient.getConnectionManager().shutdown();
            }
        }

        return resultStr;
    }

    /**
     *
     * 此处为功能说明
     *
     * @author 唐坤[0F935]
     * @date 2014-4-8 下午3:47:34
     * @param url
     * @param encode
     * @return
     * @throws java.io.IOException
     * @throws Exception
     */
    public static String postEntity(String url, HttpEntity param, String encode) throws Exception {
        String resultStr = null;
        HttpClient httpclient = null;
        try {
            HttpPost httppost = new HttpPost(url);
            httppost.setEntity(param);
            httpclient = new DefaultHttpClient();
            HttpResponse httpResponse = httpclient.execute(httppost);
            if (httpResponse.getStatusLine().getStatusCode() == STATUSCODE) {
                resultStr = EntityUtils.toString(httpResponse.getEntity(), encode);
                if (resultStr != null) {
                    resultStr = resultStr.trim();
                }
                EntityUtils.consume(httpResponse.getEntity());
            } else {
                resultStr = httpResponse.getStatusLine().toString();
                logger.error("请求失败，返回：" + resultStr);
            }
        } finally {
            if (httpclient != null) {
                httpclient.getConnectionManager().shutdown();
            }
        }

        return resultStr;
    }
}
