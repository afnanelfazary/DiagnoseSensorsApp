package com.afnan.diagnosesensorsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class BatteryHealthActivity extends AppCompatActivity {
    private BatteryReceiver mBatteryReceiver = new BatteryReceiver();
    private IntentFilter mIntentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_health);

    }
    @Override
        protected void onResume() {
            super.onResume();
            registerReceiver(mBatteryReceiver, mIntentFilter);
        }

        @Override
        protected void onPause() {
            unregisterReceiver(mBatteryReceiver);
            super.onPause();
        }

}