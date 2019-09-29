package com.example.administrator.employmentplatform.User_view;


import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.employmentplatform.AddMessageActivity;
import com.example.administrator.employmentplatform.MainActivity;
import com.example.administrator.employmentplatform.R;
import com.example.administrator.employmentplatform.Register;
import com.example.administrator.employmentplatform.data_treating.Recruitment_json;
import com.example.administrator.employmentplatform.data_treating.Register_one;
import com.example.administrator.employmentplatform.data_treating.SecondHand_json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * user_school界面主界面
 * Created by Administrator on 2/20/2018.
 */

public class User_school extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.our_school);

//        android.support.v7.widget.Toolbar toolbar =findViewById(R.id.toolbar);
//        toolbar.setTitle("我的学校");
//        toolbar.setTitleTextColor(Color.WHITE);
//        setSupportActionBar(toolbar);
//
//        Button button_second_hand = findViewById(R.id.second_hand);
//        button_second_hand.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {

                new SecondHandTask().execute();

//            }
//        });
//        Button button_select_school = findViewById(R.id.select_school);
//        button_select_school.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(User_school.this,User_school_selectschool.class));
//            }
//        });


    }

    public class SecondHandTask extends AsyncTask<Void, Void, Boolean> {

        private String secondhand_json;



        SecondHandTask() {

        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                secondhand_json = new SecondHand_json().getSecondHand_json_json();
                if (secondhand_json != null){
                    System.out.println(secondhand_json);
                    return true;
                }

            } catch (Exception e) {
                Log.i("ok", "有错误！");
            }

            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (success) {
                try {
                    JSONArray jsonArray = new JSONArray(secondhand_json);
                     String product[] = new String[jsonArray.length()];
                     String productintro[] = new String[jsonArray.length()];
                     String imgsrc[] = new String[jsonArray.length()];
                     String price[] = new String[jsonArray.length()];
                     String postscript[] = new String[jsonArray.length()];
                     String contactinfo[] = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        product[i] = jsonObject.getString("product");
                        productintro[i] = jsonObject.getString("productintro");
                        imgsrc[i] = jsonObject.getString("imgsrc");
                        price[i] = jsonObject.getString("price");
                        postscript[i] = jsonObject.getString("postscript");
                        contactinfo[i] = jsonObject.getString("contactinfo");
                    }

                    Intent intent = new Intent(User_school.this,user_school_second_hand.class);
                    intent.putExtra("product",product);
                    intent.putExtra("productintro",productintro);
                    intent.putExtra("imgsrc",imgsrc);
                    intent.putExtra("price",price);
                    intent.putExtra("postscript",postscript);
                    intent.putExtra("contactinfo",contactinfo);
                    intent.putExtra("count",jsonArray.length());
                    startActivity(intent);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(User_school.this, "找不到服务器",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
