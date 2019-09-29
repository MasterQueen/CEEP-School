package com.example.administrator.employmentplatform.User_view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.employmentplatform.R;

/**
 * Created by Administrator on 3/22/2018.
 */

public class User_school_secondschool_massage extends AppCompatActivity {

    private String product;
    private String productintro;
    private String imgsrc;
    private String price;
    private String postscript;
    private String contactinfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_school_second_massage);

        Intent i = getIntent();
        product = i.getStringExtra("product");
        productintro = i.getStringExtra("productintro");
        imgsrc = i.getStringExtra("imgsrc");
        price = i.getStringExtra("price");
        postscript = i.getStringExtra("postscript");
        contactinfo = i.getStringExtra("contactinfo");

        android.support.v7.widget.Toolbar toolbar =findViewById(R.id.second_toolbar);
        toolbar.setTitle("商品信息");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

//        ImageView imageView = findViewById(R.id.commodity_image);
//        imageView.setImageResource(R.mipmap.school);

        TextView product_view = (TextView)findViewById(R.id.commodity_name);
        TextView productintro_view = (TextView)findViewById(R.id.commodity_massage);
        TextView price_view = (TextView)findViewById(R.id.commodity_money);
        TextView postscript_view = (TextView)findViewById(R.id.more);
        TextView contactinfo_view = (TextView)findViewById(R.id.phone);

        product_view.setText(product);
        productintro_view.setText(productintro);
        price_view.setText(price);
        postscript_view.setText(postscript);
        contactinfo_view.setText(contactinfo);

    }
}
