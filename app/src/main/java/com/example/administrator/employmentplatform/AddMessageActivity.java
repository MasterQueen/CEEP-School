package com.example.administrator.employmentplatform;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrator.employmentplatform.data_treating.Register_one;
import com.example.administrator.employmentplatform.data_treating.Register_two;

import java.util.ArrayList;
import java.util.List;

public class AddMessageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button addBtn;
    private EditText sex_et;
    private EditText age_et;
    private String id;
    private String sex;
    private String age;
    private String local;
    private String university;


    private Spinner spinner1, spinner2;
    private List<Citys> data;
    private List<Province> provinces;
    private List<String> city;
    private ArrayAdapter<String> adapter1,adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        Intent i = getIntent();
        id = i.getStringExtra("id");
        initData();
        initView();
        spinner1 = (Spinner) findViewById(R.id.spinner_province);
        spinner2 = (Spinner) findViewById(R.id.spinner_school);
//      创建数据源
        getData();
        getPriovince();
//      数据源与第一级的适配器
        adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                city);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


    }
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
//  设置第二个控件给定数据源绑定适配器
        String p_name = city.get(position).trim();
        List<String> citys = getCitys(p_name);

        adapter2=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                citys);
        spinner2.setAdapter(adapter2);
    }


    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }
    //   设定一个方法   把数据(市、区)装到对应的集合里面
    private void getData(){
//      创建集合 把city数据添加到集合
        data=new ArrayList<Citys>();
        provinces=new ArrayList<Province>();
        Citys citys=null;
        Province province=null;
//      北京市区
        citys=new Citys("北京大学");
        data.add(citys);
        citys=new Citys("清华大学");
        data.add(citys);
        citys=new Citys("北京邮电大学");
        data.add(citys);
        citys=new Citys("中国传媒大学");
        data.add(citys);
        citys=new Citys("北京电影学院");
        data.add(citys);
        citys=new Citys("北京理工大学");
        data.add(citys);
        citys=new Citys("中国人民大学");
        data.add(citys);
        province=new Province("北京", data);
        provinces.add(province);

//      上海市区
        data=new ArrayList<Citys>();
        citys=new Citys("复旦大学");
        data.add(citys);
        citys=new Citys("上海交通大学");
        data.add(citys);
        citys=new Citys("同济大学");
        data.add(citys);
        citys=new Citys("上海大学");
        data.add(citys);
        citys=new Citys("华东师范大学");
        data.add(citys);
        citys=new Citys("东华大学");
        data.add(citys);
        province=new Province("上海", data);
        provinces.add(province);

//      郑州市区
        data=new ArrayList<Citys>();
        citys=new Citys("郑州大学");
        data.add(citys);
        citys=new Citys("河南财经政法大学");
        data.add(citys);
        citys=new Citys("郑州轻工业学院");
        data.add(citys);
        citys=new Citys("河南工业大学");
        data.add(citys);
        citys=new Citys("中原工学院");
        data.add(citys);
        citys=new Citys("华北水利水电大学");
        data.add(citys);
        citys=new Citys("河南中医药大学");
        data.add(citys);
        citys=new Citys("郑州航空航天管理学院");
        data.add(citys);
        province=new Province("郑州", data);
        provinces.add(province);

//      武汉市区
        data=new ArrayList<Citys>();
        citys=new Citys("湖北大学");
        data.add(citys);
        citys=new Citys("武汉大学");
        data.add(citys);
        citys=new Citys("华中科技大学");
        data.add(citys);
        citys=new Citys("武汉轻工大学");
        data.add(citys);
        citys=new Citys("武汉科技大学");
        data.add(citys);
        citys=new Citys("中国地质大学");
        data.add(citys);
        province=new Province("武汉", data);
        provinces.add(province);

//      新乡市区
        data=new ArrayList<Citys>();
        citys=new Citys("新乡学院");
        data.add(citys);
        citys=new Citys("新乡医学院");
        data.add(citys);
        citys=new Citys("河南师范大学");
        data.add(citys);
        province=new Province("新乡", data);
        provinces.add(province);
    }
    //  通过遍历集合获得市的名字 --备用赋值给Spinner1
    private void getPriovince(){
        city=new ArrayList<String>();
        for (Province province : provinces) {
            String cityname = province.getP_name();
            city.add(cityname);
        }

    }
    //  通过市获得区--备用赋值给Spinner2
    private List<String> getCitys(String c_name){
        List<String> citylist=new ArrayList<String>();
        for (Province province : provinces) {
            if (c_name.equals(province.getP_name())) {
                List<Citys> citys = province.getCitys();
                for (Citys citys2 : citys) {
                    String cityName = citys2.getCity();
                    citylist.add(cityName);
                }
            }

        }

        return citylist;
    }

    private void initData() {

        addBtn=(Button)findViewById(R.id.addmbtn);
    }
  //按钮监听
    private void initView() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                sex_et = (EditText)findViewById(R.id.edit_1);
                age_et = (EditText)findViewById(R.id.edit_2);

                sex = sex_et.getText().toString();
                age = age_et.getText().toString();
                local = spinner1.getSelectedItem().toString();
                university = spinner2.getSelectedItem().toString();

                new UserRegisterTwoTask(id, sex, age, local, university).execute();
            }
        });
    }

    public class UserRegisterTwoTask extends AsyncTask<Void, Void, Boolean> {

        private final String mid;
        private final String msex;
        private final String mage;
        private final String mlocal;
        private final String muniversity;


        UserRegisterTwoTask(String id, String sex , String age, String local, String university) {
            mid = id;
            msex = sex;
            mage = age;
            mlocal = local;
            muniversity = university;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                String is_register = new Register_two(mid, msex, mage, mlocal, muniversity).getIs_register();
                System.out.println(is_register+mid+msex+mage+mlocal+muniversity);
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
                Toast.makeText(AddMessageActivity.this, "注册成功！",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddMessageActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(AddMessageActivity.this, "注册失败！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}

