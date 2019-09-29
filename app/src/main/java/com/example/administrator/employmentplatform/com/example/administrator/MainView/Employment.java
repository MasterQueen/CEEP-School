package com.example.administrator.employmentplatform.com.example.administrator.MainView;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.administrator.employmentplatform.LoginActivity;
import com.example.administrator.employmentplatform.MainActivity;
import com.example.administrator.employmentplatform.R;
import com.example.administrator.employmentplatform.data_treating.Employment_json;
import com.example.administrator.employmentplatform.data_treating.Entrepreneurship_json;
import com.example.administrator.employmentplatform.data_treating.Login_data;
import com.example.administrator.employmentplatform.data_treating.Recruitment_json;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * 就业界面内容
 * Created by Administrator on 2/17/2018.
 */

public class Employment extends Fragment implements OnClickListener{

    private Banner banner;
    private View view;
    Button employment_url_0;
    Button employment_url_1;
    Button employment_url_2;
    Button employment_url_3;
    Button employment_url_4;

    int count = MainActivity.count;

    private MyscrollView view_hover;
    private RecyclerView mRecycleView;
    private boolean fixedFlag = false, resetFlag = false;
    private int selectPos = 0;
    private employment_recycleview_adapter myadapter;

    Object[] image1 = new Object[]{
            R.mipmap.imagebarnnertest_a,
            R.mipmap.imagebarnnertest_b,
            R.mipmap.imagebarnnertest_c
    };

    String employment_json = MainActivity.employment_json;
    String [] title = new String[count];
    String [] room = new String[count];
    String [] body = new String[count];
    String [] time = new String[count];
    String [] university = new String[count];

    int [] image2 = new int[count];



    /**
     * 获取就业数据库信息，放置在列表中显示
     */
    public void setEmployment() throws JSONException {
        try {
            JSONArray jsonArray = new JSONArray(employment_json);
            count = jsonArray.length();
            for (int i = 0; i < count; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                title[i] = jsonObject.getString("title");
                room[i] = jsonObject.getString("room");
                body[i] = jsonObject.getString("body");
                time[i] = jsonObject.getString("time");
                university[i] = jsonObject.getString("university");
                switch (university[i]){
                    case "郑州轻工业学院":
                        image2[i] = R.mipmap.zzuli;
                        break;
                    case "北京大学":
                        image2[i] = R.mipmap.beijingdaxue;
                        break;
                    case "清华大学":
                        image2[i] = R.mipmap.qinghua;
                        break;
                    case "北京邮电大学":
                        image2[i] = R.mipmap.beijingyoudian;
                        break;
                    case "中国传媒大学":
                        image2[i] = R.mipmap.zhongguochuanmei;
                        break;
                    case "北京电影学院":
                        image2[i] = R.mipmap.beijingdianying;
                        break;
                    case "北京理工大学":
                        image2[i] = R.mipmap.beijingligong;
                        break;
                    case "中国人民大学":
                        image2[i] = R.mipmap.zhongguorenmin;
                        break;
                    case "中国地质大学":
                        image2[i] = R.mipmap.zhongguodizhi;
                        break;
                    case "复旦大学":
                        image2[i] = R.mipmap.fudan;
                        break;
                    case "上海交通大学":
                        image2[i] = R.mipmap.shanghaijiaotong;
                        break;
                    case "同济大学":
                        image2[i] = R.mipmap.tongji;
                        break;
                    case "上海大学":
                        image2[i] = R.mipmap.shanghai;
                        break;
                    case "华东师范大学":
                        image2[i] = R.mipmap.huadongshifan;
                        break;
                    case "东华大学":
                        image2[i] = R.mipmap.donghua;
                        break;
                    case "郑州大学":
                        image2[i] = R.mipmap.zhengzhou;
                        break;
                    case "河南财经政法大学":
                        image2[i] = R.mipmap.henancaijingzhengfa;
                        break;
                    case "河南工业大学":
                        image2[i] = R.mipmap.henangongye;
                        break;
                    case "中原工学院":
                        image2[i] = R.mipmap.zhongyuangong;
                        break;
                    case "华北水利水电大学":
                        image2[i] = R.mipmap.huabeishuilishuidian;
                        break;
                    case "河南中医药大学":
                        image2[i] = R.mipmap.henanzhongyiyao;
                        break;
                    case "郑州航空工业管理学院":
                        image2[i] = R.mipmap.zhengzhouhangkonggongyeguanli;
                        break;
                    case "湖北大学":
                        image2[i] = R.mipmap.hubei;
                        break;
                    case "武汉大学":
                        image2[i] = R.mipmap.wuhan;
                        break;
                    case "华中科技大学":
                        image2[i] = R.mipmap.huazhongkeji;
                        break;
                    case "武汉轻工大学":
                        image2[i] = R.mipmap.wuhanqinggong;
                        break;
                    case "武汉科技大学":
                        image2[i] = R.mipmap.wuhankeji;
                        break;
                    case "新乡学院":
                        image2[i] = R.mipmap.xinxiang;
                        break;
                    case "新乡医学院":
                        image2[i] = R.mipmap.xinxiangyi;
                        break;
                    case "河南师范大学":
                        image2[i] = R.mipmap.henanshifan;
                        break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("i", "  Fragment1  执行onCreate");
        try {
            setEmployment();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_view_employment, null);

        /*
        使用框架  轮播图
        具体内容请看https://github.com/youth5201314/banner
         */

        banner = (Banner) view.findViewById(R.id.banner);

        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);

        banner.setIndicatorGravity(Banner.CENTER);

        banner.isAutoPlay(true);

        banner.setDelayTime(5000);

        banner.setImages(image1);

        banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {//设置点击事件
            @Override
            public void OnBannerClick(View view, int position) {
//                Toast.makeText(getActivity().getApplicationContext(), "你点击了：" + position, Toast.LENGTH_LONG).show();
            }
        });


         /*
         设置超链接按钮样式
         配置监听
          */

        employment_url_0 = view.findViewById(R.id.employment_url_0);
        employment_url_0.setOnClickListener(this);

        employment_url_1 = view.findViewById(R.id.employment_url_1);
        employment_url_1.setOnClickListener(this);

        employment_url_2 = view.findViewById(R.id.employment_url_2);
        employment_url_2.setOnClickListener(this);

        employment_url_3 = view.findViewById(R.id.employment_url_3);
        employment_url_3.setOnClickListener(this);

        employment_url_4 = view.findViewById(R.id.employment_url_4);
        employment_url_4.setOnClickListener(this);

        RefreshLayout refreshLayout = (RefreshLayout)view.findViewById(R.id.employment_refreshLayout);
        //设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshHeader(new BezierRadarHeader(getContext()).setEnableHorizontalDrag(true));
        //设置 Footer 为 球脉冲 样式
        refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                new ReFreshTask().execute();
                System.out.print("eeeeee");
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
        refreshLayout.setEnableLoadMore(true);

        initview();
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
                       divider.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.a));
                       mRecycleView.addItemDecoration(divider);

        return view;
    }


    public void  initview(){

        mRecycleView=view.findViewById(R.id.id_employment_recycleview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        myadapter = new employment_recycleview_adapter(getActivity(),title,room,image2);

        mRecycleView.setAdapter(myadapter);

        //解决只加载部分的问题
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setNestedScrollingEnabled(false);


        myadapter.setItemClickListener(new employment_recycleview_adapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),employment_view.class);
                intent.putExtra("title",title[position]);
                intent.putExtra("room",room[position]);
                intent.putExtra("body",body[position]);
                intent.putExtra("time",time[position]);
                intent.putExtra("university",university[position]);
                intent.putExtra("count",count);
                startActivity(intent);
            }
        });


    }

    /**
     * 设置超链接监听
     * @param v
     */
    public void onClick(View v) {

        int id  = v.getId();

        switch (id) {

            case R.id.employment_url_0:

                Uri uri0 = Uri.parse("http://www.chinahr.com");

                Intent intent0 = new Intent(Intent.ACTION_VIEW, uri0);

                startActivity(intent0);
                break;

            case R.id.employment_url_1:

                Uri uri1 = Uri.parse("http://www.51job.com/?from=baidupz");

                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);

                startActivity(intent1);
                break;

            case R.id.employment_url_2:


                Uri uri2 = Uri.parse("https://www.liepin.com/event/landingpage/search_salary/?mscid=s_00_000&utm_source=baidu&utm_medium=cpc&utm_campaign=%E5%85%A8%E5%9B%BD%2D%E4%BA%BA%E6%89%8D%E7%BD%91&utm_content=%E9%80%9A%E7%94%A8%E4%BA%BA%E6%89%8D%E7%BD%91%2D%E7%B2%BE&utm_term=%E4%B8%AD%E5%8D%8E%E4%BA%BA%E6%89%8D%E7%BD%91");

                Intent intent2 = new Intent(Intent.ACTION_VIEW, uri2);

                startActivity(intent2);

                break;

            case R.id.employment_url_3:

                Uri uri3 = Uri.parse("http://ts.zhaopin.com/jump/index.html?sid=121127632&site=yi_dqty_000020");

                Intent intent3 = new Intent(Intent.ACTION_VIEW, uri3);

                startActivity(intent3);

                break;

            case R.id.employment_url_4 :

                Uri uri4 = Uri.parse("http://zz.58.com/job.shtml?PGTID=0d100000-0015-67c7-fafd-0c6aa0dccb87&ClickID=2");

                Intent intent4 = new Intent(Intent.ACTION_VIEW, uri4);

                startActivity(intent4);


            default:
                break;
        }

    }

    public class ReFreshTask extends AsyncTask<Void, Void, Boolean> {

        private String employment_json;
        private int count;
        private String entrepreneurship_json;
        private int count1;
        private String recruitment_json;
        private int count2;


        ReFreshTask() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                employment_json = new Employment_json(MainActivity.local).getEmployment_json();
                JSONArray jsonArray = new JSONArray(employment_json);
                count = jsonArray.length();
                entrepreneurship_json = new Entrepreneurship_json().getEntrepreneurship_json();
                JSONArray jsonArray1 = new JSONArray(entrepreneurship_json);
                count1 = jsonArray1.length();
                recruitment_json = new Recruitment_json().getRecruitment_json();
                JSONArray jsonArray2 = new JSONArray(recruitment_json);
                count2 = jsonArray2.length();
                if (employment_json!=null){
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
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("id", MainActivity.id);
                intent.putExtra("password", MainActivity.password);
                intent.putExtra("sex",MainActivity.sex);
                intent.putExtra("local",MainActivity.local);
                intent.putExtra("age",MainActivity.age);
                intent.putExtra("nickname",MainActivity.nickname);
                intent.putExtra("university",MainActivity.university);
                intent.putExtra("employment_json",employment_json);
                intent.putExtra("count",count);
                intent.putExtra("entrepreneurship_json",entrepreneurship_json);
                intent.putExtra("count1",count1);
                intent.putExtra("recruitment_json",recruitment_json);
                intent.putExtra("count2",count2);
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "修改失败！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

}
class SpaceItemDecoration extends RecyclerView.ItemDecoration {


                int mSpace;



                @Override


                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {


                            super.getItemOffsets(outRect, view, parent, state);


                           outRect.left = mSpace;


                            outRect.right = mSpace;


              outRect.bottom = mSpace;


                           if (parent.getChildAdapterPosition(view) == 0) {


                                 outRect.top = mSpace;


                             }





                    }





               public SpaceItemDecoration(int space) {


                this.mSpace = space;


                       }


   }




