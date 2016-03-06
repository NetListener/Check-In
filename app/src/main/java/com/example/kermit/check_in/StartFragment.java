package com.example.kermit.check_in;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by kermit on 16/3/5.
 */
public class StartFragment extends Fragment implements View.OnClickListener{

    public static final String TAG = "StartFragment";

    private Button mButtonTeacher, mButtonStudent;

    public static StartFragment newInstace(){
        StartFragment startFragment = new StartFragment();
        return startFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_start, container, false);

        mButtonStudent = (Button) view.findViewById(R.id.btn_student);
        mButtonTeacher = (Button) view.findViewById(R.id.btn_teacher);

        mButtonTeacher.setOnClickListener(this);
        mButtonStudent.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        Fragment fragment = null;
        switch (v.getId()){
            case R.id.btn_teacher:
                fragment = TeacherFragment.newInstance();
                break;
            case R.id.btn_student:
                fragment = StudentFragment.newInstance();
                break;
        }

        Fragment startFragment = getActivity()
                .getSupportFragmentManager()
                .findFragmentByTag(TAG);

        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .hide(startFragment)
                .add(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
