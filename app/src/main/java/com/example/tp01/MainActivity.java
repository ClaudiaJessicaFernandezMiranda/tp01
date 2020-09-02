package com.example.tp01;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {
    private IntentFilter filtroIntent;
    private BroadcastDinamico dinamico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filtroIntent = new IntentFilter();
        filtroIntent.addAction("android.hardware.usb.action.USB_STATE");
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                !=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1000);
        }
        dinamico = new BroadcastDinamico();
        registerReceiver(dinamico, filtroIntent);
    }

    // @Override
    //protected void onResume() {
    //    super.onResume();
    //    dinamico = new BroadcastDinamico();
    //    registerReceiver(dinamico, filtroIntent);
    //}

    //@Override
    //protected void onPause() {
    //    super.onPause();
    //    unregisterReceiver(dinamico);
    //}
}