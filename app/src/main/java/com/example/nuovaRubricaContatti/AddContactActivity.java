package com.example.nuovaRubricaContatti;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


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


        setListenerOnAcceptButton();
        setListenerOnCancelButton();
        super.onResume();
    }


    public void setListenerOnAddImageButton(){
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



    public void setListenerOnAcceptButton(){
        View acceptButton = findViewById(R.id.acceptButton);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent();
                EditText editText = (EditText) findViewById(R.id.nameText);
                i.putExtra("name", editText.getText().toString());
                setResult(MainActivity.RESULT_OK, i);
                finish();
            }
        });

    }


    public void setListenerOnCancelButton (){
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