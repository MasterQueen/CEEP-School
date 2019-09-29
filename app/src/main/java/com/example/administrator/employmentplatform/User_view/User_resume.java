package com.example.administrator.employmentplatform.User_view;


import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.PopupMenu;


import com.example.administrator.employmentplatform.MainActivity;
import com.example.administrator.employmentplatform.R;

import java.util.ArrayList;
import java.util.List;

public class User_resume extends AppCompatActivity  implements View.OnClickListener{


    Button add_item;

    private RecyclerView recy_view;
    private List<String> data;
    private ViewHoderAdapters adaapters;
    private Button add_but,rm_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_resume);

       // init();
        add_but =  (Button) findViewById(R.id.add_item);
        add_but.setOnClickListener(this);
        //rm_but =  (Button) findViewById(R.id.rm_but);
        // rm_but.setOnClickListener(this);

        recy_view= (RecyclerView)findViewById(R.id.resume_recycler_view);
        //默认列表
        LinearLayoutManager LM = new LinearLayoutManager(this);
        //横向滑动
        recy_view.setLayoutManager(LM);
        recy_view.setItemAnimator(new DefaultItemAnimator());
        //样式二,对应类DividerItemDecoration02
        //   recy_view.addItemDecoration(new DividerItemDecoration02(this,LinearLayoutManager.HORIZONTAL,R.drawable.style02));

        initData();
        adaapters = new ViewHoderAdapters(User_resume.this,data);

        DividerItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this,R.drawable.a));
        recy_view.addItemDecoration(divider);

        adaapters.setOnItemListener(new ViewHoderAdapters.OnItemClickListener() {
            public void  onItemLongOnClickListener(View view,int position) {
                showPopMenu(view,position);

            }
            public void onItemOnClickListener(View view,int position) {
                startActivity(new Intent(User_resume.this,MyMessageActivity.class));
                      finish();

            }
        });
        recy_view.setAdapter(adaapters);


    }


    public void showPopMenu(View view,final int pos) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.item_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                adaapters.removeData(pos);
                return false;
            }
        });
        popupMenu.show();
    }


    private void initData() {
        data = new ArrayList<String>();
        for (int i=0; i < 2; i++) {
            data.add("D"+i);
        }
    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_item:
                adaapters.addData(1);
                break;

//            case R.id.rm_but:
//                adaapters.removeData(1);
//                break;
        }
    }
}






