package com.example.nuovaRubricaContatti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LookContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_contact);
    }

    @Override
    protected void onResume() {
        setListenerOnEditButton2();
        setListenerOnOkButton2();

        super.onResume();
    }


    public void  setListenerOnEditButton2(){
        ImageButton imageEditButton = findViewById(R.id.imageEditButton);
        imageEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EditContactActivity.class);
                startActivity(i);
            }
        });
    }


    public void  setListenerOnOkButton2(){
        Button okButton= findViewById(R.id.okButton2);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}