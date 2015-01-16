package edu.rosehulman.millerns.arrayadapter;

import java.util.ArrayList;

import android.content.Context;
import android.widget.ArrayAdapter;

public class BirdAdapter extends ArrayAdapter<StateBird> {

	public BirdAdapter(Context context, ArrayList<StateBird> birds) {
		super(context, android.R.layout.simple_expandable_list_item_2,
				android.R.id.text1, birds);
		// TODO Auto-generated constructor stub
	}

}
