package com.example.lostpet.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lostpet.R;
import com.example.lostpet.helpers.UtilsValidators;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.installations.Utils;

public class FragmentRegister extends Fragment {
    public static final String TAG_FRAGMENT_REGISTER="TAG_FRAGMENT_REGISTER";

        private FirebaseAuth mAuth;


     public static FragmentRegister newInstance() {

        Bundle args = new Bundle();

        FragmentRegister fragment = new FragmentRegister();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_register).setOnClickListener(v -> {
         validateEmailAndPassword();
        });
    }

    private void validateEmailAndPassword(){
         if(getView()==null)
         {
             return;
         }
        EditText emailEditText=getView().findViewById(R.id.edt_email);
        EditText passwordEditText=getView().findViewById(R.id.edt_password);
         String email=emailEditText.getText().toString();
         String password=passwordEditText.getText().toString();

        if(!UtilsValidators.isValidEmail(email))
        {
            emailEditText.setError("Invalid Email");
            return;
        }
        else
        {
            emailEditText.setError(null);
        }

        if(!UtilsValidators.isValidPassword(password))
        {
            passwordEditText.setError("Invalid Password");
            return;
        }
        else
        {
            passwordEditText.setError(null);
        }

        createFireBaseUser(email, password);
    }

    private void createFireBaseUser(String email, String password){
         if(getActivity()==null)
         {
             return;
         }
         mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), task->{
            if(task.isSuccessful())
            {
                FirebaseUser user=mAuth.getCurrentUser();
                Toast.makeText(getContext(), "Authentification success.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getContext(), "Authentification failed.", Toast.LENGTH_SHORT).show();
            }
         });
    }
}
