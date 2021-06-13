package com.example.lostpet.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.lostpet.R;
import com.example.lostpet.VolleyConfigSingleton;

import com.example.lostpet.models.RandomPhoto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class HttpRequestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<RandomPhoto> entertainmentList;

    public HttpRequestAdapter(ArrayList<RandomPhoto> entertainmentList) {
        this.entertainmentList = entertainmentList;
    }


    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.item_random_photo, parent, false);
            PhotoHolder photoHolder = new PhotoHolder(view);
            return photoHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof PhotoHolder) {
            RandomPhoto photo = (RandomPhoto) entertainmentList.get(position);
            ((PhotoHolder) holder).bind(photo);
        }

    }

    @Override
    public int getItemCount() {
        return this.entertainmentList.size();
    }


    class PhotoHolder extends RecyclerView.ViewHolder {
        private TextView owner;
        private ImageView image;

        PhotoHolder(View view) {
            super(view);
            owner = view.findViewById(R.id.tv_owner);
            image=view.findViewById(R.id.iv_random);
        }

        void bind(RandomPhoto photo) {
            owner.setText(photo.getOwner());


            //String imageViewUrl=photo.url.concat(".jpg");
            ImageLoader imageLoader = VolleyConfigSingleton.getInstance(image.getContext().getApplicationContext()).getImageLoader();
            imageLoader.get(photo.url, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    image.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }

    }
}
