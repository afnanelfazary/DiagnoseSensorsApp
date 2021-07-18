package com.afnan.diagnosesensorsapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    //os Version
TextView textView,tv_Imei;
Build build;
Build.VERSION version;
String information;
// IMEI
     TelephonyManager telephonyManager;

     String deviceID = null;
//Battery Health
TextView textview2;
    Button button;
    IntentFilter intentfilter;
  //Text Recognition
    Button txtRecoginition_btn , lightSensor_btn;

    //Motion Sensor
    Button motionSensor_btn;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
         textView = findViewById(R.id.tv);
        saveInfo();
        textView.setText(information);
        txtRecoginition_btn = findViewById(R.id.txtRecoginition_btn);
        lightSensor_btn = findViewById(R.id.lightSensor_btn);
        motionSensor_btn = findViewById(R.id.motionSensor_btn);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PackageManager.PERMISSION_GRANTED);
         tv_Imei = findViewById(R.id.tv_imei);
            telephonyManager = (TelephonyManager) this.getSystemService(this.TELEPHONY_SERVICE);
                          //if API<=29
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
             deviceID = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        }

        else {

            deviceID = telephonyManager.getImei();
         }

        tv_Imei.setText(deviceID);



        //Battery Health
        button = (Button)findViewById(R.id.buttonBatteryHealth);
        textview2= (TextView)findViewById(R.id.textViewBatteryHealth);

        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
//Health Button
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent  intent = new Intent(getApplicationContext(), BatteryHealthActivity.class);
                startActivity(intent);            }
        });

        //Text Recognition
        txtRecoginition_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent  intent = new Intent(getApplicationContext(), TextRecognitionActivity.class);
                startActivity(intent);            }
        });
//Light Sensor
        lightSensor_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent  intent = new Intent(getApplicationContext(), LightSensorActivity.class);
                startActivity(intent);            }
        });
        //Motion Sensor
        motionSensor_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent  intent = new Intent(getApplicationContext(), MotionSensorActivity.class);
                startActivity(intent);
            }
        });

    }





    private void saveInfo() {
         information = "Brand : " + build.BRAND + "\n" +
                 "Product :" + build.PRODUCT + "\n" +
                 "Hardware :" + build.HARDWARE + "\n" +
                 "Device : " + build.DEVICE + "\n" +
                 "Model :" + build.MODEL + "\n" +
                 "Manufacturer :" + build.MANUFACTURER + "\n" +
                 "Security Patch :" + version.SECURITY_PATCH + "\n" +
                 "Version  Release :" + version.RELEASE + "\n" +
                 "SDK : " + version.SDK_INT + "\n";
     } }

