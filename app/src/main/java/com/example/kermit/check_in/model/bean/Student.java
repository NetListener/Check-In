package com.example.kermit.check_in.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by kermit on 16/3/6.
 */
public class Student extends BmobObject{

    boolean isSign;

    public boolean isSign() {
        return isSign;
    }

    public void setSign(boolean sign) {
        isSign = sign;
    }

}
