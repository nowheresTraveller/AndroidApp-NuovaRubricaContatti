package com.example.nuovaRubricaContatti.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.nuovaRubricaContatti.data.entity.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertContact(Contact contact);

    @Update
    public void updateContact(Contact contact);

    @Delete
    public void deleteContact(Contact contact);

    //delete con condizione
    @Query("delete from contact where id = :idContact")
    public void deleteById(long idContact);

    //Select
    //l' "idContact" nella query è proprio il parametro passato al metodo astratto
    @Query("select * from contact where id = :idContact")
    public Contact selectById(long idContact);

    @Query("select * from contact where name like :nam and surname like :sur")
    public List<Contact> selectByNameAndSurname(String nam, String sur);

    @Query("select * from contact")
    public List<Contact> selectAllContact();

}
