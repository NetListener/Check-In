package com.example.kermit.check_in.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by kermit on 16/3/6.
 */

/**
 * 用于封装位置信息
 * 之前没好好看Bmob文档,发现Bmob支持Google的GeoPoint,后期将会替换
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
