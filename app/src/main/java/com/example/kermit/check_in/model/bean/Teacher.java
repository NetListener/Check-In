package com.example.kermit.check_in.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by kermit on 16/3/6.
 */
public class Teacher extends BmobObject{

    private Message mMessage;


    public Message getMessage() {
        return mMessage;
    }

    public void setMessage(Message message) {
        mMessage = message;
    }
}
