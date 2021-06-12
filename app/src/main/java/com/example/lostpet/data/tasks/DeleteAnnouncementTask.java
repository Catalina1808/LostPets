package com.example.lostpet.data.tasks;

import android.os.AsyncTask;
import com.example.lostpet.data.AnnouncementDataBase;
import com.example.lostpet.models.dbEntities.AnnouncementItem;

public class DeleteAnnouncementTask extends AsyncTask<AnnouncementItem,Void,Void>{


        private AnnouncementDataBase announcementDataBase;
        public DeleteAnnouncementTask(AnnouncementDataBase announcementDataBase)
        {
            this.announcementDataBase=announcementDataBase;
        }

        @Override
        protected Void doInBackground(AnnouncementItem... announcementItems) {
                announcementDataBase.announcementDAO().delete(announcementItems[0]);
                return null;
        }

        @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }

}
