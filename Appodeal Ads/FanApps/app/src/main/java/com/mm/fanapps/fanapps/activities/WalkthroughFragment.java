package com.mm.fanapps.fanapps.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import adapter.VideosAdapter;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.mm.fanapps.fanapps.activities.youtube.domain.Video;
import com.mm.fanapps.fanapps.R;
import com.mm.fanapps.fanapps.utilities.Config;

import java.util.ArrayList;

/**
 * Created by Ali Sher on 1/21/2016.
 */
public class WalkthroughFragment extends Fragment{


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_walkthrough, container, false);
        final VideoView videoView =(VideoView) rootView.findViewById(R.id.videoView1);

        MediaController mediaController= new MediaController(getActivity());
        mediaController.setAnchorView(videoView);

        Uri uri=Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.angry);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i("Video", "Duration = " +
                        videoView.getDuration());
            }
        });

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

        // Inflate the layout for this fragment
        return rootView;
    }

}