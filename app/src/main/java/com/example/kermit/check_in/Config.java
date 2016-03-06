package com.example.kermit.check_in;

import android.location.LocationManager;

/**
 * Created by kermit on 16/3/6.
 */

/**
 * 全局资源配置类
 */
public final class Config {
    public static final String LOCATION_PROVIDER = LocationManager.NETWORK_PROVIDER;
    public static final String APPLICATION_ID = "1b98ba254c56723e5948bccbe4218c22";

    public static final class TableName{
        public static final String MESSAGE = "Message";
        public static final String XLOCATION = "XLocation";
    }
}
