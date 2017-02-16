package com.uaeemployee.Application;

import android.app.Application;

public class MyApplication extends Application{

    private int statusCode;

    private static MyApplication myApplication;

    public static MyApplication getInstance(){
        return myApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
