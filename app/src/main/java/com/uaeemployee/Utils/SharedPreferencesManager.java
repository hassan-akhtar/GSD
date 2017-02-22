package com.uaeemployee.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor ;
    public static final String MY_PREFERENCES = "MyPrefs" ;
    public static final String CURRENT_GENDER = "gender";
    public static final String CURRENT_NATIONALITY = "nationality";
    public static final String CURRENT_TITLE = "first";
    public static final String CURRENT_JOB_LEVEL = "second";
    public static final String CURRENT_JOB_TYPE = "third";
    public static final String IS_VACANCY = "isvacancy";


    public SharedPreferencesManager(Context context) {
        sharedpreferences = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();;
        editor = sharedpreferences.edit();
    }



    public  void setString(String key, String value ,  Context context){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public  String getString(String key , Context context){
        return sharedpreferences.getString(key, "Not Found");
    }


    public  void setBoolean(String key, boolean value ,  Context context){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

    public  boolean getBoolean(String key , Context context){
        return sharedpreferences.getBoolean(key, false);
    }
}
