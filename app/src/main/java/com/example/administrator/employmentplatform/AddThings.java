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

import com.example.administrator.employmentplatform.User_view.User_school;
import com.example.administrator.employmentplatform.data_treating.Add;
import com.example.administrator.employmentplatform.data_treating.Register_one;

public class AddThings extends AppCompatActivity {


    private Button addBtn;
    private EditText et_name;
    private EditText et_phone;
    private EditText et_price;
    private EditText et_jianjie;
    private EditText et_beizhuxinxi;
    private String name;
    private String phone;
    private String price;
    private String jianjie;
    private String beizhu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_things);
        initData();
        initView();
    }

    private void initData() {

        addBtn = (Button)findViewById(R.id.addBtn);
    }

    private void initView() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                et_name = (EditText)findViewById(R.id.et_name);
                et_phone = (EditText)findViewById(R.id.et_phone);
                et_price = (EditText)findViewById(R.id.et_price);
                et_jianjie = (EditText)findViewById(R.id.et_jianjie);
                et_beizhuxinxi = (EditText)findViewById(R.id.et_beizhuxinxi);
                name = et_name.getText().toString();
                phone = et_phone.getText().toString();
                price = et_price.getText().toString();
                jianjie = et_jianjie.getText().toString();
                beizhu = et_beizhuxinxi.getText().toString();
                new AddThingsTask(name, phone, price,jianjie,beizhu).execute();
            }
        });
    }

    public class AddThingsTask extends AsyncTask<Void, Void, Boolean> {

        private final String mname;
        private final String mphone;
        private final String mprice;
        private final String mjianjie;
        private final String mbeizhu;

        AddThingsTask(String name, String phone , String price,String jianjie,String beizhu) {
            mname = name;
            mphone = phone;
            mprice = price;
            mjianjie = jianjie;
            mbeizhu = beizhu;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                String is_add = new Add(mname, mphone, mprice, mjianjie, mbeizhu).getIs_add();
                if (is_add.equals("1")){
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
                Toast.makeText(AddThings.this, "发布成功！",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddThings.this,User_school.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(AddThings.this, "发布失败！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
