package com.example.internprojectday1;

import android.app.Application;
import android.content.Intent;

public class ApplicationClass extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        startActivity(new Intent(this, OtpVerification.class));
    }
}
