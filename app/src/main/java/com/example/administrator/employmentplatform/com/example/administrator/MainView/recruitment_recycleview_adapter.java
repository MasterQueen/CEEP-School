package com.example.administrator.employmentplatform.com.example.administrator.MainView;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.employmentplatform.R;

/**
 * Created by Administrator on 3/8/2018.
 */

public class recruitment_recycleview_adapter extends RecyclerView.Adapter<recruitment_recycleview_adapter.MyViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private String[] company;
    private String[] address;
//    private String[] company_post;
    private String[] mark;
    private String[] time;
//    private String[] company_adress3;
//    private String[] company_attdef;
//    private int[] company_logo={R.drawable.alibaba,R.drawable.baidu,R.drawable.yanghang,R.drawable.microsoft,R.drawable.google,R.drawable.tengxun};
    private int[] marklogo;
    private MyItemClickListener mItemClickListener;

    public recruitment_recycleview_adapter(Context context, String[] company, String[] address, String[] mark, String[] time, int[] marklogo) {
        mContext = context;
        this.company = company;
        this.address = address;
        this.mark = mark;
        this.time = time;
        this.marklogo = marklogo;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(mContext).inflate(R.layout.main_view_recruitment_recycleview,parent,false);
        MyViewHolder holder = new MyViewHolder(view, mItemClickListener);
        return holder;
    }


    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.company_name.setText(company[position]);
        holder.school.setText("宣讲地点 | " + address[position]);
//        holder.company_post.setText(company_post[position]);
        holder.company_mark.setText(mark[position]);
        holder.company_time.setText(time[position].substring(0,10));
//        holder.company_adress3.setText(company_adress3[position]);
//        holder.company_attdef.setText(company_attdef[position]);
        //添加图片logo
        holder.marklogo.setImageResource(marklogo[position]);
//        holder.company_logo.setImageResource(company_logo[position]);

    }

    @Override
    public int getItemCount() {
        return company == null ? 0 : company.length;
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private MyItemClickListener mListener;
        TextView company_name;

        TextView school;

//        TextView company_post;

        TextView company_time;

        TextView company_mark;

//        TextView company_adress3;

//        TextView company_attdef;

        ImageView marklogo;

        public MyViewHolder(View view, MyItemClickListener myItemClickListener) {
            super(view);
            company_name = view.findViewById(R.id.company_name);
            school = view.findViewById(R.id.school);
//            company_post = view.findViewById(R.id.company_post);
            company_time = view.findViewById(R.id.company_time);
            company_mark = view.findViewById(R.id.company_mark);
//            company_adress3 = view.findViewById(R.id.company_adress3);
//            company_attdef  =view.findViewById(R.id.company_attdef);
            marklogo = view.findViewById(R.id.marklogo);
            view.setOnClickListener(this);
            this.mListener = myItemClickListener;



        }

        /**
         * 实现OnClickListener接口重写的方法
         *
         * @param v
         */
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getPosition());
            }

        }

    }

    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     *
     * 在activity里面adapter就是调用的这个方法,将点击事件监听传递过来,并赋值给全局的监听
     *
     *@param myItemClickListener
     *
     */
    public void setItemClickListener(MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }

}

