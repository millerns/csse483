package edu.rosehulman.millerns.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RowView extends LinearLayout {

	private TextView mLeftTextView;
	private TextView mRightTextView;

	public RowView(Context context) {
		super(context);
		// // create view
		// mLeftTextView = new TextView(context);
		// mRightTextView = new TextView(context);
		// // modify view
		// // add to layout
		// addView(mLeftTextView);
		// addView(mRightTextView);
		// setOrientation(HORIZONTAL);

		// ((Activity)context).getLayoutInflater().in
		LayoutInflater.from(context).inflate(R.layout.row_view, this);
		mLeftTextView = (TextView) findViewById(R.id.left_text_view);
		mRightTextView = (TextView) findViewById(R.id.right_text_view);
		
	}

	public void setLeftText(String text) {
		mLeftTextView.setText(text);
	}

	public void setRightText(String text) {
		mRightTextView.setText(text);
	}

}
