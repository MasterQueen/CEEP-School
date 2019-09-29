package com.example.administrator.employmentplatform.data_treating;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by WANGBIN on 2018/3/15.
 */

public class Login_data {

    private String idnumber = "未知";
    private String sex = "未知";
    private String university = "和哈哈哈哈";
    private String nickname = "未知";
    private String local = "未知";
    private String age = "未知";

    public Login_data(String mEmail, String mPassword){
        HttpURLConnection connection;
        try {
            //用GET方法向服务器传送数据，在链接里面传值
            String id = URLEncoder.encode(mEmail, "UTF-8");
            String password = URLEncoder.encode(mPassword, "UTF-8");
            URL url = new URL("http://47.104.94.221:8080/JWebDemoByIDE/LoginServlet?id=" + id + "&password=" + password);
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

                JSONArray jsonArray = new JSONArray(s.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    idnumber = jsonObject.getString("id");
                    nickname = jsonObject.getString("nickname");
                    university = jsonObject.getString("university");
                    sex = jsonObject.getString("sex");
                    local = jsonObject.getString("local");
                    age = jsonObject.getString("age");
                }

                System.out.println(s.toString());
//                System.out.println(idnumber+nickname+university+sex+age+local);
            }
        }catch(Exception e){
                Log.i("ok", "有错误！");
        }
    }



    public String getIdnumber() {
        return idnumber;
    }

    public String getAge() {
        return age;
    }

    public String getLocal() {
        return local;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSex() {
        return sex;
    }

    public String getUniversity() {
        return university;
    }

}
