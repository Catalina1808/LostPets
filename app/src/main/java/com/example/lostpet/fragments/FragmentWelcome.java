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

public class FragmentWelcome extends Fragment {
    public static final String TAG_FRAGMENT_WELCOME="TAG_FRAGMENT_WELCOME";
    private OnFragmentActivityCommunication activityCommunication;

     public static FragmentWelcome newInstance() {

        Bundle args = new Bundle();

        FragmentWelcome fragment = new FragmentWelcome();
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
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_rigster).setOnClickListener(v -> {
          goToRegister();

        });
    }

    private  void goToRegister(){
         if(activityCommunication !=null){
             activityCommunication.onReplaceFragment(FragmentRegister.TAG_FRAGMENT_REGISTER);
         }
    }


}
