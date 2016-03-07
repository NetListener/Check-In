package com.example.kermit.check_in.utils;

import com.example.kermit.check_in.model.bean.XLocation;

/**
 * Created by kermit on 16/3/6.
 */

/**
 * 用经纬度计算两点距离
 */
public class DistanceProvider {

    private static double DEF_PI = 3.14159265359; // PI
    private static double DEF_R = 6370693.5; // radius of earth

    private DistanceProvider(){}

    public static Double getDistance(XLocation location1, XLocation location2){

        double lontitude1, lontitude2, latitude1, latitude2;
        double distance;

        lontitude1 = location1.getLontitude();
        lontitude2 = location2.getLontitude();

        latitude1 = location1.getLatitude();
        latitude2 = location2.getLatitude();

        double radlat1 = rad(latitude1);
        double radlat2 = rad(latitude2);
        double a = rad(latitude1) - rad(latitude2);
        double b = rad(lontitude1) - rad(lontitude2);

        distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2), 2) +
        Math.cos(radlat1) * Math.cos(radlat2) * Math.pow(Math.sin(b/2), 2)));

        distance = distance * DEF_R;
        distance = Math.round(distance * 10000)/10000;

        return distance;
    }

    public static double rad(double m){
        return m * DEF_PI / 180;
    }
}
