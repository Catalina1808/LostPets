package com.example.lostpet.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.lostpet.fragments.*;
import android.os.Bundle;

import com.example.lostpet.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    onAddWelcomeFragment();
    }

    private void onAddWelcomeFragment(){
        FragmentManager fragmentManager= getSupportFragmentManager();

        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fly_container, FragmentWelcome.newInstance(), FragmentWelcome.TAG_FRAGMENT_WELCOME);
        fragmentTransaction.commit();
    }
}