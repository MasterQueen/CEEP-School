package com.example.administrator.employmentplatform.data_treating;

import android.util.Log;


import com.example.administrator.employmentplatform.User_view.User_school_selectschool;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by WANGBIN on 2018/3/18.
 */

public class Employment_json {
    String employment_json = null;
    String city = "郑州";
    int count;
    public Employment_json(String city){
        HttpURLConnection connection;
        try {
            //city = User_school_selectschool.local;
            //用GET方法向服务器传送数据，在链接里面传值
            this.city = city;
            String mcity = URLEncoder.encode(city, "UTF-8");
            URL url = new URL("http://47.104.94.221:8080/JWebDemoByIDE/EmploymentServlet?city=" + mcity);
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
                employment_json = s.toString();
                JSONArray jsonArray = new JSONArray(employment_json);
                count = jsonArray.length();
            }
        }catch(Exception e){
            Log.i("ok", "有错误！");
        }
    }
    public String getEmployment_json() {
        return employment_json;
    }
    public int getCount(){
        return count;
    };
}
