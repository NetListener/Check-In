package com.example.kermit.check_in.model.bean;


import cn.bmob.v3.BmobObject;

/**
 * Created by kermit on 16/3/6.
 */

/**
 * 将位置封装进签到信息中
 */
public class Message extends BmobObject{

    private String msg;
    private CustomLocation mCustomLocation;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CustomLocation getCustomLocation() {
        return mCustomLocation;
    }

    public void setCustomLocation(CustomLocation customLocation) {
        mCustomLocation = customLocation;
    }
}
