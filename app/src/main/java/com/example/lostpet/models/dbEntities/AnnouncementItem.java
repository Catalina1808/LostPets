package com.example.lostpet.models.dbEntities;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.lostpet.models.AnnouncementElement;

@Entity
public class AnnouncementItem {

    public AnnouncementItem(String petName, String breed, String imageUri, String ownerEmail, String location){
        this.petName=petName;
        this.breed=breed;
        this.imageUri=imageUri;
        this.ownerEmail=ownerEmail;
        this.location=location;
    }
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo
    public String petName;
    @ColumnInfo
    public String breed;
    @ColumnInfo
    public String imageUri;
    @ColumnInfo
    public String ownerEmail;
    @ColumnInfo
    public String location;

    public AnnouncementElement convert(){
        return new AnnouncementElement(id, petName, breed, location, ownerEmail, imageUri);
    }
}
