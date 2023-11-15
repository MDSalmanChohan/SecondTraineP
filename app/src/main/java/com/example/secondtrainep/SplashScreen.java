package com.example.secondtrainep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.content.SharedPreferences;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                try {
//                  used in shared prefrence using store login page and registration page data.

                    SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(SplashScreen.this);

                    if (sharedPreferencesManager.isLoggedIn()) {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));

                    } else {
                        // when those activity to direct open splash to login activity. these are the most expensive work from my side
                        startActivity(new Intent(SplashScreen.this, LogActivity.class));

                        //thhose are increament in this line to build start activity in this flow.


                    }
                }
                catch (Exception e)
                {e.printStackTrace();}

                finally {

                    finishAffinity();
                }


            }
        }, 3000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}