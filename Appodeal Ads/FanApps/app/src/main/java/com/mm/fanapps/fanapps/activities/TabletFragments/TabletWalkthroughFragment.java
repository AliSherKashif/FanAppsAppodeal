package com.mm.fanapps.fanapps.activities.TabletFragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.mm.fanapps.fanapps.R;
import com.mm.fanapps.fanapps.activities.First;
import com.mm.fanapps.fanapps.activities.YoutubePlayer;
import com.mm.fanapps.fanapps.activities.youtube.domain.Video;
import com.mm.fanapps.fanapps.utilities.CommonFunction;
import com.mm.fanapps.fanapps.utilities.Config;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import adapter.VideosAdapter;

/**
 * Created by Ali Sher on 1/21/2016.
 */
public class TabletWalkthroughFragment extends Fragment implements View.OnClickListener {

    LinearLayout drawerLL, fragmentLL, parentFragment;
    boolean check = false;
    private Button menuButton;
    private TextView textView;

    private static final String TAG = "Http Connection";
    ProgressDialog progressDialog;

    ArrayList<Video> vidoesList;
    VideosAdapter videosAdapter;

    private ListView cheatListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tablet_horizontal_walkthrough, container, false);
        menuButton = (Button) rootView.findViewById(R.id.menu_button);
        textView = (TextView) rootView.findViewById(R.id.menu_text);
        menuButton.setOnClickListener(this);
        VideoView videoView =(VideoView) rootView.findViewById(R.id.hori_videoView1);
        MediaController mediaController= new MediaController(getActivity());
        mediaController.setAnchorView(videoView);
        Uri uri=Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.angry);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        ////////////////// Ads Code here  ////////////////////
        if (Config.ADS_VERSION){
            String appKey = Config.APPODEAL_APP_KEY;
            Appodeal.setTesting(true);

            Appodeal.setAutoCache(Appodeal.INTERSTITIAL, false);
            Appodeal.initialize(getActivity(), appKey, Appodeal.INTERSTITIAL);
            Appodeal.cache(getActivity(), Appodeal.INTERSTITIAL);
            Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
                public void onInterstitialLoaded(boolean isPrecache) {
                    Appodeal.show(getActivity(), Appodeal.INTERSTITIAL);
                }

                public void onInterstitialFailedToLoad() {
                }

                public void onInterstitialShown() {
                }

                public void onInterstitialClicked() {
                }

                public void onInterstitialClosed() {
                }
            });

        }
        ////////////////// Ads Code here  ////////////////////


        return rootView;
    }



    public void onClick(View v) {

        drawerLL = First.drawerLL;
        fragmentLL = First.fragmentLL;
        parentFragment = First.parentFragment;

        if(check){
            check = false;

            LinearLayout.LayoutParams childParam1 = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT);
            childParam1.weight = 0.0f;
            drawerLL.setLayoutParams(childParam1);

            LinearLayout.LayoutParams childParam2 = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT);
            childParam2.weight = 1.0f;
            fragmentLL.setLayoutParams(childParam2);
            parentFragment.removeAllViews();
            parentFragment.setWeightSum(1f);
            parentFragment.addView(drawerLL);
            parentFragment.addView(fragmentLL);



        }else{
            check = true;
            LinearLayout.LayoutParams childParam1 = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT);
            childParam1.weight = 0.3f;
            //childParam1.bottomMargin = 20;
            drawerLL.setLayoutParams(childParam1);

            LinearLayout.LayoutParams childParam2 = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT);
            childParam2.weight = 0.7f;
            //childParam2.bottomMargin = 20;
            fragmentLL.setLayoutParams(childParam2);

            parentFragment.removeAllViews();
            parentFragment.setWeightSum(1f);
            parentFragment.addView(drawerLL);
            parentFragment.addView(fragmentLL);
        }

    }

}
