package com.example.reminisce_ticketing;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    public static String IsUserLogin = "isUserLogin";
    public static String IsUserToken = "isUserToken";
    public static void saveBoolean(String key, Boolean value, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferance", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putBoolean(key, value);
        myEdit.apply();
    }

    public static Boolean getBoolean(String key, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferance", MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }
    public static void saveUserToken(String userToken,Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(IsUserToken, userToken);
        editor.apply();
    }
    public static String getUserToken(String key, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }
}
