package com.example.kermit.check_in.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by kermit on 16/3/6.
 */
public class XLocation extends BmobObject{

    private double lontitude;
    private double latitude;

    public XLocation(){}

    public XLocation(double lontitude, double latitude){
        this.latitude = latitude;
        this.lontitude = lontitude;
    }

    public double getLontitude() {
        return lontitude;
    }

    public void setLontitude(double lontitude) {
        this.lontitude = lontitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
