package com.example.kermit.check_in.model;

import android.content.Context;

import com.example.kermit.check_in.App;
import com.example.kermit.check_in.model.bean.Message;

import cn.bmob.v3.BmobRealTimeData;
import cn.bmob.v3.listener.ValueEventListener;

/**
 * Created by kermit on 16/3/6.
 */
public class MessageModel {

    private Message mMessage;
    private Context mContext;

    private MessageModel(){
        mContext = App.getInstance();
    }

    static class SingletonHolder{
        private static final MessageModel instance = new MessageModel();
    }

    public static MessageModel getInstance(){
        return SingletonHolder.instance;
    }

    public Message getMessage() {
        return mMessage;
    }

    public void setMessage(Message message) {
        mMessage = message;
    }

    public void start(BmobRealTimeData data, ValueEventListener valueEventListener){
        data.start(mContext, valueEventListener);
    }
}
