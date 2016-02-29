package adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mm.fanapps.fanapps.R;
import com.mm.fanapps.fanapps.activities.TabletFragments.DrawerFragment;
import com.mm.fanapps.fanapps.model.DrawerItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DrawerAdapter extends ArrayAdapter<DrawerItem> {

	private Context context;
	private LayoutInflater vi;

	private List<DrawerItem> worldpopulationlist = null;
	private ArrayList<DrawerItem> arraylist;

	SharedPreferences pref;

	public DrawerAdapter(Context context,
						 ArrayList<DrawerItem> worldpopulationlist) {
		super(context, 0, worldpopulationlist);
		this.context = context;


		this.worldpopulationlist = worldpopulationlist;

		this.arraylist = new ArrayList<DrawerItem>();
		this.arraylist.addAll(worldpopulationlist);

		vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;

		rowView = vi.inflate(R.layout.drawer_list_single, null);

		// contact name plus contact status TextView
		TextView itemName = (TextView) rowView
				.findViewById(R.id.item_text);
		itemName.setText(worldpopulationlist.get(position).getItemName());

		Typeface face= Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
		itemName.setTypeface(face);
		if (DrawerFragment.selected_tablet_drawer_position == position){
			itemName.setTextColor(Color.RED);
		}else{
			itemName.setTextColor(Color.GRAY);
		}


		ImageView itemImage = (ImageView) rowView
				.findViewById(R.id.item_imageView);
		itemImage.setImageResource(worldpopulationlist.get(position)
				.getItemImage());

		return rowView;
	}

	// Filter Class
	public void filter(String charText, Button btn,
			ListView searchContactsListView) {
		charText = charText.toLowerCase(Locale.getDefault());
		worldpopulationlist.clear();
		if (charText.length() == 0) {
			worldpopulationlist.addAll(arraylist);
			// btn.setVisibility(View.INVISIBLE);
			searchContactsListView.setVisibility(View.INVISIBLE);
		} else {
			for (DrawerItem wp : arraylist) {
				if (wp.getItemName().toLowerCase(Locale.getDefault())
						.contains(charText)) {
					worldpopulationlist.add(wp);
					// btn.setVisibility(View.VISIBLE);
				}
			}
		}
		notifyDataSetChanged();
	}

	public void clearAdapter1() {
		arraylist.clear();
		worldpopulationlist.clear();
		notifyDataSetChanged();
	}

	public void notifyAdapter1() {
		notifyDataSetChanged();
	}

}
