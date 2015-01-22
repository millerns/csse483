package edu.rosehulman.millerns.exam2;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FriendAdapter extends ArrayAdapter<FriendTime> {

	public FriendAdapter(Context context, ArrayList<FriendTime> friends) {
		super(context, R.layout.row_view, R.id.name, friends);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		FriendTime friend = getItem(position);
		TextView name = (TextView) view.findViewById(R.id.name);
		name.setText(friend.getName());
		TextView time = (TextView) view.findViewById(R.id.time);
		time.setText(friend.getTime());

		return view;
	}
}
