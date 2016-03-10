package com.example.kermit.check_in.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.kermit.check_in.App;
import com.example.kermit.check_in.model.bean.Message;
import com.example.kermit.check_in.model.bean.Teacher;
import com.example.kermit.check_in.model.bean.CustomLocation;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by kermit on 16/3/6.
 */
public class TeacherModel {

    private Teacher mTeacher;
    private Context mContext;
    private Message mMessage;

    private TeacherModel(){
        mContext = App.getInstance();
    }

    static class SingletonHolder{
        private static final TeacherModel instance  = new TeacherModel();
    }

    public static TeacherModel getInstance(){
        return SingletonHolder.instance;
    }

    public Teacher getTeacher() {
        return mTeacher;
    }

    public void setTeacher(@NonNull Teacher teacher) {
        mTeacher = teacher;
    }

    public void sendMessage(final String msg, CustomLocation customLocation){

        if (TextUtils.isEmpty(msg) || customLocation == null){
            return;
        }

        mMessage = new Message();
        mMessage.setMsg(msg);
        mMessage.setCustomLocation(customLocation);
        customLocation.save(mContext, new SaveListener() {
            @Override
            public void onSuccess() {
                mMessage.save(mContext, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        mTeacher.setMessage(mMessage);
                        mTeacher.save(mContext, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(mContext, "消息发送成功!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Toast.makeText(mContext, "消息发送失败!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    @Override
                    public void onFailure(int i, String s) {
                    }
                });
            }
            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(mContext, "正在定位哦~", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
