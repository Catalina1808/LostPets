package com.example.lostpet.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lostpet.R;
import com.example.lostpet.interfaces.OnFragmentActivityCommunication;

import static android.app.Activity.RESULT_OK;

public class FragmentAdd extends Fragment {
    private OnFragmentActivityCommunication activityCommunication;
    public static final String TAG_FRAGMENT_ADD = "TAG_FRAGMENT_ADD";
    private Button BSelectImage;
    private ImageView IVPreviewImage;
    private int SELECT_PICTURE = 200;


    public static Fragment newInstance() {
        Bundle args = new Bundle();

        FragmentAdd fragment = new FragmentAdd();
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
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
/*
        view.findViewById(R.id.btn_register).setOnClickListener(v -> {
            goToRegister();
        });
 */
        BSelectImage = view.findViewById(R.id.BSelectImage);
        IVPreviewImage = view.findViewById(R.id.IVPreviewImage);

        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

    }
    /*
    private  void goToRegister(){
        if(activityCommunication !=null){
            activityCommunication.onReplaceFragment(FragmentRegister.TAG_FRAGMENT_REGISTER);
        }
    }
     */
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    IVPreviewImage.setImageURI(selectedImageUri);
                }
            }
        }
    }
}
