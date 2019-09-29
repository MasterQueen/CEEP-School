package com.example.administrator.employmentplatform.com.example.administrator.MainView;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.employmentplatform.R;

/**
 * Created by Administrator on 2/17/2018.
 */

public class Chat extends Fragment {


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_view_chat, null);

        android.support.v7.widget.Toolbar toolbar =view.findViewById(R.id.chat_toolbar);
        toolbar.setTitle("消息");
        toolbar.setTitleTextColor(Color.WHITE);
        ((AppCompatActivity) getActivity()). setSupportActionBar(toolbar);

        return view;

    }

}
