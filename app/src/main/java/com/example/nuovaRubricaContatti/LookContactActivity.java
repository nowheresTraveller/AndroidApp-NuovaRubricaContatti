package com.example.nuovaRubricaContatti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nuovaRubricaContatti.data.database.MyAppDatabase;
import com.example.nuovaRubricaContatti.data.entity.Contact;

public class LookContactActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_contact);
    }

    @Override
    protected void onResume() {
        long id=getIntent().getIntExtra("position",0);
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                Contact c= MyAppDatabase.getInstance(getApplicationContext()).getContactDao().selectById(id);
                String nameAndSurname="";
                if(c.checkName())
                    nameAndSurname=c.getName();
                if(c.checkSurname())
                    nameAndSurname+=" "+c.getSurname();

                ((TextView)findViewById(R.id.nameContact)).setText(nameAndSurname);
                if(c.checkCellNumber())
                    ((TextView)findViewById(R.id.cellNumberText2)).setText(c.getCellNumber());
                if(c.checkHomeNumber())
                    ((TextView)findViewById(R.id.homeNumberText2)).setText(c.getHomeNumber());
                if(c.checkEmail())
                    ((TextView)findViewById(R.id.emailText2)).setText(c.getEmail());
                return null;
            }
        }.execute();

        setListenerOnEditButton2();
        setListenerOnOkButton2();

        super.onResume();
    }


    public void setListenerOnEditButton2() {
        ImageButton imageEditButton = findViewById(R.id.imageEditButton);
        imageEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EditContactActivity.class);
                startActivity(i);
            }
        });
    }


    public void setListenerOnOkButton2() {
        Button okButton = findViewById(R.id.okButton2);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}