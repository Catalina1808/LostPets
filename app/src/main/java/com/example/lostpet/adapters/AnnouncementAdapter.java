package com.example.library.adapters;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import com.example.lostpet.data.AnnouncementRepository;
import com.example.lostpet.interfaces.OnItemClickListener;
import com.example.lostpet.models.AnnouncementElement;
import com.example.lostpet.models.dbEntities.AnnouncementItem;

import java.util.ArrayList;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder> {
    ArrayList<AnnouncementElement> announcementList;
    OnItemClickListener onItemClickListener;

    public AnnouncementAdapter(ArrayList<AnnouncementElement>bookList,OnItemClickListener onItemClickListener)
    {
        this.announcementList =bookList;
        this.onItemClickListener= onItemClickListener;
    }

    @NonNull
    @Override
    public AnnouncementAdapter.AnnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_announcement, parent, false);
        AnnouncementViewHolder announcementViewHolder = new AnnouncementViewHolder(view);
        return announcementViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementAdapter.AnnouncementViewHolder holder, int position) {
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


/*
            deleteBtn.setOnClickListener(v -> {
                AnnouncementRepository announcementRepository=new AnnouncementRepository();
                AnnouncementItem announcementItem=new AnnouncementItem(announcementElement.getId(),announcementElement.getPetName(),announcementElement.getBreed(),announcementElement.getImageUri(), announcementElement.getOwner_email());
                announcementRepository.deleteAnnouncement(announcementItem);
                onItemClickListener.onDeleteClick(announcementElement);
                announcementList.remove(getAdapterPosition());
                notifyItemChanged(getAdapterPosition());
            });

 */
        }
    }

}
