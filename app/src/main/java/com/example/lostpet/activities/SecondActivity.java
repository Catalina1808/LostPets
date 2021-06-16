package com.example.lostpet.activities;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.lostpet.R;
import com.example.lostpet.fragments.FragmentAdd;
import com.example.lostpet.fragments.FragmentMain;
import com.example.lostpet.fragments.FragmentView;
import com.example.lostpet.fragments.FragmentViewMine;
import com.example.lostpet.interfaces.OnFragmentActivityCommunication;

import static com.example.lostpet.fragments.FragmentAdd.TAG_FRAGMENT_ADD;
import static com.example.lostpet.fragments.FragmentView.TAG_FRAGMENT_VIEW;
import static com.example.lostpet.fragments.FragmentViewMine.TAG_FRAGMENT_VIEW_MINE;

public class SecondActivity extends AppCompatActivity implements OnFragmentActivityCommunication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        onAddMainFragment();
    }

    private void onAddMainFragment(){
        FragmentManager fragmentManager= getSupportFragmentManager();

        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fly_container2, FragmentMain.newInstance(), FragmentMain.TAG_FRAGMENT_MAIN);
        fragmentTransaction.commit();
    }



    public void onAddFragment(String TAG){
        FragmentManager fragmentManager= getSupportFragmentManager();
        Fragment fragment;

        switch (TAG){
            case TAG_FRAGMENT_ADD:{
                fragment= FragmentAdd.newInstance();
                break;
            }

            case TAG_FRAGMENT_VIEW:{
                fragment= FragmentView.newInstance();
                break;
            }

            case TAG_FRAGMENT_VIEW_MINE:{
                fragment= FragmentViewMine.newInstance();
                break;
            }

            default: return;
        }

        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fly_container2,fragment, TAG);
        fragmentTransaction.addToBackStack(TAG);
        fragmentTransaction.commit();
    }

}