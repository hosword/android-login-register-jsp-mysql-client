package com.example.coursedesign3;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/1/4 0004.
 */
public class HttpClientUtil {

    static DefaultHttpClient client=new DefaultHttpClient();

    public static HttpGet getGet(String uri) {
        //向服务器发送一次请求
        HttpGet request = new HttpGet(uri);

        return request;
    }

    public static HttpPost getPost(String uri) {
        HttpPost request = new HttpPost(uri);

        return request;
    }

    public static String getResponseResult(HttpRequestBase request) {
        HttpResponse res = null;//服务器端发送回来的响应
        String result = null;
        try {
            res = client.execute(request);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(res.getEntity());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String login(String account, String password)
            throws ClientProtocolException, IOException {
//        String uri = "http://172.18.23.54:8080/JDBCTest/index.jsp";
        String uri = "http://wenxiaoba.cn:8080/login/index.jsp";

        HttpPost request = HttpClientUtil.getPost(uri);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("phone", account));
        params.add(new BasicNameValuePair("password", password));
        HttpEntity httpentity = new UrlEncodedFormEntity(params, "utf-8");//响应内容
        request.setEntity(httpentity);
        String result = HttpClientUtil.getResponseResult(request);
        return result;
    }

    public static String register(String account, String password)
            throws ClientProtocolException, IOException {
        //String uri = "http://172.18.23.54:8080/JDBCTest/register.jsp";
        String uri = "http://wenxiaoba.cn:8080/login/register.jsp";

        HttpPost request = HttpClientUtil.getPost(uri);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("phone", account));
        params.add(new BasicNameValuePair("password", password));
        HttpEntity httpentity = new UrlEncodedFormEntity(params, "utf-8");//响应内容
        request.setEntity(httpentity);
        String result = HttpClientUtil.getResponseResult(request);
        return result;
    }

}
