package com.example.lostpet.data.tasks;

import android.os.AsyncTask;

import com.example.lostpet.data.AnnouncementDataBase;
import com.example.lostpet.data.AnnouncementRepositoryListener;
import com.example.lostpet.models.dbEntities.AnnouncementItem;

import java.util.ArrayList;
import java.util.List;

public class GetAllAnnouncementsTask extends AsyncTask<Void, Void, List<AnnouncementItem>> {
    private AnnouncementDataBase announcementDataBase;
    private AnnouncementRepositoryListener listener;

    public GetAllAnnouncementsTask(AnnouncementDataBase announcementDataBase, AnnouncementRepositoryListener listener){
        this.announcementDataBase= announcementDataBase;
        this.listener=listener;
    }


    @Override
    protected List<AnnouncementItem> doInBackground(Void... voids) {
        return  announcementDataBase.announcementDAO().getAll();
    }

    @Override
    protected void onPostExecute(List<AnnouncementItem> announcementItems) {
        super.onPostExecute(announcementItems);
        listener.onSuccess();
    }
}
