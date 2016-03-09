package com.example.kermit.check_in;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
import com.example.kermit.check_in.model.bean.XLocation;

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

    private XLocation mXLocation;
    public AMapLocationClient mLocationClient;
    public AMapLocationListener mLocationListener;
    public AMapLocationClientOption mLocationOption;

    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            XLocation xLocation = (XLocation) msg.obj;
            mButton.setEnabled(true);
            showLocation(xLocation);
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
                mXLocation = new XLocation();
                mXLocation.setLatitude(aMapLocation.getLatitude());
                mXLocation.setLontitude(aMapLocation.getLongitude());
                message.obj = mXLocation;
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

    }

    @Override
    public void onResume() {
        super.onResume();
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
                TeacherModel.getInstance().sendMessage("请签到!", mXLocation);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

    }

    private void showLocation(XLocation location) {
        mLongtitudeTextView.setText(String.valueOf(location.getLontitude()));
        mLatitudeTextView.setText(String.valueOf(location.getLatitude()));
    }
}

