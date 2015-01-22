package edu.rosehulman.millerns.exam2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FriendActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friend_activity);

		Intent intent = getIntent();
		String[] friends = intent
				.getStringArrayExtra(MainActivity.FRIEND_NAME_KEY);

		String friendString = "";
		for (int i = 0; i < friends.length; i++) {
			friendString += friends[i] + "\n";
		}

		TextView friendList = (TextView) findViewById(R.id.friend_list);
		friendList.setText(friendString);

	}
}
