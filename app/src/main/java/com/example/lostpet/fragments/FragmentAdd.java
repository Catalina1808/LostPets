package com.example.lostpet.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lostpet.R;
import com.example.lostpet.data.AnnouncementRepository;
import com.example.lostpet.interfaces.OnFragmentActivityCommunication;
import com.example.lostpet.models.dbEntities.AnnouncementItem;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.places.PlaceManager;
import com.facebook.places.model.PlaceFields;
import com.facebook.places.model.PlaceSearchRequestParams;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class FragmentAdd extends Fragment {
    private OnFragmentActivityCommunication activityCommunication;
    public static final String TAG_FRAGMENT_ADD = "TAG_FRAGMENT_ADD";
    private Button BSelectImage;
    private EditText ETPetName;
    private EditText ETBreed;
    private EditText ETLocation;
    private String OwnerEmail;
    private FirebaseAuth mAuth;
    private String imageUri;
    private AnnouncementRepository announcementRepository= new AnnouncementRepository();

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

    public void setUpViews(View view)
    {
        mAuth = FirebaseAuth.getInstance();
        OwnerEmail = mAuth.getCurrentUser().getEmail();
        BSelectImage = view.findViewById(R.id.BSelectImage);
        IVPreviewImage = view.findViewById(R.id.IVPreviewImage);
        ETBreed= view.findViewById(R.id.edt_breed);
        ETPetName= view.findViewById(R.id.edt_name);
        ETLocation=view.findViewById(R.id.edt_location);

/*
        ETLocation.setFocusable(false);
        ETLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Place.Field> fieldList= Arrays.asList(Place.Field.ADDRESS, Place.Field.NAME);
                Intent intent=new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldList).build(getContext());
                startActivityForResult(intent, 1);
            }
        });
        Places.initialize(getContext(),"AIzaSyBPckAnhj4tGL7gnYoAUwXdDC4ZNjSwMAo" );


 */

    }

    public void insertAnnouncement(){

        String ownerEmail= OwnerEmail;
        String breed= ETBreed.getText().toString();
        String location= ETLocation.getText().toString();
        String petName= ETPetName.getText().toString();
        if(ownerEmail.isEmpty() || breed.isEmpty() || location.isEmpty() || petName.isEmpty() || IVPreviewImage.isShown()) {
            Toast.makeText(getContext(),
                    "You have not completed all fields.",
                    Toast.LENGTH_LONG
            ).show();
            return;
        }

        AnnouncementItem announcementItem= new AnnouncementItem(petName, breed, imageUri, ownerEmail, location);
        AnnouncementRepository.OnInsertAnnouncementListener listener=  new AnnouncementRepository.OnInsertAnnouncementListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(getContext(),
                        "Insertion success.",
                        Toast.LENGTH_LONG
                ).show();
            }
        };
        announcementRepository.insertAnnouncement(announcementItem, listener);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpViews(view);

        view.findViewById(R.id.btn_addAnnouncement).setOnClickListener(v -> {
            insertAnnouncement();
        });

        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

    }

    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_OPEN_DOCUMENT);

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

                    getContext().grantUriPermission(getContext().getPackageName(), selectedImageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    final int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION;
                    getContext().getContentResolver().takePersistableUriPermission(selectedImageUri, takeFlags);


                    IVPreviewImage.setImageURI(selectedImageUri);
                    imageUri = selectedImageUri.toString();


                }
            }
        }



        //maps
        /*
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
            }
        }

         */



    }
}
