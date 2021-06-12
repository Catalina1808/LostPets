package com.example.lostpet.data;

import com.example.lostpet.ApplicationController;
import com.example.lostpet.data.tasks.GetAllAnnouncementsTask;
import com.example.lostpet.data.tasks.InsertAnnouncementTask;
import com.example.lostpet.models.dbEntities.AnnouncementItem;

import java.util.List;

public class AnnouncementRepository {
    private AnnouncementDataBase announcementDataBase;

    public static interface OnInsertAnnouncementListener {
        void onSuccess();

    }

    public static interface OnGetAnnouncementsListener {
        void onSuccess(List<AnnouncementItem> announcementItems);

    }

    public AnnouncementRepository(){
        announcementDataBase= ApplicationController.getAnnouncementDataBase();
    }


    public void insertAnnouncement(AnnouncementItem announcementItem, OnInsertAnnouncementListener listener){
        new InsertAnnouncementTask(announcementDataBase, listener).execute(announcementItem);
    }

    public void getAnnouncements(OnGetAnnouncementsListener listener){
        new GetAllAnnouncementsTask(announcementDataBase, listener).execute();
    }
}
