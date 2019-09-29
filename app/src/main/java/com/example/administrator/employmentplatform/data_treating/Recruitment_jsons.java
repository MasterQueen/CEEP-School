package com.example.administrator.employmentplatform.data_treating;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by WANGBIN on 2018/8/1.
 */

public class Recruitment_jsons {
    String recruitment_jsons = null;
    public Recruitment_jsons(String search){
        HttpURLConnection connection;
        try {
            //用GET方法向服务器传送数据，在链接里面传值
            String msearch = URLEncoder.encode(search, "UTF-8");
            URL url = new URL("http://47.104.94.221:8080/JWebDemoByIDE/EntrepreneurshipServlet?search=" + msearch);
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
                recruitment_jsons = s.toString();
            }
        }catch(Exception e){
            Log.i("ok", "有错误！");
        }
    }
    public String getRecruitment_jsons() {
        return recruitment_jsons;
    }
}
