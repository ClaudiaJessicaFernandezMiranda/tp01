package com.example.tp01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastDinamico extends BroadcastReceiver {
    String estadoLlamando;
    String estadoUSB;
    String numero;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.hardware.usb.action.USB_STATE")) {
            if (intent.getExtras().getBoolean("connected")) {
                Toast.makeText(context, "USB: CONECTADO", Toast.LENGTH_SHORT).show();
                estadoUSB = "conectado";
            } else {
                Toast.makeText(context, "USB: DESCONECTADO", Toast.LENGTH_SHORT).show();
                estadoUSB = "desconectado";
            }

        }
        if (intent.getAction().equals("tp1.broadcast.RECEIVED")) {
            estadoLlamando="llamando";

        }else estadoLlamando="nollamando";


        if(estadoLlamando.equals("llamando") && estadoUSB.equals("conectado")){
            numero = intent.getStringExtra("telefono");
            if(numero.equals("9111")){
                Toast.makeText(context, "Llamando al 9111, Usb Conectado", Toast.LENGTH_SHORT).show();
            }

        }

    }


}