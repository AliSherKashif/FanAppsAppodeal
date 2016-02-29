package com.mm.fanapps.fanapps.activities;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.NonSkippableVideoCallbacks;
import com.mm.fanapps.fanapps.R;
import com.mm.fanapps.fanapps.activities.TabletFragments.DrawerFragment;
import com.mm.fanapps.fanapps.activities.TabletFragments.TabletCheatsFragment;
import com.mm.fanapps.fanapps.activities.youtube.domain.Library;
import com.mm.fanapps.fanapps.activities.youtube.task.GetYouTubeUserVideosTask;
import com.mm.fanapps.fanapps.activities.youtube.widget.VideosListView;
import com.mm.fanapps.fanapps.utilities.Config;


/**
 * Created by Ali Sher on 1/25/2016.
 */
public class First extends Activity {

    public static LinearLayout drawerLL, fragmentLL, parentFragment;
    private int height =0;
    private int width =0;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablet_horizontal_activity);


        // height and width resolution of devices
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;

        drawerLL = (LinearLayout) findViewById(R.id.drawer_fragment_LL);
        fragmentLL = (LinearLayout) findViewById(R.id.other_fragment_LL);
        parentFragment = (LinearLayout) findViewById(R.id.fragments_LL);


        /////////////////////////// Video Code portion here ///////////////////
        if (Config.ADS_VERSION) {
            String appKey = Config.APPODEAL_APP_KEY;
            Appodeal.initialize(this, appKey, Appodeal.NON_SKIPPABLE_VIDEO);
            Appodeal.setTesting(true);

            Appodeal.setNonSkippableVideoCallbacks(new NonSkippableVideoCallbacks() {
                private Toast mToast;

                @Override
                public void onNonSkippableVideoLoaded() {
                    //Appodeal.show(MainActivity.this, Appodeal.NON_SKIPPABLE_VIDEO);
                    showToast("onNonSkippableVideoLoaded");
                }

                @Override
                public void onNonSkippableVideoFailedToLoad() {
                    showToast("onNonSkippableVideoFailedToLoad");
                    finish();
                }

                @Override
                public void onNonSkippableVideoShown() {
                    showToast("onNonSkippableVideoShown");
                }

                @Override
                public void onNonSkippableVideoFinished() {
                    showToast("onNonSkippableVideoFinished");
                    finish();
                }

                @Override
                public void onNonSkippableVideoClosed() {
                    showToast("onNonSkippableVideoClosed");
                    finish();
                }

                void showToast(final String text) {
                    if (mToast == null) {
                        mToast = Toast.makeText(First.this, text, Toast.LENGTH_SHORT);
                    }
                    mToast.setText(text);
                    mToast.setDuration(Toast.LENGTH_SHORT);
                    // mToast.show();
                }
            });
        }


    }

    private void OrientationPortrait() {
        // TODO Auto-generated method stub
        Intent homeIntent=new Intent(getBaseContext(),Home.class);
        startActivity(homeIntent);


    }

    private void OrientationLandScape() {
        // TODO Auto-generated method stub



    }



    // ////////////////////// orientation of screens

    @SuppressWarnings("deprecation")
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen for landscape and portrait
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            OrientationLandScape();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            OrientationPortrait();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (Config.ADS_VERSION) {


                AlertDialog.Builder alert = new AlertDialog.Builder(
                        First.this);
                alert.setTitle(R.string.app_name);
                alert.setIcon(R.drawable.ic_logo_profile);
                alert.setMessage("Do you want to Quiet");

                alert.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                //call your method here then finish activity;
                                Appodeal.show(First.this, Appodeal.NON_SKIPPABLE_VIDEO);

                            }


                        });

                alert.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub

                            }
                        });
                alert.show();
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);

    }


}
