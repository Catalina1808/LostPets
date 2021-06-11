package com.example.lostpet.data;

import com.example.lostpet.ApplicationController;
import com.example.lostpet.data.tasks.GetAllAnnouncementsTask;
import com.example.lostpet.data.tasks.InsertAnnouncementTask;
import com.example.lostpet.models.dbEntities.AnnouncementItem;

public class AnnouncementRepository {
    private AnnouncementDataBase announcementDataBase;

    public AnnouncementRepository(){
        announcementDataBase= ApplicationController.getAnnouncementDataBase();
    }


    public void insertAnnouncement(AnnouncementItem announcementItem, AnnouncementRepositoryListener listener){
        new InsertAnnouncementTask(announcementDataBase, listener).execute(announcementItem);
    }

    public void getAnnouncements(AnnouncementRepositoryListener listener){
        new GetAllAnnouncementsTask(announcementDataBase, listener).execute();
    }
}
