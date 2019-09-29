package com.example.administrator.employmentplatform.User_view;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.employmentplatform.MainActivity;
import com.example.administrator.employmentplatform.R;
import com.example.administrator.employmentplatform.data_treating.User_setNew;

/**
 * Created by Administrator on 3/21/2018.
 */

public class Uaser_set_conter extends AppCompatActivity {

    private EditText new_password;
    private EditText nick_name;
    private EditText new_age;
    private EditText new_sex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_set_conter);

        android.support.v7.widget.Toolbar toolbar =findViewById(R.id.set_conter_toolbar);
        toolbar.setTitle("账号设置");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        new_password = findViewById(R.id.set_new_password_edit);
        nick_name = findViewById(R.id.set_nick_name_edit);
        nick_name.setText(MainActivity.nickname);
        new_age = findViewById(R.id.set_new_age_edit);
        new_age.setText(MainActivity.age);
        new_sex = findViewById(R.id.set_new_sex_edit);
        new_sex.setText(MainActivity.sex);

        //接受Inter传来的账号参数
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        final String name = bundle.getString("id");

        Button ok = findViewById(R.id.set_conter_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password = new_password.getText().toString();
                String nickname = nick_name.getText().toString();
                String age = new_age.getText().toString();
                String sex = new_sex.getText().toString();
                new UserResetTask(name, sex, age, nickname, password).execute();
//                Toast.makeText(User_set_conter.this,name,Toast.LENGTH_SHORT).show();

            }
        });
    }

    public class UserResetTask extends AsyncTask<Void, Void, Boolean> {

        private final String mid;
        private final String msex;
        private final String mage;
        private final String mnickname;
        private final String mpassword;


        UserResetTask(String id, String sex , String age, String nickname, String password) {
            mid = id;
            msex = sex;
            mage = age;
            mnickname = nickname;
            mpassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                String is_set = new User_setNew(mid, msex, mage, mnickname, mpassword).getIs_set();
                System.out.println(is_set+mid+msex+mage+mnickname+mpassword);
                if (is_set.equals("1")){
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
                Toast.makeText(Uaser_set_conter.this, "修改成功！",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Uaser_set_conter.this, "修改失败！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
