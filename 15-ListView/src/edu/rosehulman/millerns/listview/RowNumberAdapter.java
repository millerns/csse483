package edu.rosehulman.millerns.listview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RowNumberAdapter extends BaseAdapter {

	private Context mContext;
	private int mNumRows = 5;

	public RowNumberAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		return mNumRows;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView view;
		if (convertView == null) {
			// make one
			view = new TextView(mContext);
			view.setTextSize(24);
		} else {
			// use one passed in
			view = (TextView) convertView;
		}
		// customize it
		view.setText("Row " + position);
		int color = Color.rgb(10 * position, 255 - (10 * position), 100);
		view.setTextColor(color);
		return view;
	}

	public void addView() {
		mNumRows++;
	}

}
