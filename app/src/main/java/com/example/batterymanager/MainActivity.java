package com.example.batterymanager;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout rootLayout;
    private BatteryReceiver batteryReceiver;
    private IntentFilter intentFilter;
    public TextView percentageTv;
    public TextView chargingStatusTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        percentageTv = findViewById(R.id.percentageTv);
        chargingStatusTv = findViewById(R.id.chargingStatusTv);

        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);
        intentFilter.addAction(Intent.ACTION_BATTERY_OKAY);

        rootLayout = findViewById(R.id.rootLayout);
        batteryReceiver = new BatteryReceiver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(batteryReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(batteryReceiver);
    }

    public void changeBackgroundColor(String color) {
        rootLayout.setBackgroundColor(Color.parseColor(color));
    }
}