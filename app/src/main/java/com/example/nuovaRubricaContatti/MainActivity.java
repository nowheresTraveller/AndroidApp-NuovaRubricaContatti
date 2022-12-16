package com.example.nuovaRubricaContatti;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.Manifest.permission;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.example.nuovaRubricaContatti.classes.ListContact;
import com.example.nuovaRubricaContatti.classes.CustomAdapter;
import com.example.nuovaRubricaContatti.classes.DialogEliminaContatto;
import com.example.nuovaRubricaContatti.data.entity.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private static final int ADD_MODE = 3;
    private static final int EDIT_MODE = 4;

    public CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new AsyncTask <Void,Void,Void> (){
            @Override
            protected Void doInBackground(Void... voids) {
                customAdapter = new CustomAdapter(getApplicationContext(),
                        R.layout.row_of_listview,
                        new ListContact(getApplicationContext()).getListContact());

                return null;
            }
        }.execute();

        setContentView(R.layout.activity_main);

        //Richiesta di permesso all'utente per leggere il filesystem di android
        if (!(ContextCompat.checkSelfPermission(getApplicationContext(), permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED))
            requestPermissions(new String[]{permission.READ_EXTERNAL_STORAGE}, 1);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED)
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED)
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        //Implementazione listView "miaListView"
        ListView myListView = (ListView) findViewById(R.id.myListView);
        myListView.setAdapter(customAdapter);

        //funzionamento searchView
        SearchView searchView = findViewById(R.id.mySearchView);
        searchView.clearFocus();
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

        //aggiungo colore ad "addContactButton" via codice
        addTextColorToLookButton();
        setListenerOnMyListView(myListView);
        setAddContactButton();

        super.onResume();
    }

    public void addTextColorToLookButton() {
        //TODO setTextColor of lookButton's text
        /*
        Button lookButton = customAdapter.getLookButton();
        lookButton.setTextColor(getResources().getColor(R.color.textname_color_of_listview,getTheme()));
        */
    }


    public void setAddContactButton() {
        View addContactButton = findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddContactActivity.class);
                startActivityForResult(i, ADD_MODE);
            }
        });
    }


    //Gestisce gli eventi sugli Item della listView
    public void setListenerOnMyListView(ListView myListView) {
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("listView", "cliccato");
                Button lookButton = view.findViewById(R.id.lookButton);
                lookButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), LookContactActivity.class);
                        i.putExtra("position",position+1);
                        startActivity(i);
                    }
                });

                Button editButton = view.findViewById(R.id.editButton);
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), EditContactActivity.class);
                        i.putExtra("position",position+1);
                        startActivityForResult(i, EDIT_MODE);
                    }
                });

                Button deleteButton = findViewById(R.id.deleteButton);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogEliminaContatto d = new DialogEliminaContatto();
                        d.setIdContatto(position+1);
                        d.show(getSupportFragmentManager(), "ViewHolder");}
                });
            }
        });
    }

/*
    //permetto la funzione di filtro della searchView in rapporto alla ListView
    public void filterList(String text) {
        int textLengthMin1=text.length()-1;
        text = text.toLowerCase();
        List<Contact> contactList = customAdapter.getContacts();
        List<Contact> filteredList = new ArrayList<>();

        for (Contact contact : contactList) {
            String cName = contact.getName();
            String cSurname=contact.getSurname().toLowerCase();
            if (text.equals(cName.substring(0,textLengthMin1)) || text.equals(cSurname.substring(0,textLengthMin1))){
                filteredList.add(contact);
            }
        }
        if (!filteredList.isEmpty())
            customAdapter.setFilteredList(filteredList);
     }
*/


    public void filterList(String text) {
        List<Contact> contactList = customAdapter.getContacts();
        List<Contact> filteredList = new ArrayList<>();
        for (Contact contact : contactList) {
            if (contact.getName().toLowerCase().equals(text.toLowerCase())) {
                filteredList.add(contact);
            }
        }

        if (!filteredList.isEmpty())
            customAdapter.setFilteredList(filteredList);

    }

    //implementa un "options menu" nell'activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }


    //metodo che gestisce gli eventi sugli item dell "options menu"
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itaElement:
                Log.d(" - lingua cliccata ", " italiano");
                break;

            case R.id.engElement:
                Log.d(" - lingua cliccata ", " inglese");
                break;

            case R.id.creditsItem:
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window_credits, null);
                PopupWindow popupWindow = new PopupWindow(popupView, 800, 800, true);
                popupWindow.showAtLocation(findViewById(R.id.myListView).getRootView(), Gravity.CENTER, 0, 0);

                //crea effetto blur dietro il popup window
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.dimAmount = 0.50f;
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(layoutParams);


                //gestione evento "tocco" sul popup window
                popupView.setOnTouchListener(new View.OnTouchListener() {

                    // chiude il popup window quando è toccato
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        popupWindow.dismiss();
                        return true;
                    }
                });
                break;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent i) {

        if (requestCode == ADD_MODE && resultCode == RESULT_OK) {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.contact_added_with_success, Toast.LENGTH_SHORT);
            toast.show();
        }
        if (requestCode == EDIT_MODE && resultCode == RESULT_OK) {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.contact_modified_with_success, Toast.LENGTH_SHORT);
            toast.show();
        }

        super.onActivityResult(requestCode, resultCode, i);
    }


}


// -onSaveInstanceState();
// -onBackPressed(); --> descrive il comportamento al premere del pulsante back.
//                       Gli si può fare un override per personalizzare il comportamento. Badare che, quando si realizza l'override,
//                       la sua ultima riga di codice deve essere "super.onBackPressed().

