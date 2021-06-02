package com.example.lostpet.fragments;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lostpet.models.Constants;
import com.example.lostpet.R;
import com.example.lostpet.models.VolleyConfigSingleton;
import com.example.lostpet.helpers.UtilsValidators;
import com.example.lostpet.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class FragmentLogin extends Fragment {

    public static final String TAG_FRAGMENT_LOGIN = "TAG_FRAGMENT_LOGIN";

    private FirebaseAuth mAuth;

    private ProgressBar progressBar;

    private EditText emailEditText;
    private EditText passwordEditText;

    public static FragmentLogin newInstance() {
        Bundle args = new Bundle();

        FragmentLogin fragment = new FragmentLogin();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            //TO DO
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_login).setOnClickListener(v -> {
            validateEmailAndPassword();
        });

        progressBar = view.findViewById(R.id.pb_loading);

        emailEditText = view.findViewById(R.id.edt_email);
        passwordEditText = view.findViewById(R.id.edt_password);
    }

    private void validateEmailAndPassword() {
        if(getView() == null) {
            return;
        }

        emailEditText = getView().findViewById(R.id.edt_email);
        passwordEditText = getView().findViewById(R.id.edt_password);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(!UtilsValidators.isValidEmail(email)) {
            emailEditText.setError("Invalid Email");
            return;
        } else {
            emailEditText.setError(null);
        }

        if(!UtilsValidators.isValidPassword(password)) {
            passwordEditText.setError("Invalid Password");
            return;
        } else {
            passwordEditText.setError(null);
        }

//        createFirebaseUser(email, password);
        loginUser(email, password);
    }

    private void createFirebaseUser(String email, String password) {
        if (getActivity() == null) {
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.GONE);

                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(getContext(), "Authentication success.",
                                Toast.LENGTH_SHORT).show();

                        goToMainActivity();
                    } else {
                        Toast.makeText(getContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();

                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void loginUser(String email, String password) {
        progressBar.setVisibility(View.VISIBLE);

        String url = "https://reqres.in/api/login";
        JSONObject postData = new JSONObject();
        try {
            postData.put("email", email);
            postData.put("password", password);
        } catch (JSONException ex) {
            Toast.makeText(
                    getContext(),
                    "ERROR: get users failed with error: ${ex.message}",
                    Toast.LENGTH_SHORT
            ).show();
        }

        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                postData,
                response -> {
                    progressBar.setVisibility(View.GONE);
                    Log.e("login", "succes");

                    handleResponse(response);
                },
                error -> {
                    Toast.makeText(
                            getContext(),
                            "ERROR: get users failed with error: ${error.message}",
                            Toast.LENGTH_SHORT).show();

                    progressBar.setVisibility(View.GONE);
                }
        );

        VolleyConfigSingleton.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext()).addToRequestQueue(jsonRequest);
    }

    private void handleResponse(JSONObject response) {
        try {
            String token = response.getString("token");
            saveAccessTokenToSharedPrefs(token);

            goToMainActivity();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveAccessTokenToSharedPrefs(String accessToken) {
        SharedPreferences sharedPrefs = Objects.requireNonNull(getContext()).getSharedPreferences(Constants.ARG_SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(Constants.ARG_ACCESS_TOKEN, accessToken);
        editor.apply();
    }

    private void goToMainActivity() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        Objects.requireNonNull(getActivity()).finish();
    }
}

