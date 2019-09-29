package com.example.administrator.employmentplatform.com.example.administrator.MainView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.example.administrator.employmentplatform.MainActivity;
import com.example.administrator.employmentplatform.R;
import com.example.administrator.employmentplatform.User_view.User_message;
import com.example.administrator.employmentplatform.data_treating.Employment_json;
import com.example.administrator.employmentplatform.data_treating.Entrepreneurship_json;
import com.example.administrator.employmentplatform.data_treating.Recruitment_json;
import com.example.administrator.employmentplatform.data_treating.Recruitment_jsons;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.administrator.employmentplatform.MainActivity.setCount3;
import static com.example.administrator.employmentplatform.MainActivity.setRecruitment_jsons;


/**
 * Created by Administrator on 2/17/2018.
 */

public class recruitment extends Fragment implements SearchView.OnQueryTextListener {

    private RecyclerView mRecycleView;
    private recruitment_recycleview_adapter myadapter;
    private SearchView searchView;

    private int count2 = MainActivity.count2;
    String recruitment_json = MainActivity.recruitment_json;

//    private String company_name[] = new String[count2];
//    private String school[] = new String[count2];
//    private String company_post [] = new String[count2];
//    private String company_addtef[] = new String[count2];
//    private String recruitment_time[] = new String[count2];
//    private String recruitment_form[] = new String[count2];
//    private String recruitment_city[] = new String[count2];
//    private String profile[] = new String[count2];
//    private String MOTOLEY[] = new String[count2];

    private String company[] = new String[count2];
    private String intro[] = new String[count2];
    private String postion[] = new String[count2];
    private String request[] = new String[count2];
    private String address[] = new String[count2];
    private String time[] = new String[count2];
    private String mark[] = new String[count2];
    private String sendtime[] = new String[count2];
    private String people[] = new String[count2];
    private int [] marklogo = new int[count2];

    /**
     * 获取招聘数据库信息，放置在列表中显示
     */
    public void setRecruitment() throws JSONException {
        JSONArray jsonArray = new JSONArray(recruitment_json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            company[i] = jsonObject.getString("company");
            intro[i] = jsonObject.getString("intro");
            postion[i] = jsonObject.getString("postion");
            request[i] = jsonObject.getString("request");
            address[i] = jsonObject.getString("address");
            time[i] = jsonObject.getString("time");
            mark[i] = jsonObject.getString("mark");
            sendtime[i] = jsonObject.getString("sendtime");
            people[i] = jsonObject.getString("people");
            switch (mark[i]){
                case "校招":
                    marklogo[i] = R.drawable.school;
                    break;
                case "社招":
                    marklogo[i] = R.drawable.social;
                    break;
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        try {
            setRecruitment();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        View view = inflater.inflate(R.layout.main_view_recruitment, null);

        android.support.v7.widget.Toolbar toolbar =view.findViewById(R.id.recruitment_toolbar);
        toolbar.setTitle("招聘信息");
        toolbar.setTitleTextColor(Color.WHITE);

        ((AppCompatActivity) getActivity()). setSupportActionBar(toolbar);



        RefreshLayout refreshLayout = (RefreshLayout)view.findViewById(R.id.recruitment_refreshLayout);
        //设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshHeader(new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true));
        //设置 Footer 为 球脉冲 样式
        refreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                new ReFreshTask1().execute();
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });

        searchView = view.findViewById(R.id.company_search);
        // 为该SearchView组件设置事件监听器
        searchView.setOnQueryTextListener(this);
        // 设置该SearchView显示搜索按钮
        searchView.setSubmitButtonEnabled(true);

        mRecycleView = view.findViewById(R.id.id_recruitement);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        myadapter = new recruitment_recycleview_adapter(getActivity(),company, address, mark, time, marklogo);
        mRecycleView.addItemDecoration(new SpaceItemDecoration(10));

        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.a));
        mRecycleView.addItemDecoration(divider);

        mRecycleView.setAdapter(myadapter);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecycleView.setLayoutManager(layoutManager);


         myadapter.setItemClickListener(new recruitment_recycleview_adapter.MyItemClickListener() {
             @Override
             public void onItemClick(View view, int position) {
                 System.out.println(position);
                 Intent intent = new Intent(getActivity(), recruitment_massage.class);
                 intent.putExtra("company", company[position]);
                 intent.putExtra("intro", intro[position]);
                 intent.putExtra("postion", postion[position]);
                 intent.putExtra("request", request[position]);
                 intent.putExtra("address", address[position]);
                 intent.putExtra("time", time[position]);
                 intent.putExtra("mark", mark[position]);
                 intent.putExtra("sendtime", sendtime[position]);
                 intent.putExtra("people", people[position]);
                 startActivity(intent);
             }
         });

        return view;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        new SearchTask(query).execute();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    public class ReFreshTask1 extends AsyncTask<Void, Void, Boolean> {

        private String employment_json;
        private int count;
        private String entrepreneurship_json;
        private int count1;
        private String recruitment_json;
        private int count2;


        ReFreshTask1() {
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
                if (recruitment_json!=null){
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
                Intent intent = new Intent(getContext(),MainActivity.class);
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

    public class SearchTask extends AsyncTask<Void, Void, Boolean> {

        private String recruitment_jsons;
        private int count3;
        private String msearch;


        SearchTask(String search) {
            msearch = search;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {

                recruitment_jsons = new Recruitment_jsons(msearch).getRecruitment_jsons();
                JSONArray jsonArray3 = new JSONArray(recruitment_jsons);
                count3 = jsonArray3.length();
                if (recruitment_jsons!=null){
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

                setRecruitment_jsons(recruitment_jsons);
                setCount3(count3);

                Intent intent = new Intent(getContext(), recruitments.class);
                intent.putExtra("search",msearch);
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "搜索失败！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

  //设置Item间隔
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
}
