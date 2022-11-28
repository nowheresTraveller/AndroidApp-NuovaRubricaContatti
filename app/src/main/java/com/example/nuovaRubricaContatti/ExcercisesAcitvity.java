package com.example.nuovaRubricaContatti;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;


public class ExcercisesAcitvity extends AppCompatActivity {

    public final int READ_CONTACT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercises_acitvity);
    }


    @Override
    protected void onResume() {

        setCallButton();
        setDirCallButton();
        setSendSmsButton();
        setAlarmButton();
        setAddEventInCalendButton();
        setCreateAnEmailButton();
        setLocationInMapButton();
        setGoToBrowserButton();
        setChooseContactInRubric();

        //creazioni tweener_animation
        animOnTexts();
        //creazione frame_animation
        setMyFirstFrameAnimation();

        super.onResume();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == READ_CONTACT && resultCode == RESULT_OK) {
            Uri contactUri = data.getData();
            Log.d("---ContactURI ", contactUri.toString());

            Cursor cursor = getContentResolver().query(contactUri, new String[]{
                    ContactsContract.CommonDataKinds.Phone.NUMBER,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            }, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int colonnaNumero = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int colonnaNomeContatto = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                String numero = cursor.getString(colonnaNumero);
                String nome = cursor.getString(colonnaNomeContatto);

                Log.d("---nome contatto ", nome);
                Log.d("---numero contatto ", numero);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void setCallButton() {
        Button callButton = findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:3343869868"));
                //Il metodo "resolveActivity(PackageManager p)" verifica se esistono Activity che possono essere invocate
                // dalla action dell'intent "i"
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
            }
        });
    }


    public void setDirCallButton() {
        Button dirCallButton = findViewById(R.id.dirCallButton);
        dirCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:+393343869868"));
                if (i.resolveActivity(getPackageManager()) != null)
                    // il metodo "checkSelfPermission(getApplicationContext(),permission.CALL_PHONE)" verifica se l'app
                    // ha il permesso "CALL_PHONE". In caso di esito positivo restituisce 0 (PERMISSION_GRANTED)
                    // Questo metodo è obbligatorio utilizzarlo precedentemente all'avvio di specifiche action
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(i);
                    } else {
                        // Il metodo "requestPermissions (new String []{"CALL_PHONE"},1)" chiede all'utente di dare un permesso
                        // ad un app (in questo caso, "CALL_PHONE"
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }
            }
        });
    }


    public void setSendSmsButton() {
        Button sendSmsButton = findViewById(R.id.sendSmsButton);
        sendSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:+393343869868"));
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
            }
        });
    }

    public void setAlarmButton() {
        Button setAlarmButton = findViewById(R.id.setAlarmButton);
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "mio allarme");
                i.putExtra(AlarmClock.EXTRA_HOUR, 15);
                i.putExtra(AlarmClock.EXTRA_MINUTES, 00);

                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
            }
        });
    }

    public void setAddEventInCalendButton() {

        Button addEventInCalButton = findViewById(R.id.addEventInCalendButton);
        addEventInCalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_INSERT);
                i.setData(CalendarContract.Events.CONTENT_URI);
                i.putExtra(CalendarContract.Events.TITLE, "mio appuntamento");
                i.putExtra(CalendarContract.Events.EVENT_LOCATION, "Via Rossi 14");
                i.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, new Date().getTime());
                i.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, new Date().getTime());

                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
            }
        });
    }


    public void setCreateAnEmailButton() {
        Button createAnEmailButton = findViewById(R.id.createAnEmailButton);
        createAnEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:paolo.rossi@gmail.com"));

                //aggiungere altro destinatario della mail
                //i.putExtra(Intent.EXTRA_EMAIL,"mario.neri@gmail.com");

                i.putExtra(Intent.EXTRA_SUBJECT, "Invito al meeting del 12/11/2022");
                i.putExtra(Intent.EXTRA_TEXT, "Salve sig. Rossi, è invitato al meeting in data 12/11/2022 nella nostra sede a via Roma 22");
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
            }
        });
    }

    public void setLocationInMapButton() {
        Button locationInMapButton = findViewById(R.id.locationInMapButton);
        locationInMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:0,0?q=Viale del colosseo, Roma"));
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
            }
        });
    }

    public void setGoToBrowserButton() {
        Button goToBrowserButton = findViewById(R.id.goToBrowserButton);
        goToBrowserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.google.com"));
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
            }
        });
    }


    public void setChooseContactInRubric() {
        Button chooseContactButton = findViewById(R.id.chooseContactButton);
        chooseContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                if (i.resolveActivity(getPackageManager()) != null) {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                        startActivityForResult(i, READ_CONTACT);
                    } else {
                        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);
                    }
                }
            }
        });
    }


    public void animOnTexts() {
        //firstAnimation
        Button startAnimationButton = findViewById(R.id.startAnimationFirstButton);
        startAnimationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                TextView animText = findViewById(R.id.excercisesFirstText);
                animText.startAnimation(anim);
            }
        });

        //secondAnimation
        Button startAnimationSecondButton = findViewById(R.id.startAnimationSecondButton);
        startAnimationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
                TextView animText = findViewById(R.id.excercisesSecondText);
                animText.startAnimation(anim);
            }
        });

        //thirdAnimation
        Button startAnimationThirdButton = findViewById(R.id.startAnimationThirdButton);
        startAnimationThirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation);
                TextView animText = findViewById(R.id.excercisesThirdText);
                animText.startAnimation(anim);
            }
        });

        //fourthAnimation
        Button startAnimationFourthButton = findViewById(R.id.startAnimationFourthButton);
        startAnimationFourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                TextView animText = findViewById(R.id.excercisesFourthText);
                animText.startAnimation(anim);
            }
        });

        //fifthAnimation
        Button startAnimationFifthButton = findViewById(R.id.startAnimationFifthButton);
        startAnimationFifthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.mixed);
                TextView animText = findViewById(R.id.excercisesFifthText);
                animText.startAnimation(anim);
            }
        });

    }


    public void setMyFirstFrameAnimation (){

        //TODO cercare png di piccole dimensione (50dp*50dp)
        ImageView img = (ImageView) findViewById(R.id.firstImg);
        AnimationDrawable carsAnimation = (AnimationDrawable) img.getBackground();
        carsAnimation.start();
    }

}
