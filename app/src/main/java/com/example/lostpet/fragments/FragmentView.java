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
import com.example.lostpet.data.AnnouncementRepository;
import com.example.lostpet.interfaces.OnFragmentActivityCommunication;
import com.example.lostpet.interfaces.OnItemClickListener;
import com.example.lostpet.models.AnnouncementElement;
import com.example.lostpet.models.dbEntities.AnnouncementItem;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class FragmentView extends Fragment {

    public static final String TAG_FRAGMENT_VIEW = "TAG_FRAGMENT_VIEW";
    private OnFragmentActivityCommunication activityCommunication;
    private ArrayList<AnnouncementElement> announcementList = new ArrayList<>();
    private AnnouncementRepository announcementRepository= new AnnouncementRepository();

    private ImageView IVPreviewImage;

    public void getAnnouncements(){
        AnnouncementRepository.OnGetAnnouncementsListener listener=  new AnnouncementRepository.OnGetAnnouncementsListener() {
            @Override
            public void onSuccess(List<AnnouncementItem> announcementItems) {
                Log.e("Error", "Get announcements");
            }
        };

        announcementRepository.getAnnouncements(announcementsResult -> {
            announcementList.clear();
            for (AnnouncementItem announcementItem : announcementsResult
            ) {
                announcementList.add(announcementItem.convert());
            }
            announcementAdapter.notifyDataSetChanged();
        });
    }

    private com.example.library.adapters.AnnouncementAdapter announcementAdapter = new com.example.library.adapters.AnnouncementAdapter(announcementList, new OnItemClickListener() {
        @Override
        public void onDeleteClick(AnnouncementElement element) {

        }
    });


    public static FragmentView newInstance() {

        Bundle args = new Bundle();

        FragmentView fragment = new FragmentView();
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
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv_announcements);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(announcementAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAnnouncements();


    }



}
