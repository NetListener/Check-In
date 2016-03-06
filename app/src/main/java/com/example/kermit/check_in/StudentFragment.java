package com.example.kermit.check_in;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private XLocation mXLocationS;

    private Button mButton;

    public static StudentFragment newInstance() {

        StudentFragment studentFragment = new StudentFragment();
        return studentFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    mXLocationS = new XLocation(location.getLongitude(), location.getLatitude());
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        StudentModel.getInstance().setStudent(new Student());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student, container, false);
        mButton = (Button) view.findViewById(R.id.btn_get);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentModel.getInstance().sign(mXLocationS);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mLocationManager.requestLocationUpdates(Config.LOCATION_PROVIDER, 0, 0, mLocationListener);

        //开始监听数据变化
        MessageModel.getInstance().startListenDataChange();
    }
}

