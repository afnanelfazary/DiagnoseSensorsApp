package com.afnan.diagnosesensorsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.widget.ImageView;
import android.widget.TextView;

class BatteryReceiver extends BroadcastReceiver {
    //Battery Health
     IntentFilter intentfilter;
    int deviceHealth;
    String currentBatteryHealth="Battery Health ";
    int batteryLevel;
        @Override
        public void onReceive(Context context, Intent intent) {

            TextView statusLabel = ((BatteryHealthActivity)context).findViewById(R.id.statusLabel);
            TextView percentageLabel = ((BatteryHealthActivity)context).findViewById(R.id.percentageLabel);
            ImageView batteryImage = ((BatteryHealthActivity)context).findViewById(R.id.batteryImage);
           TextView  textview2= ((BatteryHealthActivity)context).findViewById(R.id.textViewBatteryHealth);
            intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

            String action = intent.getAction();

            if (action != null && action.equals(Intent.ACTION_BATTERY_CHANGED)) {

                // Status
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                String message = "";

                switch (status) {
                    case BatteryManager.BATTERY_STATUS_FULL:
                        message = "Full";
                        break;
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        message = "Charging";
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        message = "Discharging";
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        message = "Not charging";
                        break;
                    case BatteryManager.BATTERY_STATUS_UNKNOWN:
                        message = "Unknown";
                        break;
                }
                statusLabel.setText(message);


                // Percentage
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int percentage = level * 100 / scale;
                percentageLabel.setText(percentage + "%");


                // Image
                Resources res = context.getResources();

                if (percentage >= 90) {
                    batteryImage.setImageDrawable(res.getDrawable(R.drawable.b100));

                } else if (90 > percentage && percentage >= 65) {
                    batteryImage.setImageDrawable(res.getDrawable(R.drawable.b75));

                } else if (65 > percentage && percentage >= 40) {
                    batteryImage.setImageDrawable(res.getDrawable(R.drawable.b50));

                } else if (40 > percentage && percentage >= 15) {
                    batteryImage.setImageDrawable(res.getDrawable(R.drawable.b25));

                } else {
                    batteryImage.setImageDrawable(res.getDrawable(R.drawable.b0));

                }

            }


            deviceHealth = intent.getIntExtra(BatteryManager.EXTRA_HEALTH,0);

            if(deviceHealth == BatteryManager.BATTERY_HEALTH_COLD){

                textview2.setText(currentBatteryHealth+" = Cold");
            }

            if(deviceHealth == BatteryManager.BATTERY_HEALTH_DEAD){

                textview2.setText(currentBatteryHealth+" = Dead");
            }

            if (deviceHealth == BatteryManager.BATTERY_HEALTH_GOOD){

                textview2.setText(currentBatteryHealth+" = Good");
            }

            if(deviceHealth == BatteryManager.BATTERY_HEALTH_OVERHEAT){

                textview2.setText(currentBatteryHealth+" = OverHeat");
            }

            if (deviceHealth == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE){

                textview2.setText(currentBatteryHealth+" = Over voltage");
            }

            if (deviceHealth == BatteryManager.BATTERY_HEALTH_UNKNOWN){

                textview2.setText(currentBatteryHealth+" = Unknown");
            }
            if (deviceHealth == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE){

                textview2.setText(currentBatteryHealth+" = Unspecified Failure");
        }
    }}