package com.mm.fanapps.fanapps.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mm.fanapps.fanapps.R;

/**
 * Created by Ali Sher on 1/27/2016.
 */
public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /****** Create Thread that will sleep for 5 seconds *************/
        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(5*1000);

                    // After 5 seconds redirect to another intent
                    Intent homeIntent=new Intent(getBaseContext(),Home.class);
                    startActivity(homeIntent);

                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();



    }
}
