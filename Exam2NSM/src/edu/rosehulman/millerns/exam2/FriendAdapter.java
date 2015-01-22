package edu.rosehulman.millerns.exam2;

import java.util.ArrayList;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class FriendAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<FriendTime> mFriends;

	public FriendAdapter(Context context, ArrayList<FriendTime> friends) {
		mContext = context;
		mFriends = friends;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);

		return view;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

}
