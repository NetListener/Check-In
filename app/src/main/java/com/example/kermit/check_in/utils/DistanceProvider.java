package com.example.kermit.check_in.utils;

import com.example.kermit.check_in.model.bean.XLocation;

/**
 * Created by kermit on 16/3/6.
 */
public class DistanceProvider {

    private static double DEF_PI = 3.14159265359; // PI
    private static double DEF_2PI = 6.28318530712; // 2*PI
    private static double DEF_PI180 = 0.01745329252; // PI/180.0
    private static double DEF_R = 6370693.5; // radius of earth

    private DistanceProvider(){}

    public static Double getDistance(XLocation location1, XLocation location2){

        double lonDegree1, lonDegree2, latDegree1, latDegree2;
        double distance;

        lonDegree1 = location1.getLontitude() * DEF_PI180;
        lonDegree2 = location2.getLontitude() * DEF_PI180;

        latDegree1 = location1.getLatitude() * DEF_PI180;
        latDegree2 = location2.getLatitude() * DEF_PI180;

        distance = Math.sin(latDegree1) * Math.sin(latDegree1) +
                Math.cos(latDegree1) * Math.cos(latDegree2) * Math.cos(lonDegree1-lonDegree2);

        if (distance > 1.0){
            distance = 1.0;
        }else if (distance < -1.0){
            distance = -1.0;
        }

        distance = DEF_R * Math.acos(distance);

        return distance;
    }
}
