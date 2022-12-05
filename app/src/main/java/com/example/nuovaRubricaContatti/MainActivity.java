package com.example.nuovaRubricaContatti;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.Manifest.permission;

import com.example.nuovaRubricaContatti.classes.Contact;
import com.example.nuovaRubricaContatti.classes.CustomAdapter;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int ADD_MODE = 3;
    public CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Richiesta di permesso all'utente per leggere il filesystem di android
        if (!(ContextCompat.checkSelfPermission(getApplicationContext(),permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED))
            requestPermissions(new String[]{permission.READ_EXTERNAL_STORAGE}, 1);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED)
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED)
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);

    }

    @Override
    protected void onResume() {


        //Metodo per lavora sulla directory "data" dell'applicazione
        // MainActivity.workOnDataDirectoryOfApplication(getApplicationContext());

        //Mio Metodo per esplorare il FileSystem di Android
        //PS:per esplorare delle directory di android c'è bisogno del permesso di root
        // MainActivity.exploreAndroidFileSystem(getApplicationContext());


        //Implementazione listView "miaListView"
        ListView myListView = (ListView) findViewById(R.id.myListView);
        List contatti = new ArrayList();
        contatti.add(new Contact("Giovanni"));
        contatti.add(new Contact("Paolo"));
        customAdapter = new CustomAdapter(getApplicationContext(), R.layout.row_of_listview, contatti);
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

        setMyListView(myListView);
        setAddContactButton();

        super.onResume();
    }

    public void  addTextColorToLookButton(){
        //TODO setTextColor of lookButton's text
        /*
        Button lookButton = customAdapter.getLookButton();
        lookButton.setTextColor(getResources().getColor(R.color.textname_color_of_listview,getTheme()));
        */
    }


    public void setAddContactButton(){
        View addContactButton = findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddContactActivity.class);
                startActivityForResult(i, ADD_MODE);
            }
        });
    }




    public void setMyListView(ListView myListView){
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("listView", "cliccato");
                Button lookButton = view.findViewById(R.id.lookButton);
                lookButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), LookContactActivity.class);
                        startActivity(i);
                    }
                });

                Button editButton = view.findViewById(R.id.editButton);
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), EditContactActivity.class);
                        startActivity(i);
                    }
                });
            }
        });
    }


    public void filterList(String text) {
        List<Contact> contactList = customAdapter.getContacts();
        List<Contact> filteredList = new ArrayList<>();
        for (Contact contact : contactList) {
            if (contact.getNome().toLowerCase().equals(text.toLowerCase())) {
                filteredList.add(contact);
            }
        }

        if (!filteredList.isEmpty())
            customAdapter.setFilteredList(filteredList);

    }



    //Alert
    //Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();


    //Il parametro "requestCode" indica quale view ha scatenato l'evento
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent i) {
        super.onActivityResult(requestCode, resultCode, i);
    }


    public static void exploreAndroidFileSystem (Context c){
        File file= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        Log.d("directory:", ""+file.isDirectory()+" and name is :"+file.getName());
        File [] listFiles = file.listFiles();
        for (File singleFile : listFiles){
            Log.d("name file",""+singleFile.getName());
        }
    }


    public static void workOnDataDirectoryOfApplication(Context c){
        //Scrittura nel file "terzo_testo_esempio.txt" nell'absolute path "/data/user/0/com.example.nuovaRubricaContatti/files"
        try {
            FileOutputStream outputStream = c.openFileOutput("terzo_testo_esempio.txt", Context.MODE_APPEND);
            DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(outputStream));
            outStream.writeUTF("ciao mondo");
            outStream.close();
        } catch (Exception e) {
            Log.d("errore:", "file non creato");
        }

        //lettura nel file "terzo_testo_esempio.txt" nell'absolute path "/data/user/0/com.example.nuovaRubricaContatti/files"
        try {

            String result= new String(),patternString;
            FileInputStream fis = c.openFileInput("terzo_testo_esempio.txt");
            BufferedReader d = new BufferedReader(new InputStreamReader(fis));
            patternString = d.readLine();
            while (patternString!=null){
                result+=patternString+"\n";
                patternString=d.readLine();
            }
            Log.d("contenuto file 'terzo_testo_esempio.txt",""+result);
            d.close();

        }catch (FileNotFoundException e){
            Log.d("Errore","file per la lettura non trovato - "+e.getClass());
        }catch (java.io.IOException e){
            Log.d("Errore","Impossibile leggere file - "+e.getClass());
        }


        // get della directory
        // con absolute path "/data/user/0/com.example.nuovaRubricaContatti/files"
        //e listato dei file
        File f = c.getFilesDir();
        File[] files = f.listFiles();
        for (File singoloFile : files) {
            Log.d("file", "" + singoloFile.getName());
        }
    }


}


// -onSaveInstanceState();
// -onBackPressed(); --> descrive il comportamento al premere del pulsante back.
//                       Gli si può fare un override per personalizzare il comportamento. Badare che, quando si realizza l'override,
//                       la sua ultima riga di codice deve essere "super.onBackPressed().

