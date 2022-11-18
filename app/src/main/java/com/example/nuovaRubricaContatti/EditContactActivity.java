package com.example.nuovaRubricaContatti;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.nuovaRubricaContatti.Fragment.ContactFragment;
import com.example.nuovaRubricaContatti.Fragment.addEditFragment;


public class EditContactActivity extends AppCompatActivity {

    private final int SELECT_IMAGE=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
    }


    @Override
    protected void onResume() {

        /*
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentContainerView, new addEditFragment());
        transaction.commit();
        */

        //gestione evento su chooseImageButton
        View chooseImageButton = findViewById(R.id.chooseImageButton);
        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent. ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);

            }
        });

        TextView titleText = findViewById(R.id.TitleText);
        titleText.setText("Modifica contatto");

        super.onResume();
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent i) {

        super.onActivityResult(requestCode, resultCode, i);
    }
}