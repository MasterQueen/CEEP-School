package com.example.administrator.employmentplatform.User_view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.employmentplatform.R;

import java.util.List;

/**
 * Created by Administrator on 3/17/2018.
 */

public class ViewHoderAdapters extends RecyclerView.Adapter{
    private Context context;
    private List<String> datas;
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;

    //构造方法
    public ViewHoderAdapters(Context mainActivity, List<String> data) {
        this.context = mainActivity;
        this.datas = data;
        inflater = LayoutInflater.from(context);
    }

    //定义一个监听接口，里面有两个方法
    public interface OnItemClickListener{
        void onItemOnClickListener(View view,int position);
        void onItemLongOnClickListener(View view,int position);
    }
    //给监听设置一个构造函数，用于main中调用
    public void setOnItemListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_activity,parent,false);
        ViewHoders viewHoders = new ViewHoders(view);
        return viewHoders;
    }
    @Override
    //填充onCreateViewHolder方法返回的holder中的控件
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ViewHoders) holder).tv.setText(datas.get(position));

        if (mOnItemClickListener!=null) {
            //直接给某个空间添加监听
            ((ViewHoders) holder).li_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mOnItemClickListener.onItemOnClickListener(v,position);
                }
            });
            //直接给某个空间添加长按监听
            ((ViewHoders) holder).li_layout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongOnClickListener(v,position);
                    return false;
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义ViewHolder,
    /*
    * RecylerView封装了viewholder的回收复用，也就是说RecylerView标准化了ViewHolder，编写Adapter面向的是ViewHolder而不再是View了
    * */
    class ViewHoders extends RecyclerView.ViewHolder{
        private TextView tv;
        private LinearLayout li_layout;

        public ViewHoders(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.textView);
            li_layout= (LinearLayout) itemView.findViewById(R.id.li_layout);
        }
    }
    //添加一个增加item数据的方法，posion代表你从主类中传过来的值，这个值对应你添加的item在列表中的位置
    public void addData(int position) {
        //保证列表没有数据时，首先添加
        if(datas.size()==0){
            datas.add(0, "我是熊大");
        }else {
            //更新列表
            datas.add(position, "请编辑");
            notifyItemInserted(position);
            notifyItemRangeChanged(position,datas.size());
        }
    }

    public void removeData(int position) {
        //保证列表有数据，并且最少有一条
        if(datas.size()<2&&datas.size()!=0){
            datas.remove(0);
            notifyDataSetChanged();
        }else if(datas.size()==0){//当列表没有数据提示用户，免得造成系统崩溃
            Toast.makeText(context,"搞毛啊，没数据了",Toast.LENGTH_SHORT).show();
        }else{//更新列表
            datas.remove(position);
            notifyDataSetChanged();
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,datas.size());
        }

    }
}

