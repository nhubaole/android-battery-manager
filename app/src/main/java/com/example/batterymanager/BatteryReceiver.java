package com.example.batterymanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

public class BatteryReceiver extends BroadcastReceiver {
    private final MainActivity activity;

    public BatteryReceiver(MainActivity activity) {
        this.activity = activity;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())){
            activity.percentageTv.setText(String.valueOf(intent.getIntExtra("level", 0)) + "%");
            switch (intent.getIntExtra("status",-1)){
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    activity.chargingStatusTv.setText("Unknown");
                    break;
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    activity.chargingStatusTv.setText("Charging");
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    activity.chargingStatusTv.setText("Discharging");
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    activity.chargingStatusTv.setText("Not Charging");
                    break;
                case BatteryManager.BATTERY_STATUS_FULL:
                    activity.chargingStatusTv.setText("Full");
                    break;
                default:
                    activity.chargingStatusTv.setText("Null");
                    break;
            }
        }
        else if (Intent.ACTION_BATTERY_LOW.equals(intent.getAction())){
            activity.changeBackgroundColor("#ffda10");
        }
        else if (Intent.ACTION_BATTERY_OKAY.equals(intent.getAction())){
            activity.changeBackgroundColor("#82d152");
        }
    }
}
