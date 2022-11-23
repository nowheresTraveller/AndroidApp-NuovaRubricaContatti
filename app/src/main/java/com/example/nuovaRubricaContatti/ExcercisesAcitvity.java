package com.example.nuovaRubricaContatti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;

public class ExcercisesAcitvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercises_acitvity);
    }


    @Override
    protected void onResume() {

        //gestione evento su "callButton" (intent implicito)
        Button callButton = findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:3343869868"));
                //Il metodo "resolveActivity(PackageManager p)" verifica se esistono Activity che possono essere invocate
                // dalla action dell'intent "i"
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
            }
        });

        //gestione evento su "dirCallButton" (intent implicito)
        Button dirCallButton = findViewById(R.id.dirCallButton);
        dirCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:+393343869868"));
                if (i.resolveActivity(getPackageManager()) != null)
                    // il metodo "checkSelfPermission(getApplicationContext(),permission.CALL_PHONE)" verifica se l'app
                    // ha il permesso "CALL_PHONE". In caso di esito positivo restituisce 0 (PERMISSION_GRANTED)
                    // Questo metodo è obbligatorio utilizzarlo precedentemente all'avvio di specifiche action
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(i);
                    } else {
                        // Il metodo "requestPermissions (new String []{"CALL_PHONE"},1)" chiede all'utente di dare un permesso
                        // ad un app (in questo caso, "CALL_PHONE"
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }
            }
        });

        //gestione evento su "sendSmsButton"
        Button sendSmsButton = findViewById(R.id.sendSmsButton);
        sendSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:+393343869868"));
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
            }
        });

        setAlarmButton();

        super.onResume();
    }


    public void setAlarmButton() {
        Button setAlarmButton = findViewById(R.id.setAlarmButton);
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "mio allarme");
                i.putExtra(AlarmClock.EXTRA_HOUR, 15);
                i.putExtra(AlarmClock.EXTRA_MINUTES, 00);

                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
            }
        });
    }
}