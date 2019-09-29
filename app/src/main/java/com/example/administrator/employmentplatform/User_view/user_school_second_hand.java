package com.example.administrator.employmentplatform.User_view;



import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.employmentplatform.AddThings;
import com.example.administrator.employmentplatform.R;

import java.util.ArrayList;
import java.util.List;

/*
二手交易平台主界面
 */
public class user_school_second_hand extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Button add_Btn;

    private List<Picture> mpicture = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewAdapter mAdapter;

    private String product[];
    private String productintro[];
    private String imgsrc[];
    private String price[];
    private String postscript[];
    private String contactinfo[];
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.our_school_secondhand_main);
        Intent i = getIntent();
        product = i.getStringArrayExtra("product");
        productintro = i.getStringArrayExtra("productintro");
        imgsrc = i.getStringArrayExtra("imgsrc");
        price = i.getStringArrayExtra("price");
        postscript = i.getStringArrayExtra("postscript");
        contactinfo = i.getStringArrayExtra("contactinfo");
        count = i.getIntExtra("count",0);


        initDatas();

         initViews();
        android.support.v7.widget.Toolbar toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle("校园交易平台");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        add_Btn = findViewById(R.id.send_button);
        add_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_school_second_hand.this,AddThings.class));
                finish();
            }
        });
    }

    private void initDatas() {
        getData();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mAdapter = new RecyclerViewAdapter(this,mpicture);

    }
    private  void  initViews(){
       //得到控件
        mRecyclerView = findViewById(R.id.id_recyclerView);
        //设置布局
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //
        mAdapter.setmOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override

            //监听
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(user_school_second_hand.this,User_school_secondschool_massage.class);
                intent.putExtra("product",product[position]);
                intent.putExtra("productintro",productintro[position]);
                intent.putExtra("imgsrc",imgsrc[position]);
                intent.putExtra("price",price[position]);
                intent.putExtra("postscript",postscript[position]);
                intent.putExtra("contactinfo",contactinfo[position]);

                startActivity(intent);

            }

            @Override
            public void onItemLongClick(View view, int position) {


            }
        });
        // 设置间隔
      //  mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        //设置Adapter
        mRecyclerView.setAdapter(mAdapter);
    }
    private void getData(){
        for (int i = 0;i<count;i++){
            Picture A=new Picture(product[i],R.drawable.zzuli);
            mpicture.add(A);
//            Picture B=new  Picture(product[1],R.mipmap.test);
//            mpicture.add(B);
//            Picture C=new Picture(product[2],R.mipmap.test);
//            mpicture.add(C);
//            Picture D=new  Picture(product[3],R.mipmap.test);
//            mpicture.add(D);
//            Picture E=new Picture(product[4],R.mipmap.test);
//            mpicture.add(E);
        }
    }
}