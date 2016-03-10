package com.example.kermit.check_in.utils;

import com.example.kermit.check_in.model.bean.CustomLocation;

/**
 * Created by kermit on 16/3/6.
 */

/**
 * 用经纬度计算两点距离
 */
public class DistanceProvider {

    private static double DEF_R = 6370693.5; // radius of earth

    private DistanceProvider(){}

    public static Double getDistance(CustomLocation location1, CustomLocation location2){

        double latitude1, latitude2;
        double distance;

        latitude1 = location1.getLatitude();
        latitude2 = location2.getLatitude();

        double dLat = Math.toRadians(latitude2 - latitude1);
        double dLng = Math.toRadians(latitude2 - latitude1);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        distance = DEF_R * c;

        return distance;
    }
}
