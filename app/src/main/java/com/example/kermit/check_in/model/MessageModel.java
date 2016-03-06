package com.example.kermit.check_in.model;

import android.content.Context;

import com.example.kermit.check_in.App;
import com.example.kermit.check_in.Config;
import com.example.kermit.check_in.model.bean.Message;
import com.example.kermit.check_in.model.bean.XLocation;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.BmobRealTimeData;
import cn.bmob.v3.listener.ValueEventListener;

/**
 * Created by kermit on 16/3/6.
 */
public class MessageModel {

    private Message mMessage;
    private Context mContext;
    private BmobRealTimeData mBmobRealTimeData;


    private MessageModel(){
        mContext = App.getInstance();
        mBmobRealTimeData = new BmobRealTimeData();
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

    /**
     * 监听位置表的信息变化
     */
    public void startListenDataChange(){

        mBmobRealTimeData.start(mContext, new ValueEventListener() {
            @Override
            public void onConnectCompleted() {
                if (mBmobRealTimeData.isConnected()){
                    mBmobRealTimeData.subTableUpdate(Config.TableName.XLOCATION);
                }
            }

            @Override
            public void onDataChange(JSONObject jsonObject) {

                if (mBmobRealTimeData.ACTION_UPDATETABLE.equals(jsonObject.opt("action"))){
                    JSONObject data = jsonObject.optJSONObject("data");
                    try {
                        XLocation xLocation =
                                new XLocation(data.getDouble("lontitude"), data.getDouble("latitude"));

                        StudentModel.getInstance().getXLocation(xLocation);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
