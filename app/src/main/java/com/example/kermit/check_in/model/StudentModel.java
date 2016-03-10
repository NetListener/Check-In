package com.example.kermit.check_in.model;

import android.util.Log;
import android.widget.Toast;

import com.example.kermit.check_in.App;
import com.example.kermit.check_in.model.bean.Student;
import com.example.kermit.check_in.model.bean.CustomLocation;
import com.example.kermit.check_in.utils.DistanceProvider;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by kermit on 16/3/6.
 */
public class StudentModel{

    public static final String TAG = "StudentModel";

    private Student mStudent;
    private StudentModel(){}

    static class SingletonHolder{
        private static final StudentModel instance = new StudentModel();
    }

    public static StudentModel getInstance(){
        return SingletonHolder.instance;
    }

    public Student getStudent() {
        return mStudent;
    }

    public void setStudent(Student student) {
        mStudent = student;
    }

    /**
     * 签到
     * @param sLocation
     */
    public void sign(CustomLocation sLocation){

        mStudent = new Student();

        if (sLocation == null){
            Log.d(TAG, "sLocation is null!");
        }

        if (mCustomLocationTeacher == null){
            Log.d(TAG, "mCustomLocationTeacher is null!");
        }

        if (sLocation != null && mCustomLocationTeacher != null) {
            Toast.makeText(App.getInstance(), DistanceProvider.getDistance(sLocation, mCustomLocationTeacher).toString(), Toast.LENGTH_SHORT).show();
            if (DistanceProvider.getDistance(sLocation, mCustomLocationTeacher) < 250) {
                mStudent.setSign(true);
                Toast.makeText(App.getInstance(), "签到成功!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(App.getInstance(), "请快去上课~", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(App.getInstance(), "请检查网络~", Toast.LENGTH_SHORT).show();
        }
        mStudent.setSign(false);
    }


    public void save(SaveListener listener){
        mStudent.save(App.getInstance(), listener);
    }


    CustomLocation mCustomLocationTeacher;

    /**
     * 用来从Message中获得位置信息
     * @param customLocation
     */
    public void getCustomLocation(CustomLocation customLocation) {
        mCustomLocationTeacher = customLocation;
    }
}
