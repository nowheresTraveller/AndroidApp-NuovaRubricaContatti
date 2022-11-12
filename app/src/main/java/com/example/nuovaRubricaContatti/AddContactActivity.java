package com.example.nuovaRubricaContatti;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;


public class AddContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }



    @Override
    protected void onResume() {
        View acceptButton = findViewById(R.id.acceptButton);
        acceptButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(MainActivity.RESULT_OK);
                finish();
            }
        });

        View cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(MainActivity.RESULT_DENIED);
                finish();
            }
        });

        super.onResume();
    }
}