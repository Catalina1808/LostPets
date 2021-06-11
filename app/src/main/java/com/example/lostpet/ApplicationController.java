package com.example.lostpet;

import android.app.Application;

import androidx.room.Room;

import com.example.lostpet.data.AnnouncementDataBase;


public class ApplicationController extends Application {

    public static ApplicationController getInstance(){
        return instance;
    }

    private static AnnouncementDataBase announcementDataBase;
    private static ApplicationController instance;
    private final String announcementDataBaseName="AnnouncementDB";

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        setUpDataBase();
    }

    void setUpDataBase(){
        announcementDataBase= Room.databaseBuilder(
                getApplicationContext(),
                AnnouncementDataBase.class,
                announcementDataBaseName)
                .fallbackToDestructiveMigration()
                .build();
    }

    public static AnnouncementDataBase getAnnouncementDataBase() {
        return announcementDataBase;
    }

}
