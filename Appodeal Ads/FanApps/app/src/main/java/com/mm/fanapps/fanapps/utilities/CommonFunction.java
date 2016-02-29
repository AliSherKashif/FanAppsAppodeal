package com.mm.fanapps.fanapps.utilities;

import android.app.Activity;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.mm.fanapps.fanapps.activities.youtube.domain.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Ali Sher on 1/29/2016.
 */
public class CommonFunction {
    public CommonFunction() {
    }
    public String convertInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));

        String line = "";
        String result = "";

        while((line = bufferedReader.readLine()) != null){
            result += line;
        }

            /* Close Stream */
        if(null!=inputStream){
            inputStream.close();
        }

        return result;
    }


    public ArrayList<Video> parseResult(String result) {
        String url, title = null, thumb = null;

        ArrayList<Video> vidoesList = new ArrayList<Video>();


        try{
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("items");
            for(int i=0; i< posts.length();i++ ){
                JSONObject post = posts.optJSONObject(i);
                url = post.getJSONObject("id").getString("videoId");
                title = post.getJSONObject("snippet").getString(
                        "title");
                thumb = post.getJSONObject("snippet")
                        .getJSONObject("thumbnails").getJSONObject("high")
                        .getString("url");
                // Create the video object and add it to our list
                if (title.equals("") || url.equals("") || thumb.equals("")) {

                } else {
                    Video video = new Video(title, url, thumb);
                    vidoesList.add(video);

                }
            }


        }catch (JSONException e){
            e.printStackTrace();
            url = "";
            title = "";
            thumb = "";

        }

        return vidoesList;
    }

    public void AppodealFunction(final Activity activity){
        String appKey = Config.APPODEAL_APP_KEY;
        Appodeal.setTesting(true);
        Appodeal.initialize(activity, appKey, Appodeal.BANNER_BOTTOM);
        Appodeal.show(activity, Appodeal.BANNER_BOTTOM);

//        Appodeal.setAutoCache(Appodeal.INTERSTITIAL, false);
//        Appodeal.initialize(activity, appKey, Appodeal.INTERSTITIAL);
//        Appodeal.cache(activity, Appodeal.INTERSTITIAL);
//        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
//            public void onInterstitialLoaded(boolean isPrecache) {
//                Appodeal.show(activity, Appodeal.INTERSTITIAL);
//            }
//            public void onInterstitialFailedToLoad() {
//            }
//
//            public void onInterstitialShown() {
//            }
//
//            public void onInterstitialClicked() {
//            }
//
//            public void onInterstitialClosed() {
//            }
//        });
    }

    public void AppodealFunctionTop(final Activity activity){
        String appKey = Config.APPODEAL_APP_KEY;
        Appodeal.setTesting(true);

        Appodeal.setAutoCache(Appodeal.INTERSTITIAL, false);
        Appodeal.initialize(activity, appKey, Appodeal.INTERSTITIAL);
        Appodeal.cache(activity, Appodeal.INTERSTITIAL);
        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
            public void onInterstitialLoaded(boolean isPrecache) {
                Appodeal.show(activity, Appodeal.INTERSTITIAL);
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
}
