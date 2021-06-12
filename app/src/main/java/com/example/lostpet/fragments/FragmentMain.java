package com.example.lostpet.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lostpet.R;
import com.example.lostpet.interfaces.OnFragmentActivityCommunication;

public class FragmentMain extends Fragment {
    public static final String TAG_FRAGMENT_MAIN="TAG_FRAGMENT_MAIN";
    private OnFragmentActivityCommunication activityCommunication;

    public static FragmentMain newInstance() {

        Bundle args = new Bundle();

        FragmentMain fragment = new FragmentMain();
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
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_add).setOnClickListener(v -> {
            goToAdd();

        });

        view.findViewById(R.id.btn_view_all).setOnClickListener(v -> {
            goToView();
        });

        view.findViewById(R.id.btn_view_mine).setOnClickListener(v -> {
            goToViewMine();
        });


    }



    private  void goToAdd(){
        if(activityCommunication !=null){
            activityCommunication.onAddFragment(FragmentAdd.TAG_FRAGMENT_ADD);
        }
    }

    private void goToView() {
        if(activityCommunication != null) {
            activityCommunication.onAddFragment(FragmentView.TAG_FRAGMENT_VIEW);
        }
    }

    private void goToViewMine() {
        if(activityCommunication != null) {
            activityCommunication.onAddFragment(FragmentViewMine.TAG_FRAGMENT_VIEW_MINE);
        }
    }

}
