package com.example.administrator.employmentplatform.com.example.administrator.MainView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.employmentplatform.R;

public class employment_view extends AppCompatActivity {

    int count;
    String title;
    String room;
    String body;
    String time;
    String university;
    TextView ed_title;
    TextView ed_time;
    TextView ed_university;
    TextView ed_body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employment_view);

        Intent i = getIntent();
        title = i.getStringExtra("title");
        room = i.getStringExtra("room");
        body = i.getStringExtra("body");
        university = i.getStringExtra("university");
        time = i.getStringExtra("time");
        count = i.getIntExtra("count",0);

        ed_title = findViewById(R.id.employment_title);
        ed_time = findViewById(R.id.employment_time);
        ed_university = findViewById(R.id.employment_university);
        ed_body = findViewById(R.id.employment_body);

        ed_title.setText(title);
        ed_time.setText(time);
        ed_university.setText(university);
        ed_body.setText(body);

    }
}
