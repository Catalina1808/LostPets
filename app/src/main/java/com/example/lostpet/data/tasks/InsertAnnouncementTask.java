package com.example.lostpet.data.tasks;

import android.os.AsyncTask;

import com.example.lostpet.ApplicationController;
import com.example.lostpet.data.AnnouncementDataBase;
import com.example.lostpet.data.AnnouncementRepositoryListener;
import com.example.lostpet.models.dbEntities.AnnouncementItem;

public class InsertAnnouncementTask extends AsyncTask<AnnouncementItem, Void, Void> {
    private AnnouncementDataBase announcementDataBase;
    private AnnouncementRepositoryListener listener;

    public InsertAnnouncementTask(AnnouncementDataBase announcementDataBase, AnnouncementRepositoryListener listener){
        this.announcementDataBase= announcementDataBase;
        this.listener=listener;
    }

    @Override
    protected Void doInBackground(AnnouncementItem... announcementItems) {
        announcementDataBase.announcementDAO().insert(announcementItems[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }
}
