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
import com.amap.api.location.LocationManagerBase;
import com.example.kermit.check_in.model.MessageModel;
import com.example.kermit.check_in.model.StudentModel;
import com.example.kermit.check_in.model.bean.Student;
import com.example.kermit.check_in.model.bean.XLocation;

/**
 * Created by kermit on 16/3/4.
 */

/**
 * 此碎片用来获取学生的位置
 * 接受签到信息
 */
public class StudentFragment extends Fragment {

    public static final String TAG = "StudentFragment";

    private XLocation mXLocationS;

    private AMapLocationClient mLocationClient;
    private AMapLocationListener mLocationListener;
    private AMapLocationClientOption mLocationOption;

    private Button mButton;
    private TextView mTextViewLontitude, mTextViewLatitude;

    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mXLocationS = (XLocation) msg.obj;
            showLocation(mXLocationS);
        }
    };

    public static StudentFragment newInstance() {
        StudentFragment studentFragment = new StudentFragment();
        return studentFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationClient = new AMapLocationClient(getContext().getApplicationContext());
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                Message message = mHandler.obtainMessage();
                mXLocationS = new XLocation(aMapLocation.getLongitude(), aMapLocation.getLatitude());
                message.obj =  mXLocationS;
                message.sendToTarget();
            }
        };

        mLocationOption = new AMapLocationClientOption();
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


        StudentModel.getInstance().setStudent(new Student());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student, container, false);

        mTextViewLatitude = (TextView) view.findViewById(R.id.tv_fragment_student_latitude);
        mTextViewLontitude = (TextView) view.findViewById(R.id.tv_fragment_student_longtitude);

        mButton = (Button) view.findViewById(R.id.btn_get);
        mButton.setEnabled(false);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentModel.getInstance().sign(mXLocationS);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //启动定位
        mLocationClient.setLocationListener(mLocationListener);
        mLocationClient.startLocation();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MessageModel.getInstance().startListenDataChange(mButton);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

    }

    private void showLocation(XLocation location) {
        mTextViewLontitude.setText(String.valueOf(location.getLontitude()));
        mTextViewLatitude.setText(String.valueOf(location.getLatitude()));
    }
}

