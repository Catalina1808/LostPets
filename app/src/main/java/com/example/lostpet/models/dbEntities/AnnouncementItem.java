package com.example.lostpet.models.dbEntities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AnnouncementItem {

    public AnnouncementItem(int id, String petName, String breed, String imageUri, String ownerEmail){
        this.id=id;
        this.petName=petName;
        this.breed=breed;
        this.imageUri=imageUri;
        this.ownerEmail=ownerEmail;
    }
    @PrimaryKey
    public int id;
    @ColumnInfo
    public String petName;
    @ColumnInfo
    public String breed;
    @ColumnInfo
    public String imageUri;
    @ColumnInfo
    public String ownerEmail;
}
