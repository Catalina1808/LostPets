package com.example.lostpet.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lostpet.models.dbEntities.AnnouncementItem;

import java.util.List;

@Dao
public interface AnnouncementDAO {
    @Query("SELECT * FROM announcementItem")
    List<AnnouncementItem> getAll();

    @Insert
    void insert(AnnouncementItem announcementItem);

    @Delete
    void delete(AnnouncementItem announcementItem);
}
