package com.example.administrator.employmentplatform.User_view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.employmentplatform.LoginActivity;
import com.example.administrator.employmentplatform.MainActivity;
import com.example.administrator.employmentplatform.R;
import com.example.administrator.employmentplatform.data_treating.Login_data;
import com.example.administrator.employmentplatform.data_treating.ShowMessage_json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User_message extends AppCompatActivity {

    String nickname = null;
    String university = null;
    String sex = null;
    String local = null;
    String age = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 接收LoginActivity传输来的登录用户ID
        Intent i = getIntent();
        String id = i.getStringExtra("id");

        new ShowMessageTask(id).execute();
    }

    public class ShowMessageTask extends AsyncTask<Void, Void, String> {

        private final String mid;

        ShowMessageTask(String id) {
            mid = id;
        }

        @Override
        protected String doInBackground(Void... params) {

            String showmessage_json = new ShowMessage_json(mid).getShowMessage_json();
            try {
                JSONArray jsonArray = new JSONArray(showmessage_json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    nickname = jsonObject.getString("nickname");
                    university = jsonObject.getString("university");
                    sex = jsonObject.getString("sex");
                    local = jsonObject.getString("local");
                    age = jsonObject.getString("age");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return "\n账号："+mid+"\n\n\n昵称："+nickname+"\n\n\n性别："+sex+"\n\n\n年龄："+age+"\n\n\n城市："+local+"\n\n\n高校："+university;
//            message.setText("\n昵称："+nickname+"\n\n\n性别："+sex+"\n\n\n年龄："+age+"\n\n\n城市："+local+"\n\n\n高校："+university);
//            System.out.println("\n昵称："+nickname+"\n\n\n性别："+sex+"\n\n\n年龄："+age+"\n\n\n城市："+local+"\n\n\n高校："+university);
        }

        @Override
        protected void onPostExecute(final String success) {
            TextView message = (TextView)findViewById(R.id.message);
            message.setText(success);
            TextView idView = (TextView) LayoutInflater.from(User_message.this).inflate(R.layout.main_header, null).findViewById(R.id.idView);

            TextView schoolView = (TextView) LayoutInflater.from(User_message.this).inflate(R.layout.main_header, null).findViewById(R.id.schoolView);

            idView.setText(nickname);
            schoolView.setText(university);
        }

        @Override
        protected void onCancelled() {

        }
    }
}
