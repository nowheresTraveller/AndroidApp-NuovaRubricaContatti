package com.example.nuovaRubricaContatti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.nuovaRubricaContatti.Fragment.addEditFragment;


public class AddContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }



    @Override
    protected void onResume() {

        /*
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentContainerView, new addEditFragment());
        transaction.commit();
        */

        View acceptButton = findViewById(R.id.acceptButton);
        acceptButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent i = new Intent();
                EditText editText =(EditText)findViewById(R.id.textName);
                i.putExtra("name",editText.getText().toString());
                setResult(MainActivity.RESULT_OK,i);
                finish();
            }
        });

        View cancelButton = findViewById(R.id.deleteButton);
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