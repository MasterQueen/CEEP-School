package com.example.administrator.employmentplatform;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.employmentplatform.data_treating.Employment_json;
import com.example.administrator.employmentplatform.data_treating.Login_data;
import com.example.administrator.employmentplatform.data_treating.Register_one;

public class Register extends AppCompatActivity {


    private Button logonBtn;
    private EditText nickname_et;
    private EditText id_et;
    private EditText password_et;
    private String nickname;
    private String id;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initData();
        initView();
    }

    private void initData() {

        logonBtn = (Button)findViewById(R.id.logonbtn);
    }

    private void initView() {
        logonBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                nickname_et = (EditText)findViewById(R.id.et_1);
                id_et = (EditText)findViewById(R.id.et_2);
                password_et = (EditText)findViewById(R.id.et_3);
                nickname = nickname_et.getText().toString();
                id = id_et.getText().toString();
                password = password_et.getText().toString();
                new UserRegisterTask(id, nickname, password).execute();
            }
        });
    }

    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private final String mid;
        private final String mnickname;
        private final String mpassword;


        UserRegisterTask(String id, String nickname , String password) {
            mid = id;
            mnickname = nickname;
            mpassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                String is_register = new Register_one(mid, mnickname, mpassword).getIs_register();
                System.out.println(is_register+mid+mnickname+mpassword);
                if (is_register.equals("1")){
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
                Toast.makeText(Register.this, "成功，请完善信息！",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register.this,AddMessageActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Register.this, "注册失败！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
