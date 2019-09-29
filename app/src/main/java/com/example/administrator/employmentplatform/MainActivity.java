package com.example.administrator.employmentplatform;


import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.employmentplatform.User_view.User_message;
import com.example.administrator.employmentplatform.User_view.User_resume;
import com.example.administrator.employmentplatform.User_view.User_school;
import com.example.administrator.employmentplatform.User_view.User_school_selectschool;
import com.example.administrator.employmentplatform.User_view.User_set;
import com.example.administrator.employmentplatform.User_view.about_us;
import com.example.administrator.employmentplatform.com.example.administrator.MainView.Chat;
import com.example.administrator.employmentplatform.com.example.administrator.MainView.Employment;
import com.example.administrator.employmentplatform.com.example.administrator.MainView.Enterprenrurshio;

import com.example.administrator.employmentplatform.com.example.administrator.MainView.recruitment;
import com.example.administrator.employmentplatform.data_treating.Employment_json;
import com.example.administrator.employmentplatform.data_treating.Login_data;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private List<TabItem> mTableItemList;

   private DrawerLayout mDrawerLayout;
   public ViewPager mViewPager;

    public static String id;
    public static String password;
    public static String nickname;
    public static String university;
    public static String sex;
    public static String local;
    public static String age;
    public static String employment_json;
    public static String entrepreneurship_json;
    public static String recruitment_json;
    public static String recruitment_jsons;
    public static int count;
    public static int count1;
    public static int count2;
    public static int count3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = this.getWindow();
                View decorView = window.getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                window.setNavigationBarColor(Color.TRANSPARENT);

            }else {
                Window window = this.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }

        // 接收LoginActivity传输来的登录用户ID
        Intent i = getIntent();
        id = i.getStringExtra("id");
        password = i.getStringExtra("password");
        nickname = i.getStringExtra("nickname");
        university = i.getStringExtra("university");
        sex = i.getStringExtra("sex");
        age = i.getStringExtra("age");
        local = i.getStringExtra("local");
        employment_json = i.getStringExtra("employment_json");
        entrepreneurship_json = i.getStringExtra("entrepreneurship_json");
        recruitment_json = i.getStringExtra("recruitment_json");
        count = i.getIntExtra("count",0);
        count1 = i.getIntExtra("count1",0);
        count2 = i.getIntExtra("count2",0);

        TextView idView = (TextView) LayoutInflater.from(MainActivity.this).inflate(R.layout.main_header, null).findViewById(R.id.idView);
//        TextView idView = (TextView)findViewById(R.id.idView);
//        idView = (TextView)findViewById(R.id.idView);

        TextView schoolView = (TextView) LayoutInflater.from(MainActivity.this).inflate(R.layout.main_header, null).findViewById(R.id.schoolView);
//        TextView schoolView = (TextView)findViewById(R.id.schoolView);
//        schoolView = (TextView)findViewById(R.id.schoolView);

//        idView.setText(nickname);
//        Toast.makeText(this,nickname,Toast.LENGTH_SHORT);
//        schoolView.setText(university);

        initTabData();
        initTabHost();
        setmDrawerLayout();

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }




    public void  setmDrawerLayout(){

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.slide_meun);
        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId()){
                   case R.id.user_message:

                       Intent intent = new Intent(MainActivity.this, User_message.class);
                       intent.putExtra("id", id);
                       startActivity(intent);
                       break;
                   case R.id.user_resume:
//                       startActivity(new Intent(MainActivity.this,User_resume.class));
                       startActivity(new Intent(MainActivity.this,User_school_selectschool.class));

                       break;

                   case R.id.user_school:
                       startActivity(new Intent(MainActivity.this,User_school.class));

                       break;
                   case R.id.account_set:

                       startActivity(new Intent(MainActivity.this,User_set.class));

                       break;
                   case R.id.about_our:
                       startActivity(new Intent(MainActivity.this,about_us.class));

                       break;
                   case R.id.update:
                        Toast.makeText(MainActivity.this,"已是最新版本",Toast.LENGTH_SHORT).show();

                       break;

               }
               return true;
            }

        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }


    private void initTabData() {
        mTableItemList = new ArrayList<>();
        //添加tab
        mTableItemList.add(new TabItem(R.drawable.four,R.drawable.four_press,R.string.employment_name, Employment.class));
        mTableItemList.add(new TabItem(R.drawable.one,R.drawable.one_press,R.string.enertprenrurshio_name,Enterprenrurshio.class));
        mTableItemList.add(new TabItem(R.drawable.two,R.drawable.two_press,R.string.recruitment_name, recruitment.class));
      //  mTableItemList.add(new TabItem(R.drawable.three,R.drawable.three_press,R.string.chat_name, Chat.class));

    }
    private void initTabHost() {
        //实例化FragmentTabHost对象
        FragmentTabHost fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);

        //去掉分割线
        fragmentTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i<mTableItemList.size(); i++) {
            TabItem tabItem = mTableItemList.get(i);
            //实例化一个TabSpec,设置tab的名称和视图
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(tabItem.getTitleString()).setIndicator(tabItem.getView());
            fragmentTabHost.addTab(tabSpec,tabItem.getFragmentClass(),null);

            //给Tab按钮设置背景
            fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.colorWhite));

            //默认选中第一个tab
            if(i == 0) {
                tabItem.setChecked(true);
            }
        }

        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //重置Tab样式
                for (int i = 0; i< mTableItemList.size(); i++) {
                    TabItem tabitem = mTableItemList.get(i);
                    if (tabId.equals(tabitem.getTitleString())) {
                        tabitem.setChecked(true);
                    }else {
                        tabitem.setChecked(false);
                    }
                }
            }
        });
    }
    public class TabItem {
        //正常情况下显示的图片
        private int imageNormal;
        //选中情况下显示的图片
        private int imagePress;
        //tab的名字
        private int title;
        private String titleString;

        //tab对应的fragment
        public Class<? extends Fragment> fragmentClass;

        public View view;
        public ImageView imageView;
        public TextView textView;

        public TabItem(int imageNormal, int imagePress, int title,Class<? extends Fragment> fragmentClass) {
            this.imageNormal = imageNormal;
            this.imagePress = imagePress;
            this.title = title;
            this.fragmentClass =fragmentClass;
        }

        public Class<? extends  Fragment> getFragmentClass() {
            return fragmentClass;
        }
        public int getImageNormal() {
            return imageNormal;
        }

        public int getImagePress() {
            return imagePress;
        }

        public int getTitle() {
            return  title;
        }

        public String getTitleString() {
            if (title == 0) {
                return "";
            }
            if(TextUtils.isEmpty(titleString)) {
                titleString = getString(title);
            }
            return titleString;
        }

        public View getView() {
            if(this.view == null) {
                this.view = getLayoutInflater().inflate(R.layout.view_tab_indictor, null);
                this.imageView = (ImageView) this.view.findViewById(R.id.tab_iv_image);
                this.textView = (TextView) this.view.findViewById(R.id.tab_tv_text);
                if(this.title == 0) {
                    this.textView.setVisibility(View.GONE);
                } else {
                    this.textView.setVisibility(View.VISIBLE);
                    this.textView.setText(getTitleString());
                }
                this.imageView.setImageResource(imageNormal);
            }
            return this.view;
        }

        //切换tab的方法
        public void setChecked(boolean isChecked) {
            if(imageView != null) {
                if(isChecked) {
                    imageView.setImageResource(imagePress);
                }else {
                    imageView.setImageResource(imageNormal);
                }
            }
            if(textView != null && title != 0) {
                if(isChecked) {
                    textView.setTextColor(getResources().getColor(R.color.main_botton_text_select));
                } else {
                    textView.setTextColor(getResources().getColor(R.color.main_bottom_text_normal));
                }
            }
        }
    }

    public static void setRecruitment_jsons(String recruitment_jsons) {
        MainActivity.recruitment_jsons = recruitment_jsons;
    }

    public static void setCount3(int count3) {
        MainActivity.count3 = count3;
    }

    /**
     * 使得按下返回就会跟按下Home键操作一致
     * 启动界面在第一次启动会显示出来
     * 按下返回键回到桌面再次进入就不会显示了
     * 除非程序被杀死或退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            Intent i= new Intent(Intent.ACTION_MAIN);  //主启动，不期望接收数据

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);       //新的activity栈中开启，或者已经存在就调到栈前

            i.addCategory(Intent.CATEGORY_HOME);            //添加种类，为设备首次启动显示的页面

            startActivity(i);
        }
        return super.onKeyDown(keyCode, event);
    }
}
