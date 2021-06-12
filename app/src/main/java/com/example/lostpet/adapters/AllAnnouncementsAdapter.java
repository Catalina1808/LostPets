package com.example.lostpet.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostpet.R;
import com.example.lostpet.interfaces.OnItemClickListener;
import com.example.lostpet.models.AnnouncementElement;

import java.util.ArrayList;

public class AllAnnouncementsAdapter extends RecyclerView.Adapter<AllAnnouncementsAdapter.AnnouncementViewHolder> {
    ArrayList<AnnouncementElement> announcementList;
    OnItemClickListener onItemClickListener;

    public AllAnnouncementsAdapter(ArrayList<AnnouncementElement>announcementList, OnItemClickListener onItemClickListener)
    {
        this.announcementList =announcementList;
        this.onItemClickListener= onItemClickListener;
    }

    @NonNull
    @Override
    public AllAnnouncementsAdapter.AnnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_all_announcements, parent, false);
        AnnouncementViewHolder announcementViewHolder = new AnnouncementViewHolder(view);
        return announcementViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllAnnouncementsAdapter.AnnouncementViewHolder holder, int position) {
        AnnouncementElement announcementElement = announcementList.get(position);
        holder.bind(announcementElement);
    }

    @Override
    public int getItemCount() {
        return announcementList.size();
    }
    class AnnouncementViewHolder extends RecyclerView.ViewHolder {
        private TextView pet_name;
        private TextView breed;
        private TextView location;
        private TextView owner_email;
        private View view;
        private Button deleteBtn;
        private ImageView image;

        public AnnouncementViewHolder(View view) {
            super(view);
            pet_name = view.findViewById(R.id.tv_pet_name);
            breed=view.findViewById(R.id.tv_breed);
            location = view.findViewById(R.id.tv_location);
            owner_email=view.findViewById(R.id.tv_email);
            deleteBtn = view.findViewById(R.id.btn_delete);
            image=view.findViewById(R.id.IVImage);
            this.view=view;
        }

        public void bind(AnnouncementElement announcementElement) {
            pet_name.setText(announcementElement.getPetName());
            breed.setText(announcementElement.getBreed());
            location.setText((announcementElement.getLocation()));
            owner_email.setText((announcementElement.getOwner_email()));
            image.setImageURI(Uri.parse((announcementElement.getImageUri())));

        }
    }

}
