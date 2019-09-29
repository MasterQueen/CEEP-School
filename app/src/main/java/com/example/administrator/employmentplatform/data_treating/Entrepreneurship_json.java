package com.example.administrator.employmentplatform.data_treating;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by WANGBIN on 2018/3/18.
 */

public class Entrepreneurship_json {
    String entrepreneurship_json = null;
    public Entrepreneurship_json(){
        HttpURLConnection connection;
        try {
            //用GET方法向服务器传送数据，在链接里面传值
            URL url = new URL("http://47.104.94.221:8080/JWebDemoByIDE/EntrepreneurshipServlet");
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("charset", "UTF-8");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            if (connection.getResponseCode() == 200) {
                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder s = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    s.append(line);
                }
                reader.close();
                entrepreneurship_json = s.toString();
            }
        }catch(Exception e){
            Log.i("ok", "有错误！");
        }
    }
    public String getEntrepreneurship_json() {
        return entrepreneurship_json;
    }
}
