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
import android.widget.TextView;

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

    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private XLocation mXLocation;


    public static TeacherFragment newInstance() {
        TeacherFragment teacherFragment = new TeacherFragment();
        return teacherFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
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

        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null){
                    mXLocation = new XLocation();
                    mXLocation.setLatitude(location.getLatitude());
                    mXLocation.setLontitude(location.getLongitude());
                    mButton.setEnabled(true);
                }
                showLocation(location);
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

        mLocationManager.requestLocationUpdates(Config.LOCATION_PROVIDER, 0, 0, mLocationListener);
    }

    private void showLocation(Location location) {
        mLongtitudeTextView.setText(String.valueOf(location.getLongitude()));
        mLatitudeTextView.setText(String.valueOf(location.getLatitude()));
    }
}

