package com.mm.fanapps.fanapps.activities.TabletFragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.mm.fanapps.fanapps.R;
import com.mm.fanapps.fanapps.activities.First;
import com.mm.fanapps.fanapps.activities.YoutubePlayer;
import com.mm.fanapps.fanapps.activities.youtube.domain.Video;
import com.mm.fanapps.fanapps.utilities.CommonFunction;
import com.mm.fanapps.fanapps.utilities.Config;
import com.mm.fanapps.fanapps.utilities.ConnectionDetector;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import adapter.VideosAdapter;

/**
 * Created by Ali Sher on 1/21/2016.
 */
public class TabletRecordedgamesFragment extends Fragment implements View.OnClickListener {

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
        View rootView = inflater.inflate(R.layout.tablet_horizontal_recordedgames, container, false);
        menuButton = (Button) rootView.findViewById(R.id.menu_button);
        textView = (TextView) rootView.findViewById(R.id.menu_text);
        menuButton.setOnClickListener(this);
        vidoesList = new ArrayList<Video>();
        cheatListView = (ListView) rootView.findViewById(R.id.hor_recolistView);

        // preferences initialization...

        Config.prefs = this.getActivity().getSharedPreferences(Config.APP_PREFS_NAME, getActivity().MODE_PRIVATE);
        Config.editor = this.getActivity().getSharedPreferences(Config.APP_PREFS_NAME, getActivity().MODE_PRIVATE).edit();

        cheatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = vidoesList.get(position).getUrl();
                Intent playerIntent = new Intent(getActivity(),
                        YoutubePlayer.class);
                playerIntent.putExtra("video_id", url);
                startActivity(playerIntent);

            }
        });
        final String url = Config.recordedGamesURL;
        ConnectionDetector connectionDetector = new ConnectionDetector(getActivity());
        if (connectionDetector.isConnectingToInternet()){
            boolean recordgamecheck = Config.prefs.getBoolean("recordgamecheck", false);
            if (recordgamecheck) {
                CommonFunction commonFunction = new CommonFunction();
                String response = Config.prefs.getString("recordgameresponse", "");

                if(response.equals("")){
                    Toast.makeText(getActivity(), "No videos found", Toast.LENGTH_SHORT).show();

                }else{

                    vidoesList = commonFunction.parseResult(response);
                    videosAdapter = new VideosAdapter(getActivity(), vidoesList);
                    cheatListView.setAdapter(videosAdapter);

                }




            } else {
                new AsyncHttpTask().execute(url);
            }

        }else{

            Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_SHORT).show();

        }

        ////////////////// Ads Code here  ////////////////////
        if (Config.ADS_VERSION){
            CommonFunction commonFunction = new CommonFunction();
            commonFunction.AppodealFunction(getActivity());

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



    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(getActivity(), "Please Wait",
                    "Fetching videos...", true);
            progressDialog.setCancelable(true);




        }

        @Override
        protected Integer doInBackground(String... params) {
            InputStream inputStream = null;
            HttpURLConnection urlConnection = null;
            Integer result = 0;
            try {
                /* forming th java.net.URL object */
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                 /* optional request header */
                urlConnection.setRequestProperty("Content-Type", "application/json");

                /* optional request header */
                urlConnection.setRequestProperty("Accept", "application/json");

                /* for Get request */
                urlConnection.setRequestMethod("GET");
                int statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
                if (statusCode ==  200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    CommonFunction commonFunction = new CommonFunction();
                    String response = commonFunction.convertInputStreamToString(inputStream);
                    Config.editor.putString("recordgameresponse", response);
                    Config.editor.commit();
                    vidoesList = commonFunction.parseResult(response);
                    result = 1; // Successful

                }else{
                    result = 0; //"Failed to fetch data!";
                }

            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }

            return result; //"Failed to fetch data!";
        }


        @Override
        protected void onPostExecute(Integer result) {
            /* Download complete. Lets update UI */
            if(result == 1){
                videosAdapter = new VideosAdapter(getActivity(), vidoesList);
                cheatListView.setAdapter(videosAdapter);
                progressDialog.dismiss();
                Config.editor.putBoolean("recordgamecheck", true);
                Config.editor.commit();
            }else{
                Log.e(TAG, "Failed to fetch data!");
                progressDialog.dismiss();
            }
        }
    }


}
