package com.example.administrator.employmentplatform.User_view;

/**
 * user_school_secondhand界面图片添加
 * Created by Administrator on 2/2/2018.
 */

public class Picture {
    private String name;
    private int imageId;

    public Picture(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }
    public String getName(){

        return  name;
    }
    public  int getImageId(){

        return imageId;
    }
}
