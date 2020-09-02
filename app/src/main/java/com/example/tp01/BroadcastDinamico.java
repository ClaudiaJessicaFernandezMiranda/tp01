package com.example.tp01;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class BroadcastDinamico extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.hardware.usb.action.USB_STATE")) {
            if (intent.getExtras().getBoolean("connected")) {

                Intent llamado = new Intent(Intent.ACTION_CALL);
                llamado.setData(Uri.parse("tel:" + 9111));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                Toast.makeText(context, "USB: CONECTADO, LLAMANDO", Toast.LENGTH_SHORT).show();
                context.startActivity(llamado);
            } else {
                Toast.makeText(context, "USB: DESCONECTADO, NO LLAMA", Toast.LENGTH_SHORT).show();
            }

        }
    }


}