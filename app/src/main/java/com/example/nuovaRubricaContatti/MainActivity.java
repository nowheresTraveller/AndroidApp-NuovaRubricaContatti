package com.example.nuovaRubricaContatti;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;import com.example.nuovaRubricaContatti.classes.ContactOnListView;
import com.example.nuovaRubricaContatti.classes.CustomAdapter;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private static final int ADD_MODE = 3;
    public static final int RESULT_DENIED = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {

        //Implementazione listView "miaListView"
        ListView miaListView = (ListView) findViewById(R.id.miaListView);
        ContactOnListView [] contatti = {new ContactOnListView("Giovanni"),new ContactOnListView("Paolo")};
        CustomAdapter customAdapter= new CustomAdapter(getApplicationContext(),R.layout.row_of_listview,contatti);
        miaListView.setAdapter(customAdapter);

        //Implementazione listener su "miaListView"
        miaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("listView","cliccato");
                Button lookButton=view.findViewById(R.id.lookButton);
                lookButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i= new Intent(getApplicationContext(),LookContactActivity.class);
                        startActivity(i);
                    }
                });

                Button editButton=view.findViewById(R.id.editButton);
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i= new Intent(getApplicationContext(),EditContactActivity.class);
                        startActivity(i);
                    }
                });
            }
        });



        View addContactButton = findViewById(R.id.addContact);
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddContactActivity.class);
                startActivityForResult(i, ADD_MODE);
            }
        });


        //funzionamento searchView
        SearchView searchView = findViewById(R.id.mySearchView);
        searchView.clearFocus();

        //TODO riparare filtraggio sulla SearchView
        /*
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        */

        super.onResume();
    }


    /*
    public void filterList(String text) {
        List<PlaceholderContent.PlaceholderItem> firstList = ContactFragment.itemArrayList;
        List<PlaceholderContent.PlaceholderItem> filteredList = new ArrayList<>();
        for (PlaceholderContent.PlaceholderItem contact : firstList) {
            if (contact.getFirstContent().toLowerCase().equals(text.toLowerCase())) {
                filteredList.add(contact);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        } else {
            ContactFragment.myRecyclerView.setFilteredList(filteredList);
        }
    }
*/

    //Il parametro "requestCode" indica quale view ha scatenato l'evento
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent i) {
        if (resultCode == RESULT_OK && requestCode == ADD_MODE)
            Log.d("Aggiunzione contatto", "Il contatto è stato aggiunto con successo!\nIl nome inserito = " + i.getStringExtra("name"));
        else if (resultCode == RESULT_DENIED && requestCode == ADD_MODE)
            Log.d("Aggiunzione contatto", "Errore: il contatto non è stato aggiunto!");

        super.onActivityResult(requestCode, resultCode, i);
    }


}


// -onSaveInstanceState();
// -onBackPressed(); --> descrive il comportamento al premere del pulsante back.
//                       Gli si può fare un override per personalizzare il comportamento. Badare che, quando si realizza l'override,
//                       la sua ultima riga di codice deve essere "super.onBackPressed().

