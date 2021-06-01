package com.example.lostpet.helpers;

import android.text.TextUtils;
import android.util.Patterns;
public class UtilsValidators {



   // private static boolean isValidEmail(String email)
   // {
   //     return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    //}

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return !TextUtils.isEmpty(password) && password.length()>=6;
    }
}
