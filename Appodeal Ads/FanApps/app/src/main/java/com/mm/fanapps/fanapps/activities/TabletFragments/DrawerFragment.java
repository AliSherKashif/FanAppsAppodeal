package com.mm.fanapps.fanapps.activities.TabletFragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.mm.fanapps.fanapps.R;
import com.mm.fanapps.fanapps.activities.CheatsFragment;
import com.mm.fanapps.fanapps.activities.FacebookFragment;
import com.mm.fanapps.fanapps.activities.LivestreamingFragment;
import com.mm.fanapps.fanapps.activities.RSSFragment;
import com.mm.fanapps.fanapps.activities.RecordedgamesFragment;
import com.mm.fanapps.fanapps.activities.TwitterFragment;
import com.mm.fanapps.fanapps.activities.WalkthroughFragment;
import com.mm.fanapps.fanapps.model.DrawerItem;
import com.mm.fanapps.fanapps.utilities.Config;

import java.util.ArrayList;

import adapter.DrawerAdapter;


public class DrawerFragment extends Fragment implements AdapterView.OnItemClickListener {



	public static ListView drawerListView;

	public static ArrayList<DrawerItem> drawerItems;
	public static DrawerAdapter drawerAdapter;
	private static String[] titles = null;
	public static int selected_tablet_drawer_position = 0;
	TabletCheatsFragment cheatFragment = new TabletCheatsFragment();


	// ////////////////////////

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.tablet_horizontal_drawer,
				container, false);

		drawerListView = (ListView) rootView.findViewById(R.id.drawer_list);

		PopulateListView();




		drawerListView.setOnItemClickListener(this);

		return rootView;

	}

	private void PopulateListView() {


		drawerItems = new ArrayList<DrawerItem>();

		// drawer labels
		titles = Config.titles;

		// preparing navigation drawer items
		for (int i = 0; i < titles.length; i++) {
			DrawerItem navItem = new DrawerItem();
			if(selected_tablet_drawer_position == i){
				navItem.setItemImage(Config.ICONS_SELECTED[i]);
			}else{
				navItem.setItemImage(Config.ICONS[i]);
			}

			navItem.setItemName(titles[i]);
			drawerItems.add(navItem);
		}

		drawerAdapter = new DrawerAdapter(getActivity(), drawerItems);
		drawerListView.setAdapter(drawerAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		final FragmentTransaction ft = getFragmentManager().beginTransaction();
		selected_tablet_drawer_position = position;
		String title = Config.titles[position];

		switch (title) {
			case "Cheats":

				ft.replace(R.id.fragments_place, new TabletCheatsFragment(), "NewFragmentTag");
				ft.commit();
				break;

			case "Walkthrough":

				ft.replace(R.id.fragments_place, new TabletWalkthroughFragment(), "NewFragmentTag");
				ft.commit();
				break;

			case "Live Streaming":

				ft.replace(R.id.fragments_place, new TabletLivestreamingFragment(), "NewFragmentTag");
				ft.commit();
				break;

			case "Recorded Games":

				ft.replace(R.id.fragments_place, new TabletRecordedgamesFragment(), "NewFragmentTag");
				ft.commit();
				break;

			case "RSS":

				ft.replace(R.id.fragments_place, new TabletRSSFragment(), "NewFragmentTag");
				ft.commit();
				break;

			case "Facebook":

				ft.replace(R.id.fragments_place, new TabletFacebookFragment(), "NewFragmentTag");
				ft.commit();
				break;

			case "Twitter":

				ft.replace(R.id.fragments_place, new TabletTwitterFragment(), "NewFragmentTag");
				ft.commit();
				break;

			default:
				break;


		}

		PopulateListView();
	}

}
