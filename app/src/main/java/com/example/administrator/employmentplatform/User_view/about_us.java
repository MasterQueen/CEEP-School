package com.example.administrator.employmentplatform.User_view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.employmentplatform.R;

/**
 * Created by Administrator on 2/20/2018.
 */

public class about_us extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        android.support.v7.widget.Toolbar toolbar =findViewById(R.id.about_us_toolbar);
        toolbar.setTitle("关于我们");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

    }
}
