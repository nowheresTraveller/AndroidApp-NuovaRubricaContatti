package com.example.nuovaRubricaContatti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nuovaRubricaContatti.data.entity.Contact;

public class LookContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_contact);
    }

    @Override
    protected void onResume() {
        Contact c = (Contact) getIntent().getSerializableExtra("item");
        String nameAndSurname = "";
        if (c.checkName())
            nameAndSurname = c.getName();
        if (c.checkSurname())
            nameAndSurname += " " + c.getSurname();

        ((TextView) findViewById(R.id.nameContact)).setText(nameAndSurname);
        if (c.checkCellNumber()){
            SpannableString content = new SpannableString(c.getCellNumber());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            ((TextView) findViewById(R.id.cellNumberText2)).setText(content);
        }
        if (c.checkHomeNumber()){
            SpannableString content2 = new SpannableString(c.getHomeNumber());
            content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
            ((TextView) findViewById(R.id.homeNumberText2)).setText(content2);
        }
         if (c.checkEmail()){
             SpannableString content3 = new SpannableString(c.getEmail());
             content3.setSpan(new UnderlineSpan(), 0, content3.length(), 0);
             ((TextView) findViewById(R.id.emailText2)).setText(content3);
         }

        setListenerOnEditButton2();
        setListenerOnOkButton2();


        registerForContextMenu(findViewById(R.id.cellNumberText2));
        registerForContextMenu(findViewById(R.id.homeNumberText2));
        registerForContextMenu(findViewById(R.id.emailText2));
        super.onResume();
    }

    // assegna il mio "context menu"(layout) all'oggetto parametro "ContextMenu"
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        switch (v.getId()) {
            case R.id.cellNumberText2:
                inflater.inflate(R.menu.context_1, menu);
                break;

            case R.id.homeNumberText2:

                inflater.inflate(R.menu.context_2, menu);
                break;

            case R.id.emailText2:
                inflater.inflate(R.menu.context_3, menu);
                break;

            default:
                Log.d(" context menu", "non hai usato alcuna opzione");
        }

    }

    // gestisce gli eventi sugli item del "context menu"
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
                String numberUser = ((TextView) findViewById(R.id.cellNumberText2)).getText().toString();


                switch (item.getItemId()) {
                    case R.id.callCellNumElement:
                        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+" + numberUser));
                        //Il metodo "resolveActivity(PackageManager p)" verifica se esistono Activity che possono essere invocate
                        // dalla action dell'intent "i"
                        if (i.resolveActivity(getPackageManager()) != null) {
                            startActivity(i);
                        }

                        return true;

                    case R.id.callHomeNumElement:
                        Intent secondI = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+" + ((TextView) findViewById(R.id.homeNumberText2)).getText().toString()));
                        //Il metodo "resolveActivity(PackageManager p)" verifica se esistono Activity che possono essere invocate
                        // dalla action dell'intent "i"
                        if (secondI.resolveActivity(getPackageManager()) != null) {
                            startActivity(secondI);
                        }

                        return true;

                    case R.id.sendSmsElement:
                        Intent thirdI = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:+" + numberUser));
                        if (thirdI.resolveActivity(getPackageManager()) != null) {
                            startActivity(thirdI);
                        }
                        return true;

                    case R.id.sendEmail:
                        Intent fourthI = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + ((TextView) findViewById(R.id.emailText2)).getText().toString()));
                        if (fourthI.resolveActivity(getPackageManager()) != null) {
                            startActivity(fourthI);
                        }
                        return true;

                    default:
                        Log.d(" - context menu ", " non è stato cliccato alcun elemento");
                }

                return false;
    }

    public void setListenerOnEditButton2() {
        ImageButton imageEditButton = findViewById(R.id.imageEditButton);
        imageEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EditContactActivity.class);
                startActivity(i);
            }
        });
    }


    public void setListenerOnOkButton2() {
        Button okButton = findViewById(R.id.okButton2);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}