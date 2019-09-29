package com.example.administrator.employmentplatform.User_view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.employmentplatform.R;

import java.util.List;

/**
 *
 * user_school_secondhand 界面RecyclerView适配器
 * Created by Administrator on 2/4/2018.
 */

   public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>{


       private Context mContxt;
       private  List<Picture> mDatas;

       public  interface  OnItemClickListener{
           void onItemClick(View view, int position);
           void onItemLongClick(View view, int position);
       }

       private  OnItemClickListener mOnItemClickListener;

       public  void  setmOnItemClickListener(OnItemClickListener listener){
           this.mOnItemClickListener = listener;
       }

       public RecyclerViewAdapter(Context context,List<Picture> datas){
              this.mContxt = context;
              this.mDatas =datas;

       }
    @Override
    public int getItemCount() {

           return mDatas.size();
    }

       //绑定ViewHolder
    public void onBindViewHolder(final MyViewHolder holder, final int pos) {
          Picture picture = mDatas.get(pos);
          holder.tv.setText(picture.getName());
          holder.iImageView.setImageResource(picture.getImageId());

           if (mOnItemClickListener!=null){


           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                 mOnItemClickListener.onItemClick(holder.itemView,pos);
           }
           });

           holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View view) {
                   mOnItemClickListener.onItemLongClick(holder.itemView,pos);
                   return false;
               }
           });
           }
    }
      //创建ViewHolder
    public MyViewHolder onCreateViewHolder(ViewGroup arg0,int arg1){

        View view =  LayoutInflater.from(mContxt).inflate(R.layout.our_school_secondhand,arg0,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }
}

class  MyViewHolder extends RecyclerView.ViewHolder{

    ImageView iImageView;

       TextView tv;

    public MyViewHolder(View arg0){
        super(arg0);
        tv = arg0.findViewById(R.id.id_tv);
        iImageView = arg0.findViewById(R.id.id_iv);

    }
}
