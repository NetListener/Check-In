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


    public static final class THIRDPART_KEY{
        public static final String APPLICATION_ID = "1b98ba254c56723e5948bccbe4218c22";
    }

    public static final class SIGNAL{
        /** Network type is unknown */
        public static final int NETWORK_TYPE_UNKNOWN = 0;
        /** Current network is GPRS */
        public static final int NETWORK_TYPE_GPRS = 1;
        /** Current network is EDGE */
        public static final int NETWORK_TYPE_EDGE = 2;
        /** Current network is UMTS */
        public static final int NETWORK_TYPE_UMTS = 3;
        /** Current network is CDMA: Either IS95A or IS95B*/
        public static final int NETWORK_TYPE_CDMA = 4;
        /** Current network is EVDO revision 0*/
        public static final int NETWORK_TYPE_EVDO_0 = 5;
        /** Current network is EVDO revision A*/
        public static final int NETWORK_TYPE_EVDO_A = 6;
        /** Current network is 1xRTT*/
        public static final int NETWORK_TYPE_1xRTT = 7;
        /** Current network is HSDPA */
        public static final int NETWORK_TYPE_HSDPA = 8;
        /** Current network is HSUPA */
        public static final int NETWORK_TYPE_HSUPA = 9;
        /** Current network is HSPA */
        public static final int NETWORK_TYPE_HSPA = 10;
        /** Current network is iDen */
        public static final int NETWORK_TYPE_IDEN = 11;
        /** Current network is EVDO revision B*/
        public static final int NETWORK_TYPE_EVDO_B = 12;
        /** Current network is LTE */
        public static final int NETWORK_TYPE_LTE = 13;
        /** Current network is eHRPD */
        public static final int NETWORK_TYPE_EHRPD = 14;
        /** Current network is HSPA+ */
        public static final int NETWORK_TYPE_HSPAP = 15;


    }

    public static final class KIND_OF_SIGNAL{
        /** Unknown network class. {@hide} */
        public static final int NETWORK_CLASS_UNKNOWN = 0;
        /** Class of broadly defined "2G" networks. {@hide} */
        public static final int NETWORK_CLASS_2_G = 1;
        /** Class of broadly defined "3G" networks. {@hide} */
        public static final int NETWORK_CLASS_3_G = 2;
        /** Class of broadly defined "4G" networks. {@hide} */
        public static final int NETWORK_CLASS_4_G = 3;
    }


    public static final class TableName{
        public static final String MESSAGE = "Message";
        public static final String CUSTOMLOCATION = "CustomLocation";
    }
}
