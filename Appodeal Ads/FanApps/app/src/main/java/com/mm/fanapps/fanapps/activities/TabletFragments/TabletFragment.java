package com.mm.fanapps.fanapps.activities.TabletFragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mm.fanapps.fanapps.R;
public class TabletFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout for this fragment

		final View rootView = inflater.inflate(R.layout.tablet_horizontal_cheats,
				container, false);

		Toast.makeText(getActivity(), "Called", Toast.LENGTH_SHORT).show();
		return rootView;

	}

}
