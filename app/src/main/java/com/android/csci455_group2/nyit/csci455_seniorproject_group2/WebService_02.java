package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import java.io.*;
import java.net.*;
/**
 * Created by llh on 2016/3/2.
 */
public class WebService_02 {
    private static String IP = "52.39.2.78:9999";
    // 通过Get方式获取HTTP服务器数据 get server data by using Get method
    public static String executeHttpGet(String name,String age, String username, String password){
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            // 用户名 密码username password
            // URL 地址 address
            String path = "http://" + IP + "/AndroidProject_Login/servlet/RegLet";
            path = path + "?name=" + name + "&age=" + age + "&username=" + username + "&password=" + password;
            conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(5000); // 设置超时时间 set the time limit
            conn.setReadTimeout(5000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式 set hte way to get data
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式 set character form
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                return parseInfo(is);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    // 将输入流转化为 String 型 (change the input stream to String)
    private static String parseInfo(InputStream inStream) throws Exception {
        byte[] data = read(inStream);
        // 转化为字符串
        return new String(data, "UTF-8");
    }
    // 将输入流转化为byte型
    public static byte[] read(InputStream inStream) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        inStream.close();
        return outputStream.toByteArray();
    }
}

