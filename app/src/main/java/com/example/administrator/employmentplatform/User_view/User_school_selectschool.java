package com.example.administrator.employmentplatform.User_view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.employmentplatform.AddMessageActivity;
import com.example.administrator.employmentplatform.Citys;
import com.example.administrator.employmentplatform.LoginActivity;
import com.example.administrator.employmentplatform.MainActivity;
import com.example.administrator.employmentplatform.Province;
import com.example.administrator.employmentplatform.R;
import com.example.administrator.employmentplatform.data_treating.ChangeSchool;
import com.example.administrator.employmentplatform.data_treating.Employment_json;
import com.example.administrator.employmentplatform.data_treating.Entrepreneurship_json;
import com.example.administrator.employmentplatform.data_treating.Recruitment_json;
import com.example.administrator.employmentplatform.data_treating.Register_two;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 选择学校
 * Created by Administrator on 2/23/2018.
 */

public class User_school_selectschool extends AppCompatActivity  implements View.OnClickListener,AdapterView.OnItemSelectedListener{




    private Spinner spinner1, spinner2;
    private String id;
    public static String local = "郑州";
    private String university;
    private List<Citys> data;
    private List<Province> provinces;
    private List<String> city;
    private ArrayAdapter<String> adapter1,adapter2;

    public static String employment_json;
    public static int count;
    public static String entrepreneurship_json;
    public static int count1;
    public static String recruitment_json;
    public static int count2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.our_school_selectschool);

            android.support.v7.widget.Toolbar toolbar =findViewById(R.id.toolbar);
            toolbar.setTitle("选择学校");
            toolbar.setTitleTextColor(Color.WHITE);
            setSupportActionBar(toolbar);

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



           Button ok = findViewById(R.id.select_school_button);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    id = MainActivity.id;
                    local = spinner1.getSelectedItem().toString();
                    university = spinner2.getSelectedItem().toString();
//                    Toast.makeText(User_school_selectschool.this,spinner2.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                    setLocal(local);
                    new ChangeSchoolTask(id, local, university).execute();
                }
            });

        }

        public void setLocal(String local){
            this.local = local;
        }

    public String getLocal() {
        return local;
    }

    public class ChangeSchoolTask extends AsyncTask<Void, Void, Boolean> {

        private final String mid;
        private final String mlocal;
        private final String muniversity;


        ChangeSchoolTask(String id, String local, String university) {
            mid = id;
            mlocal = local;
            muniversity = university;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                employment_json = new Employment_json(mlocal).getEmployment_json();
                JSONArray jsonArray = new JSONArray(employment_json);
                count = jsonArray.length();
                entrepreneurship_json = new Entrepreneurship_json().getEntrepreneurship_json();
                JSONArray jsonArray1 = new JSONArray(entrepreneurship_json);
                count1 = jsonArray1.length();
                recruitment_json = new Recruitment_json().getRecruitment_json();
                JSONArray jsonArray2 = new JSONArray(recruitment_json);
                count2 = jsonArray2.length();
                String is_changed = new ChangeSchool(mid, mlocal, muniversity).getIs_changed();
                System.out.println(is_changed+mid+mlocal+muniversity);
                if (is_changed.equals("1") && employment_json!=null){
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
                Toast.makeText(User_school_selectschool.this, "修改成功！",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(User_school_selectschool.this, MainActivity.class);
                intent.putExtra("id", MainActivity.id);
                intent.putExtra("password", MainActivity.password);
                intent.putExtra("sex",MainActivity.sex);
                intent.putExtra("local",mlocal);
                intent.putExtra("age",MainActivity.age);
                intent.putExtra("nickname",MainActivity.nickname);
                intent.putExtra("university",muniversity);
                intent.putExtra("employment_json",employment_json);
                intent.putExtra("count",count);
                intent.putExtra("entrepreneurship_json",entrepreneurship_json);
                intent.putExtra("count1",count1);
                intent.putExtra("recruitment_json",recruitment_json);
                intent.putExtra("count2",count2);
                startActivity(intent);
            } else {
                Toast.makeText(User_school_selectschool.this, "修改失败！",Toast.LENGTH_SHORT).show();
            }
            finish();
        }

        @Override
        protected void onCancelled() {

        }
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
        citys=new Citys("郑州航空工业管理学院");
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.select_school_button:

                break;
        }
    }
}


