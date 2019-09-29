package com.example.administrator.employmentplatform.com.example.administrator.MainView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.employmentplatform.MainActivity;
import com.example.administrator.employmentplatform.R;
import com.example.administrator.employmentplatform.data_treating.Employment_json;
import com.example.administrator.employmentplatform.data_treating.Entrepreneurship_json;
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
 * Created by Administrator on 2/17/2018.
 */

public class Enterprenrurshio extends Fragment {



    private Banner banner;

    private int count1 = MainActivity.count1;

    private employment_recycleview_adapter myadapter;
    private RecyclerView mRecycleView;

    String entrepreneurship_json = MainActivity.entrepreneurship_json;
    private String[] title = new String[count1];
    private String[] room = new String[count1];
    private String[] body = new String[count1];
    private String[] time = new String[count1];
    private String university[] = new String[count1];

    private Object[] image1 = new Object[]{
            R.mipmap.chuangye1,
            R.mipmap.chuangye2,
            R.mipmap.chuangye3
    };
    //创业资讯图片
    private int image2[] = new int[count1];

    /**
     * 获取创业数据库信息，放置在列表中显示
     */
    public void setEntrepreneurship() throws JSONException {
        JSONArray jsonArray = new JSONArray(entrepreneurship_json);
        count1 = jsonArray.length();
        for (int i = 0; i < count1; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            title[i] = jsonObject.getString("title");
            room[i] = jsonObject.getString("room");
            body[i] = jsonObject.getString("body");
            time[i] = jsonObject.getString("sendtime");
            university[i] = jsonObject.getString("university");
            switch (university[i]) {
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
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            setEntrepreneurship();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(entrepreneurship_json);

        View view = inflater.inflate(R.layout.main_view_enterpreneurshio, null);

        android.support.v7.widget.Toolbar toolbar =view.findViewById(R.id.enterpreneurshio_toolbar);
        toolbar.setTitle("创业资讯");
        toolbar.setTitleTextColor(Color.WHITE);

        ((AppCompatActivity) getActivity()). setSupportActionBar(toolbar);

        RefreshLayout refreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);
        //设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshHeader(new BezierRadarHeader(getContext()).setEnableHorizontalDrag(true));
        //设置 Footer 为 球脉冲 样式
        refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                new ReFreshTask().execute();
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


        mRecycleView=view.findViewById(R.id.view_enterpreneurshio);

        mRecycleView.setNestedScrollingEnabled(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        myadapter = new employment_recycleview_adapter(getActivity(),title,room,image2);

        mRecycleView.setAdapter(myadapter);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecycleView.setLayoutManager(layoutManager);

        myadapter.setItemClickListener(new employment_recycleview_adapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),enterprenrurshio_view.class);
                intent.putExtra("title",title[position]);
                intent.putExtra("time",time[position]);
                intent.putExtra("body",body[position]);
                intent.putExtra("room",room[position]);
                intent.putExtra("university",university[position]);
                startActivity(intent);
            }
        });

        mRecycleView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        return view;
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
                Toast.makeText(getContext(), "刷新失败！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

    //设置Item间隔
    class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace=1;

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
}
