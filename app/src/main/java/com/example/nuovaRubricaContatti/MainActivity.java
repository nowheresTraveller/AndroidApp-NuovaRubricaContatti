package com.example.nuovaRubricaContatti;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.nuovaRubricaContatti.Fragment.ContactFragment;


public class MainActivity extends AppCompatActivity {

    private static final int FIRST_EDIT_MODE = 1;
    private static final int SECOND_EDIT_MODE = 2;
    private static final int ADD_MODE = 3;
    public static final int RESULT_OK = 10;
    public static final int RESULT_DENIED = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onResume() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.lista, new ContactFragment());
        transaction.commit();

        View addContactButton = findViewById(R.id.addContact);
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddContactActivity.class);
                startActivityForResult(i, ADD_MODE);
            }
        });


        super.onResume();
    }


    //Il parametro "requestCode" indica quale view ha scatenato l'evento
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent i) {
        if (resultCode == RESULT_OK && requestCode == ADD_MODE)
            Log.d("Aggiunzione contatto", "Il contatto è stato aggiunto con successo!");
        else if (resultCode == RESULT_DENIED && requestCode == ADD_MODE)
            Log.d("Aggiunzione contatto", "Errore: il contatto non è stato aggiunto!");

        super.onActivityResult(requestCode, resultCode, i);
    }


}


// -onSaveInstanceState();
// -onBackPressed(); --> descrive il comportamento al premere del pulsante back.
//                       Gli si può fare un override per personalizzare il comportamento. Badare che, quando si realizza l'override,
//                       la sua ultima riga di codice deve essere "super.onBackPressed().

