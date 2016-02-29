package com.mm.fanapps.fanapps.activities.TabletFragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mm.fanapps.fanapps.R;
import com.mm.fanapps.fanapps.activities.First;
import com.mm.fanapps.fanapps.utilities.CommonFunction;
import com.mm.fanapps.fanapps.utilities.Config;

/**
 * Created by Ali Sher on 1/21/2016.
 */
public class TabletTwitterFragment extends Fragment implements View.OnClickListener {

    LinearLayout drawerLL, fragmentLL, parentFragment;
    boolean check = false;
    private Button menuButton;
    private TextView textView;
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tablet_horizontal_twitter, container, false);
        menuButton = (Button) rootView.findViewById(R.id.menu_button);
        textView = (TextView) rootView.findViewById(R.id.menu_text);
        //Get webview
        webView = (WebView) rootView.findViewById(R.id.webView1);
        menuButton.setOnClickListener(this);

        final String url = Config.twitterLink;
        startWebView(url);

        ////////////////// Ads Code here  ////////////////////
        if (Config.ADS_VERSION){
            CommonFunction commonFunction = new CommonFunction();
            commonFunction.AppodealFunction(getActivity());

        }
        ////////////////// Ads Code here  ////////////////////

        return rootView;
    }


    private void startWebView(String url) {

        //Create new webview Client to show progress dialog
        //When opening a url or click on link

        webView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;

            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                return true;
            }

            //Show loader on url load
//            public void onLoadResource(WebView view, String url) {
//                if (progressDialog == null) {
//                    // in standard case YourActivity.this
//                    progressDialog = new ProgressDialog(getActivity());
//                    progressDialog.setMessage("Loading...");
//                    progressDialog.show();
//                }
//            }

            public void onPageFinished(WebView view, String url) {
                try {
                    progressDialog.dismiss();
                    progressDialog = null;

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        });

        // Javascript inabled on webview
        webView.getSettings().setJavaScriptEnabled(true);

        //Load url in webview
        webView.loadUrl(url);


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
