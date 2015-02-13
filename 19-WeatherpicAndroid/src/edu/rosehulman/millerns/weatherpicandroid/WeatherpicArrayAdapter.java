package edu.rosehulman.millerns.weatherpicandroid;

import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.appspot.millerns_weatherpics.weatherpics.model.Weatherpic;

public class WeatherpicArrayAdapter extends ArrayAdapter<Weatherpic> {

	private Context mContext;

	public WeatherpicArrayAdapter(Context context, int resource,
			int textViewResourceId, List<Weatherpic> pics) {
		super(context, resource, textViewResourceId, pics);
		mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ThumbnailView view;
		if (convertView == null) {
			view = new ThumbnailView(mContext);
		} else {
			view = (ThumbnailView) convertView;
		}
		view.setCaption(getItem(position).getCaption());
		view.setImageUrl(getItem(position).getImageUrl());
		return view;
	}

}
