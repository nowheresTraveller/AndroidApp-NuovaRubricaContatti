package com.example.nuovaRubricaContatti;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.nuovaRubricaContatti.Fragment.ContactFragment;
import com.example.nuovaRubricaContatti.samples.ItemFragment;


public class MainActivity extends AppCompatActivity {

    private static final int FIRST_EDIT_MODE = 1;
    private static final int SECOND_EDIT_MODE = 2;
    private static final int ADD_MODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onResume() {
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.lista,new ContactFragment());
        transaction.commit();



        super.onResume();
    }



    //Il parametro "requestCode" indica quale view ha scatenato l'evento
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent i) {
        if (resultCode == RESULT_OK) {
            TextView text = findViewById(R.id.TitleText);

            if (requestCode == SECOND_EDIT_MODE || requestCode == FIRST_EDIT_MODE) {
                Log.d("MainActivity.class - Messaggio","Contatto modificato con successo!");
            } else if (requestCode == ADD_MODE) {
                Log.d("MainActivity.class - Messaggio","Contatto aggiunto con successo!");
            }
        }

        super.onActivityResult(requestCode, resultCode, i);
    }



}


// -onSaveInstanceState();
// -onBackPressed(); --> descrive il comportamento al premere del pulsante back.
//                       Gli si può fare un override per personalizzare il comportamento. Badare che, quando si realizza l'override,
//                       la sua ultima riga di codice deve essere "super.onBackPressed().

