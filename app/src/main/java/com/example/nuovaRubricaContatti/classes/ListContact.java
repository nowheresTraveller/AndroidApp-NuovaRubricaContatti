package com.example.nuovaRubricaContatti.classes;

import android.content.Context;

import com.example.nuovaRubricaContatti.data.database.MyAppDatabase;
import com.example.nuovaRubricaContatti.data.entity.Contact;

import java.util.List;

public class ListContact {

   List<Contact> listContact;

   public ListContact(Context context){
       listContact = MyAppDatabase.getInstance(context).getContactDao().selectAllContact();
   }

    public List<Contact> getListContact() {
        return listContact;
    }


}
