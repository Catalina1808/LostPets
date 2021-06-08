package com.example.lostpet.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class FragmentAdd extends Fragment {

    public static final String TAG_FRAGMENT_ADD = "TAG_FRAGMENT_ADD";

    public static Fragment newInstance() {
        Bundle args = new Bundle();

        FragmentAdd fragment = new FragmentAdd();
        fragment.setArguments(args);
        return fragment;
    }
}
