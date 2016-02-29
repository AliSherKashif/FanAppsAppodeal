package com.mm.fanapps.fanapps.activities.TabletFragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.os.ResultReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mm.fanapps.fanapps.R;
import com.mm.fanapps.fanapps.activities.First;
import com.mm.fanapps.fanapps.model.RssItem;
import com.mm.fanapps.fanapps.utilities.CommonFunction;
import com.mm.fanapps.fanapps.utilities.Config;
import com.mm.fanapps.fanapps.utilities.RssService;

import java.util.List;

import adapter.RssAdapter;

/**
 * Created by Ali Sher on 1/21/2016.
 */
public class TabletRSSFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    LinearLayout drawerLL, fragmentLL, parentFragment;
    boolean check = false;
    private Button menuButton;
    private TextView textView;
    private ProgressBar progressBar;
    private ListView listView;
    private View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (rootView == null) {
            rootView = inflater.inflate(R.layout.tablet_horizontal_rss, container, false);
            menuButton = (Button) rootView.findViewById(R.id.menu_button);
            textView = (TextView) rootView.findViewById(R.id.menu_text);
            menuButton.setOnClickListener(this);
            progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
            listView = (ListView) rootView.findViewById(R.id.listView);
            listView.setOnItemClickListener(this);
            startService();
        } else {
            // If we are returning from a configuration change:
            // "view" is still attached to the previous view hierarchy
            // so we need to remove it and re-attach it to the current one
            ViewGroup parent = (ViewGroup) rootView.getParent();
            parent.removeView(rootView);
        }

        ////////////////// Ads Code here  ////////////////////
        if (Config.ADS_VERSION){
            CommonFunction commonFunction = new CommonFunction();
            commonFunction.AppodealFunction(getActivity());

        }
        ////////////////// Ads Code here  ////////////////////

        return rootView;
    }

    private void startService() {
        Intent intent = new Intent(getActivity(), RssService.class);
        intent.putExtra(RssService.RECEIVER, resultReceiver);
        getActivity().startService(intent);
    }

    /**
     * Once the {@link RssService} finishes its task, the result is sent to this ResultReceiver.
     */
    private final ResultReceiver resultReceiver = new ResultReceiver(new Handler()) {
        @SuppressWarnings("unchecked")
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            List<RssItem> items = (List<RssItem>) resultData.getSerializable(RssService.ITEMS);
            if (items != null) {
                RssAdapter adapter = new RssAdapter(getActivity(), items);
                listView.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "An error occured while downloading the rss feed.",
                        Toast.LENGTH_LONG).show();
            }
            progressBar.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }

        ;
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RssAdapter adapter = (RssAdapter) parent.getAdapter();
        RssItem item = (RssItem) adapter.getItem(position);
        Uri uri = Uri.parse(item.getLink());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
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
