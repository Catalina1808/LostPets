package com.example.lostpet.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.lostpet.fragments.*;

import android.content.Intent;
import android.os.Bundle;

import com.example.lostpet.R;
import com.example.lostpet.interfaces.OnFragmentActivityCommunication;

import static com.example.lostpet.fragments.FragmentRegister.TAG_FRAGMENT_REGISTER;
import static com.example.lostpet.fragments.FragmentLogin.TAG_FRAGMENT_LOGIN;

public class WelcomeActivity extends AppCompatActivity implements OnFragmentActivityCommunication {


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
    public void onReplaceFragment(String TAG){
        FragmentManager fragmentManager= getSupportFragmentManager();

        Fragment fragment;

        switch (TAG){
            case TAG_FRAGMENT_REGISTER:{
                fragment= FragmentRegister.newInstance();
                break;
            }

            case TAG_FRAGMENT_LOGIN: {
                fragment = FragmentLogin.newInstance();
                break;
            }
            default: return;
        }

        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fly_container,fragment, TAG);
        fragmentTransaction.commit();
    }

    public void onAddFragment(String TAG){
        FragmentManager fragmentManager= getSupportFragmentManager();

        Fragment fragment;

        switch (TAG){
            case TAG_FRAGMENT_REGISTER:{
                fragment= FragmentRegister.newInstance();
                break;
            }

            case TAG_FRAGMENT_LOGIN: {
                fragment = FragmentLogin.newInstance();
                break;
            }
            default: return;
        }

        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fly_container,fragment, TAG);
        fragmentTransaction.addToBackStack(TAG);
        fragmentTransaction.commit();
    }

}