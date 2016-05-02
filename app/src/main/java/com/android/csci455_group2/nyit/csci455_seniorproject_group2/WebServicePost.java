package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.util.*;

/**
 * Created by llh on 2016/3/2.
 */
public class WebServicePost {
    private static String IP = "216.37.110.198:8080";
    // 通过 POST 方式获取HTTP服务器数据
    public static String executeHttpPost(String username, String password) {
        try {
            String path = "http://" + IP + "/AndroidProject_Login/servlet/LogLet";
            // 发送指令和信息
            Map<String, String> params = new HashMap<String, String>();
            params.put("username", username);
            params.put("password", password);
            return sendPOSTRequest(path, params, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // 处理发送数据请求
    private static String sendPOSTRequest(String path, Map<String, String> params, String encoding) throws Exception {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, encoding);
        HttpPost post = new HttpPost(path);
        post.setEntity(entity);

        CloseableHttpClient client = HttpClients.createDefault();

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
        post.setConfig(requestConfig);
        //client.execute(post);

        HttpResponse response = client.execute(post);
        // 判断是否成功收取信息
        if (response.getStatusLine().getStatusCode() == 200) {
            return getInfo(response);
        }
        // 未成功收取信息，返回空指针
        return null;
    }
    // 收取数据
    private static String getInfo(HttpResponse response) throws Exception {
        HttpEntity entity = response.getEntity();
        InputStream is = entity.getContent();
        // 将输入流转化为byte型
        byte[] data = WebService.read(is);
        // 转化为字符串
        return new String(data, "UTF-8");
    }
}
