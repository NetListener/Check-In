package com.example.kermit.check_in.model;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.kermit.check_in.App;
import com.example.kermit.check_in.model.bean.Message;
import com.example.kermit.check_in.model.bean.Teacher;
import com.example.kermit.check_in.model.bean.XLocation;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by kermit on 16/3/6.
 */
public class TeacherModel {

    private Teacher mTeacher;
    private Context mContext;

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

    public void sendMessage(String msg, XLocation xLocation, final SaveListener saveListener){
        final Message mMessage = new Message();
        mMessage.setMsg(msg);
        mMessage.setXLocation(xLocation);
        xLocation.save(mContext, new SaveListener() {
            @Override
            public void onSuccess() {
                mMessage.save(mContext, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        mTeacher.setMessage(mMessage);
                        mTeacher.save(mContext, saveListener);
                    }
                    @Override
                    public void onFailure(int i, String s) {
                    }
                });
            }
            @Override
            public void onFailure(int i, String s) {
            }
        });
    }
}
