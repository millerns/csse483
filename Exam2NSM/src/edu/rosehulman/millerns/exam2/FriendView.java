package edu.rosehulman.millerns.exam2;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FriendView extends LinearLayout {

	private TextView mNameTextView;
	private TextView mTimeTextView;

	public FriendView(Context context) {
		super(context);

		LayoutInflater.from(context).inflate(R.layout.row_view, this);
		mNameTextView = (TextView) findViewById(R.id.name);
		mTimeTextView = (TextView) findViewById(R.id.time);

	}

	public void setName(String name) {
		mNameTextView.setText(name);
	}

	public void setTime(String time) {
		mTimeTextView.setText(time);
	}

}
