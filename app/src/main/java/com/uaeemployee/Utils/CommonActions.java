package com.uaeemployee.Utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonActions {

    public static CustomProgressDialog mCustomProgressDialog;

    public static Context mContext = null;

    public void setActivityContext(Context context) {
        CommonActions.mContext = context;
    }


    public CommonActions(Context context) {
        this.mContext = context;
    }


    public static void showProgressDialog(Context con) {
        mCustomProgressDialog = new CustomProgressDialog(con);
        mCustomProgressDialog.show("");
    }

    public static void DismissesDialog() {

        mCustomProgressDialog.dismiss("");
    }


    public boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;
        String expression = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            isValid = true;
        }

        return isValid;
    }

    public static boolean isNameValid(String name) {
        boolean isValid = false;
        String expression = "^[a-zA-Z ]*$";
        CharSequence inputStr = name;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            isValid = true;
        }

        return isValid;
    }

}
