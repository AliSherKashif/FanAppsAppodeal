package com.mm.fanapps.fanapps.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mm.fanapps.fanapps.R;
import com.mm.fanapps.fanapps.utilities.CommonFunction;
import com.mm.fanapps.fanapps.utilities.Config;

/**
 * Created by Ali Sher on 1/21/2016.
 */
public class TwitterFragment extends Fragment {
    private WebView webView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_twitter, container, false);

        final String url = Config.twitterLink;
        //Get webview
        webView = (WebView) rootView.findViewById(R.id.webView1);

        startWebView(url);

        ////////////////// Ads Code here  ////////////////////
        if (Config.ADS_VERSION){
            CommonFunction commonFunction = new CommonFunction();
            commonFunction.AppodealFunction(getActivity());

        }

        ////////////////// Ads Code here  ////////////////////
        // Inflate the layout for this fragment
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

}
