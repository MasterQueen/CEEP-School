package com.example.administrator.employmentplatform.User_view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.employmentplatform.LoginActivity;
import com.example.administrator.employmentplatform.MainActivity;
import com.example.administrator.employmentplatform.R;

/**
 * Created by Administrator on 3/21/2018.
 */

public class User_set extends AppCompatActivity {

    private EditText idname;
    private EditText password;
    private Button change;
    private int temp = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_set);

        android.support.v7.widget.Toolbar toolbar =findViewById(R.id.set_toolbar);
        toolbar.setTitle("身份验证");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        Button ok = findViewById(R.id.set_ok);
        change = findViewById(R.id.set_change);

        idname = (EditText)findViewById(R.id.set_idname_edit);
        password = (EditText)findViewById(R.id.set_password_edit);
        idname.setText(MainActivity.id);

        //确认按键监听 进行账号密码检测

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mid = idname.getText().toString();
                String mpassword = password.getText().toString();

                if (mid.equals(MainActivity.id)){
                    if (mpassword.equals(MainActivity.password)){
                        Intent intent = new Intent(User_set.this,Uaser_set_conter.class);
                        //用Bundle携带数据
                        Bundle bundle = new Bundle();
                        //传递name参数为tinyphp
                        bundle.putString("id",idname.getText().toString());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else {
                        temp = 1;
                        putOut();
                    }
                }else {
                    temp = 2;
                    putOut();
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_set.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    void putOut(){
        if (temp == 1){
            Toast.makeText(User_set.this,"密码不正确",Toast.LENGTH_SHORT).show();
            System.out.println("密码不正确");
        }else if (temp == 2){
            Toast.makeText(User_set.this,"用户名错误",Toast.LENGTH_SHORT).show();
            System.out.println("用户名错误");
        }
    }
}
