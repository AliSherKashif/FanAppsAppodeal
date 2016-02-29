package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.mm.fanapps.fanapps.R;

/**
 * Created by Ali Sher on 2/12/2016.
 */
public class GridAdapter extends BaseAdapter {

    private Context mContext;

    public GridAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.icon_barcode, R.drawable.icon_game,
            R.drawable.icon_livewallpaper, R.drawable.icon_kickbuddy,
            R.drawable.icon_nyfw, R.drawable.icon_pmath,
            R.drawable.icon_qrreader, R.drawable.icon_scanpro,
            R.drawable.icon_scrubby, R.drawable.icon_wc,
            R.drawable.icon_wchef, R.drawable.icon_
    };
}
