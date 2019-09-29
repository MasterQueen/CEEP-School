package com.example.administrator.employmentplatform.com.example.administrator.MainView;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.administrator.employmentplatform.R;

public class enterprenrurshio_view extends AppCompatActivity {

    String title;
    String body;
    String time;
    String university;
    String room;
    TextView ed_title;
    TextView ed_time;
    TextView ed_body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprenrurshio_view);

        android.support.v7.widget.Toolbar toolbar =findViewById(R.id.enterpreneurshio_massage_toolbar);
        toolbar.setTitle("咨询信息");
        toolbar.setBackgroundColor(Color.rgb(71,172,200));
        toolbar.setTitleTextColor(Color.WHITE);
        Window window = getWindow();
        window.setStatusBarColor(Color.rgb(71,172,200));

        Intent i = getIntent();
        title = i.getStringExtra("title");
        body = i.getStringExtra("body");
        time = i.getStringExtra("time");
        university = i.getStringExtra("university");
        room = i.getStringExtra("room");

        ed_title = findViewById(R.id.enterprenrurshio_title);
        ed_time = findViewById(R.id.enterprenrurshio_time);
        ed_body = findViewById(R.id.enterprenrurshio_body);

        ed_title.setText(title);
        ed_time.setText(university + room + "   " + time);
        ed_body.setText(body);
    }
}
