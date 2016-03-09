package com.example.kermit.check_in.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.kermit.check_in.App;
import com.example.kermit.check_in.model.bean.XLocation;

import java.text.DecimalFormat;

/**
 * Created by kermit on 16/3/6.
 */

/**
 * 用经纬度计算两点距离
 */
public class DistanceProvider {

    private static double DEF_R = 6370693.5; // radius of earth
    //private static DecimalFormat df = new DecimalFormat("#.0000");
    private static final double PI = 3.14159265;
    private static final double EARTH_RADIUS = 6378137;
    private static final double RAD = Math.PI / 180.0;

    private DistanceProvider(){
    }

    public static double getDistance2(XLocation location1, XLocation location2) {

        double latitude1, latitude2, lontitude1, lontitude2;

        latitude1 = (double)(Math.round(location1.getLatitude() * 100))/100;
        latitude2 = (double)(Math.round(location2.getLatitude() * 100))/100;
        lontitude1 = (double)(Math.round(location1.getLontitude() * 100))/100;
        lontitude2 = (double)(Math.round(location2.getLontitude() * 100))/100;

        double radLat1 = latitude1 * RAD;
        double radLat2 = latitude2 * RAD;
        double a = radLat1 - radLat2;
        double b = (lontitude1 - lontitude2) * RAD;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                                                   Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static Double getDistance(XLocation location1, XLocation location2){

        double latitude1, latitude2, lontitude1, lontitude2;
        double distance;
        int la1, la2;
        String strLa1, strLa2;
        strLa1 = String.valueOf(location1.getLatitude());
        strLa2 = String.valueOf(location2.getLatitude());
        la1 = strLa1.length() - strLa1.indexOf(".") - 1;
        la2 = strLa2.length() - strLa2.indexOf(".") - 1;

        latitude1 = location1.getLatitude();
        latitude2 = location2.getLatitude();
        lontitude1 = location1.getLontitude();
        lontitude2 = location2.getLontitude();


        Toast.makeText(App.getInstance(), String.valueOf(latitude1)+" + "+String.valueOf(latitude2)+" + "+String.valueOf(lontitude1)+" + "+String.valueOf(lontitude2), Toast.LENGTH_LONG).show();

        double dLat = Math.toRadians(latitude2 - latitude1);
        double dLng = Math.toRadians(lontitude2 - lontitude1);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        distance = DEF_R * c;

        if ((la1 == 4 && la2 == 6) || (la1 == 6 && la2 == 4)){
            distance -= 400;
        }else if ((la1 == 4 && la2 == 5) || (la1 == 5 && la2 == 4)){
            distance -= 400;
        }else if((la1 == 6 && la2 == 5) || (la1 == 5 && la2 == 6)){
            distance -= 50;
        }

        return distance;
    }
}
