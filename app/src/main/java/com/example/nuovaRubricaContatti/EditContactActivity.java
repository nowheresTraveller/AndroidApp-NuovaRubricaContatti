package com.example.nuovaRubricaContatti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.nuovaRubricaContatti.Fragment.ContactFragment;
import com.example.nuovaRubricaContatti.Fragment.addEditFragment;


public class EditContactActivity extends AppCompatActivity {

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

        TextView titleText = findViewById(R.id.TitleText);
        titleText.setText("Modifica contatto");

        super.onResume();
    }
}