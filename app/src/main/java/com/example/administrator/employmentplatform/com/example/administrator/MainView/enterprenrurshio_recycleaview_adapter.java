package com.example.administrator.employmentplatform.com.example.administrator.MainView;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.employmentplatform.R;



/**
 * Created by Administrator on 3/3/2018.
 */

public class enterprenrurshio_recycleaview_adapter extends RecyclerView.Adapter<NormalViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private  String[] mTitle;
    private String[] conter;
    private  int [] mPic;

    public enterprenrurshio_recycleaview_adapter(Context context,String[]title,int[] pic,String[]conter){
        this.mContext = context;
        this.mTitle = title;
        this.mPic = pic;
       this.conter = conter;
        mLayoutInflater = LayoutInflater.from(context);
    }




    @Override
    public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(mLayoutInflater.inflate(R.layout.main_view_enterprenrurshio_recycleview,parent,false));
    }

    @Override
    public void onBindViewHolder(NormalViewHolder holder, int position) {
        holder.mTextView.setText(mTitle[position]);
        holder.mImageView.setBackgroundResource(mPic[position]);
        holder.conter.setText(conter[position]);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }

    @Override
    public int getItemCount() {
        return mTitle==null ? 0 : mTitle.length;
    }
}
     class NormalViewHolder extends RecyclerView.ViewHolder {

    TextView mTextView;
    CardView mCardView;
    ImageView mImageView;
    TextView conter;

    public NormalViewHolder(View itemView) {
        super(itemView);
        conter = (TextView) itemView.findViewById(R.id.id_recyclerView_text);
        mTextView = itemView.findViewById(R.id.id_recyclerView_title);
        mCardView = (CardView) itemView.findViewById(R.id.cv_item);
        mImageView = (ImageView) itemView.findViewById(R.id.id_recyclerView_imageview);
    }
}