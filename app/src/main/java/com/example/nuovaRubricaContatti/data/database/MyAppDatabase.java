package com.example.nuovaRubricaContatti.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.nuovaRubricaContatti.data.dao.ContactDao;
import com.example.nuovaRubricaContatti.data.entity.Contact;

@Database( entities = {Contact.class}, version=1)
public abstract class MyAppDatabase extends RoomDatabase {

    private static MyAppDatabase myDatabase;

    public abstract ContactDao getContactDao();

    public static MyAppDatabase getInstance(Context context){
        if(myDatabase == null){

            //creazione DB
            myDatabase= Room.databaseBuilder(
                    context,
                    MyAppDatabase.class,
                    "nuovaRubricaContatti.db"
            ).build();
        }

        return myDatabase;
    }

}
