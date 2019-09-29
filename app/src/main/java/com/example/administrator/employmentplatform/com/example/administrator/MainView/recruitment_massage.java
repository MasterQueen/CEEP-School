package com.example.administrator.employmentplatform.com.example.administrator.MainView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.employmentplatform.R;

/**
 * Created by Administrator on 3/11/2018.
 */

public class recruitment_massage extends AppCompatActivity {

//    private Button send_resume;
//    private Boolean is_sent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view_recruitment_massage);

        Intent i = getIntent();
        String company = i.getStringExtra("company");
        String intro = i.getStringExtra("intro");
        String postion = i.getStringExtra("postion");
        String request = i.getStringExtra("request");
        String address = i.getStringExtra("address");
        String time = i.getStringExtra("time");
        String people = i.getStringExtra("people");

        android.support.v7.widget.Toolbar toolbar =findViewById(R.id.recruitment_toolbar_masage);
        toolbar.setTitle("校招信息");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setBackgroundColor(Color.rgb(71,172,200));
        setSupportActionBar(toolbar);

        Window window = getWindow();
        window.setStatusBarColor(Color.rgb(71,172,200));


        TextView tv = (TextView)findViewById(R.id.company_name);
        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "fonnts/text.ttf");
        tv.setTypeface(typeface );

        TextView tv2 = (TextView)findViewById(R.id.company_intro);
        Typeface typeface2 = Typeface.createFromAsset(this.getAssets(), "fonnts/text2.ttf");
        tv2.setTypeface(typeface2 );

        TextView tv3 = (TextView)findViewById(R.id.company_post);
        Typeface typeface3 = Typeface.createFromAsset(this.getAssets(), "fonnts/text2.ttf");
        tv3.setTypeface(typeface3 );

        TextView tv4 = (TextView)findViewById(R.id.company_school);
        Typeface typeface4 = Typeface.createFromAsset(this.getAssets(), "fonnts/text2.ttf");
        tv4.setTypeface(typeface4 );

        TextView tv5 = (TextView)findViewById(R.id.company_time);
        Typeface typeface5 = Typeface.createFromAsset(this.getAssets(), "fonnts/text2.ttf");
        tv5.setTypeface(typeface5 );

        TextView tv6 = (TextView)findViewById(R.id.company_request);
        Typeface typeface6 = Typeface.createFromAsset(this.getAssets(), "fonnts/text2.ttf");
        tv6.setTypeface(typeface6 );

        TextView tv7 = findViewById(R.id.company_people);
        tv7.setTypeface(typeface6);



        TextView company_view = (TextView)findViewById(R.id.company_name);
        TextView profile_view = (TextView)findViewById(R.id.profile);
        TextView MOTOLEY_view = (TextView)findViewById(R.id.MOTOLEY);
        TextView university_view = (TextView)findViewById(R.id.university);
        TextView request_view = findViewById(R.id.request_conter);
        TextView time_view = findViewById(R.id.time_conter);
        TextView people_view = findViewById(R.id.people_conter);
        company_view.setText(company);
        profile_view.setText(intro);
        MOTOLEY_view.setText(postion);
        request_view.setText(request);
        university_view.setText(address);
        time_view.setText(time);
        people_view.setText(people);


//        send_resume = (Button)findViewById(R.id.send_resume);
//        send_resume.setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View v) {
//                  is_sent = true;
//              }
//           }
//        );
//
//        if (is_sent == true){
//            Toast.makeText(this, "发送成功！", Toast.LENGTH_SHORT).show();
//        }

    }

}
