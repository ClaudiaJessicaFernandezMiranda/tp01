package com.example.tp01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText etNum;
    private ImageButton btnLlamado;
    private IntentFilter filtroIntent;
    private BroadcastDinamico dinamico = new BroadcastDinamico();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                !=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1000);
        }

        filtroIntent = new IntentFilter();
        filtroIntent.addAction("tp1.broadcast.RECEIVED");
        filtroIntent.addAction("android.hardware.usb.action.USB_STATE");
        etNum = findViewById(R.id.etTelefono);
        btnLlamado = findViewById(R.id.btnLlamar);

        btnLlamado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = etNum.getText().toString();
                if(numero.matches("[0-9]{3,8}")){
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + numero));
                    Intent llamado = new Intent("tp1.broadcast.RECEIVED");
                    llamado.putExtra("telefono", numero);
                    if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    sendBroadcast(llamado);
                    startActivity(intent);
                }
                else Toast.makeText(getApplication(), "Ingresa numero, de 3 o mas caracteres", Toast.LENGTH_SHORT).show();

            }
        });
        registerReceiver(dinamico, filtroIntent);
    }







}