package com.example.nuovaRubricaContatti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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
        setListenerOnNewViewButton();

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


    public void setListenerOnNewViewButton() {
        Button createNewButton = findViewById(R.id.newViewButton);
        createNewButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ConstraintLayout cLayout = (ConstraintLayout) findViewById(R.id.layout_prova);
                ConstraintSet set = new ConstraintSet();
                set.clone(cLayout);
                TextView newText = new TextView(getApplicationContext());
                newText.setId(View.generateViewId());
                newText.setText("ciao_mondo");
                cLayout.addView(newText);
                set.connect(newText.getId(), ConstraintSet.TOP, createNewButton.getId(), ConstraintSet.BOTTOM, 0);
                set.connect(newText.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
                set.constrainHeight(newText.getId(), 50);
                set.applyTo(cLayout);
            }
        });

        createNewButton.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                ((ViewGroup) createNewButton.getParent()).removeViewAt(1);
                return false;
            }
        });
    }
}