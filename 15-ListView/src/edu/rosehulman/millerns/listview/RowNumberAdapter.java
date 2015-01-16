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
	private String[] mMonths;

	public RowNumberAdapter(Context context) {
		mContext = context;
		mMonths = context.getResources().getStringArray(R.array.month_names);
	}

	@Override
	public int getCount() {
		return mNumRows;
	}

	@Override
	public Object getItem(int position) {
		return mMonths[position % 12];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RowView view;
		if (convertView == null) {
			// make one
			view = new RowView(mContext);
		} else {
			// use one passed in
			view = (RowView) convertView;
		}
		// customize it
		view.setLeftText((position + 1) + ".");
		view.setRightText(mMonths[position % 12]);
		// int color = Color.rgb(10 * position, 255 - (10 * position), 100);
		// view.setTextColor(color);
		return view;
	}

	public void addView() {
		mNumRows++;
	}

}
