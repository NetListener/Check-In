package com.example.kermit.check_in;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.bmob.v3.Bmob;

public class StartActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Bmob.initialize(this, Config.APPLICATION_ID);


        StartFragment startFragment = StartFragment.newInstace();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, startFragment, StartFragment.TAG)
                .commit();
    }

}
