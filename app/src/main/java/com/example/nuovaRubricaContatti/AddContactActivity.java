package com.example.nuovaRubricaContatti;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nuovaRubricaContatti.data.dao.ContactDao;
import com.example.nuovaRubricaContatti.data.database.MyAppDatabase;
import com.example.nuovaRubricaContatti.data.entity.Contact;


public class AddContactActivity extends AppCompatActivity {

    private final int SELECT_IMAGE = 5;
    private ImageView imgContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }


    @Override
    protected void onResume() {
        imgContact = findViewById(R.id.imageContact);

        /*
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentContainerView, new addEditFragment());
        transaction.commit();
        */

        //gestione evento su chooseImageButton
        setListenerOnAddImageButton();
        setListenerOnAddButton();
        setListenerOnCancelButton();
        super.onResume();
    }

    public boolean checkNameAndSurnameEditText() {
        if (((EditText) findViewById(R.id.nameText)).getText().toString().equals("") || ((EditText) findViewById(R.id.surnameText)).getText().toString().equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.nametext_and_surnametext_empty, Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        return true;
    }


    public void setListenerOnAddImageButton() {
        View chooseImageButton = findViewById(R.id.addImageButton);
        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);
            }
        });
    }


    public void setListenerOnAddButton() {
        View okButton = findViewById(R.id.addButton);
        okButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (checkNameAndSurnameEditText()) {
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            Contact newContact = new Contact(
                                    ((EditText) findViewById(R.id.nameText)).getText().toString(),
                                    ((EditText) findViewById(R.id.surnameText)).getText().toString(),
                                    ((EditText) findViewById(R.id.cellNumberText)).getText().toString(),
                                    ((EditText) findViewById(R.id.homeNumberText)).getText().toString(),
                                    ((EditText) findViewById(R.id.emailText)).getText().toString()
                            );

                            ContactDao contactDao = MyAppDatabase.getInstance(getApplicationContext()).getContactDao();
                            long id = contactDao.insertContact(newContact);
                            Log.d("inserimento ", " ok ");
                            Log.d(" - nome e cognome prima riga tabella ", " " + contactDao.selectById(id).getName() + " " + contactDao.selectById(id).getSurname());

                            return null;
                        }
                    }.execute();

                    Intent i = new Intent();
                    setResult(-1, i);
                    finish();
                }
            }
        });

    }


    public void setListenerOnCancelButton() {
        View cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent i) {

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_IMAGE) {
                // Get the url of the image from data
                Uri selectedImageUri = i.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imgContact.setImageURI(selectedImageUri);
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, i);
    }

}