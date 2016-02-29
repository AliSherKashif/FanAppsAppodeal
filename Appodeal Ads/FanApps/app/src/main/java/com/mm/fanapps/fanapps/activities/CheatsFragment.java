package com.mm.fanapps.fanapps.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mm.fanapps.fanapps.R;
import com.mm.fanapps.fanapps.utilities.Config;

/**
 * Created by Ali Sher on 1/21/2016.
 */
public class CheatsFragment extends Fragment{

    private TextView textView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cheats, container, false);
        textView = (TextView) rootView.findViewById(R.id.textView);
        textView.setText(Config.gameCheats);
        Typeface face= Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
        textView.setTypeface(face);

        // Inflate the layout for this fragment
        return rootView;
    }

}
