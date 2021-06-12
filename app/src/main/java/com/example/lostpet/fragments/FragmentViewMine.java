package com.example.lostpet.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostpet.R;
import com.example.lostpet.adapters.AnnouncementAdapter;
import com.example.lostpet.data.AnnouncementRepository;
import com.example.lostpet.interfaces.OnFragmentActivityCommunication;
import com.example.lostpet.interfaces.OnItemClickListener;
import com.example.lostpet.models.AnnouncementElement;
import com.example.lostpet.models.dbEntities.AnnouncementItem;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class FragmentViewMine extends Fragment {

    public static final String TAG_FRAGMENT_VIEW_MINE = "TAG_FRAGMENT_VIEW_MINE";
    private OnFragmentActivityCommunication activityCommunication;
    private ArrayList<AnnouncementElement> announcementList = new ArrayList<>();
    private AnnouncementRepository announcementRepository= new AnnouncementRepository();
    private FirebaseAuth mAuth;


    public void getAnnouncements(){

        announcementRepository.getAnnouncements(announcementsResult -> {
            announcementList.clear();
            for (AnnouncementItem announcementItem : announcementsResult
            ) {
                if(announcementItem.convert().getOwner_email().equals(mAuth.getCurrentUser().getEmail()))
                announcementList.add(announcementItem.convert());
            }
            announcementAdapter.notifyDataSetChanged();
        });
    }

    private AnnouncementAdapter announcementAdapter = new AnnouncementAdapter(announcementList, new OnItemClickListener() {
        @Override
        public void onDeleteClick(AnnouncementElement element) {

        }
    });


    public static FragmentViewMine newInstance() {

        Bundle args = new Bundle();

        FragmentViewMine fragment = new FragmentViewMine();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof OnFragmentActivityCommunication){
            activityCommunication= (OnFragmentActivityCommunication) context;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_mine, container, false);
        mAuth = FirebaseAuth.getInstance();
        getAnnouncements();
        RecyclerView recyclerView = view.findViewById(R.id.rv_announcements);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(announcementAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }



}
