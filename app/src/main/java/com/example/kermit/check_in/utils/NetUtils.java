package com.example.kermit.check_in.utils;

import com.example.kermit.check_in.Config;

/**
 * Created by kermit on 16/3/8.
 */
public class NetUtils {

    private NetUtils(){}

    public static int getNetworkClass(int networkType) {
        switch (networkType) {
            case Config.SIGNAL.NETWORK_TYPE_GPRS:
            case Config.SIGNAL.NETWORK_TYPE_EDGE:
            case Config.SIGNAL.NETWORK_TYPE_CDMA:
            case Config.SIGNAL.NETWORK_TYPE_1xRTT:
            case Config.SIGNAL.NETWORK_TYPE_IDEN:
                return Config.KIND_OF_SIGNAL.NETWORK_CLASS_2_G;
            case Config.SIGNAL.NETWORK_TYPE_UMTS:
            case Config.SIGNAL.NETWORK_TYPE_EVDO_0:
            case Config.SIGNAL.NETWORK_TYPE_EVDO_A:
            case Config.SIGNAL.NETWORK_TYPE_HSDPA:
            case Config.SIGNAL.NETWORK_TYPE_HSUPA:
            case Config.SIGNAL.NETWORK_TYPE_HSPA:
            case Config.SIGNAL.NETWORK_TYPE_EVDO_B:
            case Config.SIGNAL.NETWORK_TYPE_EHRPD:
            case Config.SIGNAL.NETWORK_TYPE_HSPAP:
                return Config.KIND_OF_SIGNAL.NETWORK_CLASS_3_G;
            case Config.SIGNAL.NETWORK_TYPE_LTE:
                return Config.KIND_OF_SIGNAL.NETWORK_CLASS_4_G;
            default:
                return Config.KIND_OF_SIGNAL.NETWORK_CLASS_UNKNOWN;
        }
    }

}
