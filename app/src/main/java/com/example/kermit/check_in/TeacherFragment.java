package com.example.kermit.check_in;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.kermit.check_in.model.TeacherModel;
import com.example.kermit.check_in.model.bean.Teacher;
import com.example.kermit.check_in.model.bean.CustomLocation;

/**
 * Created by kermit on 16/3/4.
 */

/**
 * 此碎片用来获取教室位置
 * 发送签到信息
 */
public class TeacherFragment extends Fragment {

    public static final String TAG = "TeacherFragment";

    private Button mButton;
    private TextView mLongtitudeTextView, mLatitudeTextView;

    private CustomLocation mCustomLocation;
    public AMapLocationClient mLocationClient;
    public AMapLocationListener mLocationListener;
    public AMapLocationClientOption mLocationOption;

    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CustomLocation customLocation = (CustomLocation) msg.obj;
            mButton.setEnabled(true);
            showLocation(customLocation);
        }
    };

    public static TeacherFragment newInstance() {
        TeacherFragment teacherFragment = new TeacherFragment();
        return teacherFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {

                Message message = mHandler.obtainMessage();
                mCustomLocation = new CustomLocation();
                mCustomLocation.setLatitude(aMapLocation.getLatitude());
                mCustomLocation.setLongitude(aMapLocation.getLongitude());
                message.obj = mCustomLocation;
                message.sendToTarget();

            }
        };
        mLocationClient = new AMapLocationClient(getContext().getApplicationContext());

        mLocationOption = new AMapLocationClientOption();
        //初始化定位参数
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(0);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位

        mLocationClient.setLocationListener(mLocationListener);
        mLocationClient.startLocation();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher, container, false);
        mButton = (Button) view.findViewById(R.id.btn_send);
        mLatitudeTextView = (TextView) view.findViewById(R.id.tv_fragment_teacher_latitude);
        mLongtitudeTextView = (TextView) view.findViewById(R.id.tv_fragment_teacher_longtitude);

        mButton.setEnabled(false);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeacherModel.getInstance().setTeacher(new Teacher());
                TeacherModel.getInstance().sendMessage("请签到!", mCustomLocation);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void showLocation(CustomLocation location) {
        mLongtitudeTextView.setText(String.valueOf(location.getLongitude()));
        mLatitudeTextView.setText(String.valueOf(location.getLatitude()));
    }
}

