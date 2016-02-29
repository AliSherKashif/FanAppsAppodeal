package com.mm.fanapps.fanapps.activities.TabletFragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mm.fanapps.fanapps.R;
import com.mm.fanapps.fanapps.activities.First;
import com.mm.fanapps.fanapps.utilities.Config;

/**
 * Created by Ali Sher on 1/21/2016.
 */
public class TabletCheatsFragment extends Fragment implements View.OnClickListener {

    LinearLayout drawerLL, fragmentLL, parentFragment;
    boolean check = false;
    private Button menuButton;
    private TextView textView, cheatText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tablet_horizontal_cheats, container, false);
           menuButton = (Button) rootView.findViewById(R.id.menu_button);
           textView = (TextView) rootView.findViewById(R.id.menu_text);
           cheatText = (TextView) rootView.findViewById(R.id.hortextView);
           cheatText.setText(Config.gameCheats);
           Typeface face= Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
           cheatText.setTypeface(face);


           menuButton.setOnClickListener(this);

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
