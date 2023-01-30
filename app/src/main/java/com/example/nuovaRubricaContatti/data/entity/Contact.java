package com.example.nuovaRubricaContatti.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//  "unique = true" = la coppia {name,surname} diventano una chiave
@Entity(tableName = "contact",
        indices = { @Index("cellular_number"), @Index("email") , @Index(value={"name","surname"}, unique = true)}
        //nel caso si vuole aggiungere delle foreign key
        //ps: childColumns = campo id dell'altra entity
        //ps: parentColumns= foreign key di questa tabella
        //, foreignKeys= @ForeignKey(entity=Pippo.class, childColumns="id" , parentColumns="pippo_id")
        )
public class Contact implements Serializable {

    // "autoGenerate = true" = l'id è autoincrementale
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String surname;
    @ColumnInfo(name = "cellular_number")
    private String cellNumber;
    @ColumnInfo(name = "home_number")
    private String homeNumber;
    private String email;

    //foreign key di questa tabella
    //@ColumnInfo(name = "pippo_id")
    //private long pippoId;

    public Contact(String name, String surname,String cellNumber,String homeNumber, String email){
        this.name=name;
        this.surname=surname;
        this.cellNumber=cellNumber;
        this.homeNumber= homeNumber;
        this.email=email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCellNumber(String cellularNumber) {
        this.cellNumber = cellularNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getEmail() {
        return email;
    }

    public boolean checkName(){
        return (name!=null);
    }

    public boolean checkSurname(){
        return (surname!=null);
    }

    public boolean checkCellNumber(){
        return (cellNumber!=null);
    }

    public boolean checkHomeNumber(){
        return (homeNumber!=null);
    }

    public boolean checkEmail(){
        return (email!=null);
    }

    // quest'annotation permette ad un campo della classe
    // di non essere una colonna della tabella
    @Ignore
    private String campoIgnorato;


}
