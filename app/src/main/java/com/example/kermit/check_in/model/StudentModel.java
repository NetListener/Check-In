package com.example.kermit.check_in.model;

import android.widget.Toast;

import com.example.kermit.check_in.App;
import com.example.kermit.check_in.model.bean.Student;
import com.example.kermit.check_in.model.bean.XLocation;
import com.example.kermit.check_in.utils.DistanceProvider;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by kermit on 16/3/6.
 */
public class StudentModel {

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

    public void sign(XLocation location1, XLocation location2){

        if (mStudent.isSign()){
            return;
        }

        if (location1 != null && location2 != null) {
            if (DistanceProvider.getDistance(location1, location2) < 100) {
                mStudent.setSign(true);
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
}
