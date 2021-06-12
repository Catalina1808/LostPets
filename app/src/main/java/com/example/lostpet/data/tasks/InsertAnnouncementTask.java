package com.example.lostpet.data.tasks;

import android.os.AsyncTask;

import com.example.lostpet.data.AnnouncementDataBase;
import com.example.lostpet.data.AnnouncementRepository;
import com.example.lostpet.models.dbEntities.AnnouncementItem;

public class InsertAnnouncementTask extends AsyncTask<AnnouncementItem, Void, Void> {
    private AnnouncementDataBase announcementDataBase;
    private AnnouncementRepository.OnInsertAnnouncementListener listener;

    public InsertAnnouncementTask(AnnouncementDataBase announcementDataBase, AnnouncementRepository.OnInsertAnnouncementListener listener){
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
