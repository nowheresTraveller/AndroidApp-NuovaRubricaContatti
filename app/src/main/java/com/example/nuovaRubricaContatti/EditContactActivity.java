package com.example.nuovaRubricaContatti;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class EditContactActivity extends AppCompatActivity {

    private final int SELECT_IMAGE = 5;
    private ImageView imgContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
    }


    @Override
    protected void onResume() {
       imgContact=findViewById(R.id.imageContact);

        /*
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentContainerView, new addEditFragment());
        transaction.commit();
        */

        //gestione evento su addImageButton
        View chooseImageButton = findViewById(R.id.addImageButton);
        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);
            }
        });

        TextView titleText = findViewById(R.id.TitleText);
        titleText.setText("Modifica contatto");

        super.onResume();
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