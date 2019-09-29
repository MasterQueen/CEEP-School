package com.example.administrator.employmentplatform.com.example.administrator.MainView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;



import com.example.administrator.employmentplatform.R;

/**
 * Created by Administrator on 3/10/2018.
 */

public class employment_recycleview_adapter extends RecyclerView.Adapter<employment_recycleview_adapter.MyViewHolder>{

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private String[] title;
    private String[] body;
    private int[] image;
    private MyItemClickListener mItemClickListener;

    public employment_recycleview_adapter(Context context, String[] title, String[] body,int [] image) {
        mContext = context;
        this.title = title;
        this.body = body;
        this.image = image;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(mContext).inflate(R.layout.main_view_employment_recycleview,parent,false);
        MyViewHolder holder = new MyViewHolder(view, mItemClickListener);
        return holder;
    }


    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.title.setText(title[position]);
        holder.conter.setText(body[position]);
        holder.image.setImageResource(image[position]);

    }

    @Override
    public int getItemCount() {
        return title == null ? 0 : title.length;
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private MyItemClickListener mListener;
        TextView title;
        TextView conter;

        ImageView image;

        public MyViewHolder(View view, MyItemClickListener myItemClickListener) {

            super(view);

            title = view.findViewById(R.id.employment_recycleview_text_title);
            conter = view.findViewById(R.id.employment_recycleview_text_conter);
            image = view.findViewById(R.id.employment_recycleview_imageview);
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


