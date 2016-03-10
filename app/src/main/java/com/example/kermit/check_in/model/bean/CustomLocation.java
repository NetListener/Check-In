package com.example.kermit.check_in.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by kermit on 16/3/6.
 */

/**
 * 用于封装位置信息
 */
public class CustomLocation extends BmobObject{

    private double longitude;
    private double latitude;

    public CustomLocation(){}

    public CustomLocation(double longitude, double latitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
