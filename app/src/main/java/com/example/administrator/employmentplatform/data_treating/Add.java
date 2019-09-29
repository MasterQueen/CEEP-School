package com.example.administrator.employmentplatform.data_treating;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by WANGBIN on 2018/3/18.
 */

public class Add {

    String is_add;

    public Add(String name, String phone, String price, String jianjie, String beizhu){
        HttpURLConnection connection;
        try {
            //用GET方法向服务器传送数据，在链接里面传值
            String mname = URLEncoder.encode(name, "UTF-8");
            String mphone = URLEncoder.encode(phone, "UTF-8");
            String mprice = URLEncoder.encode(price, "UTF-8");
            String mjianjie = URLEncoder.encode(jianjie, "UTF-8");
            String mbeizhu = URLEncoder.encode(beizhu, "UTF-8");
//            System.out.println(".........."+id+nickname+password+"///////////////////"+mid+mnickname+mpassword);
            URL url = new URL("http://47.104.94.221:8080/JWebDemoByIDE/RegisterServlet?name=" + mname + "&phone=" + mphone + "&price=" + mprice+ "&jianjie=" + mjianjie+ "&beizhu=" + mbeizhu);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(5000);
            connection.setRequestMethod("POST");
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
                is_add = s.toString();
                System.out.println(s.toString()+"jjjjjjjjjjssssss"+is_add);
            }
        }catch(Exception e){
            Log.i("ok", "有错误！");
        }
    }

    public String getIs_add() {
        return is_add;
    }
}
