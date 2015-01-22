package edu.rosehulman.millerns.exam2;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

public class MainActivity extends Activity {

	private ArrayList<FriendTime> friends;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView listView = (ListView) findViewById(R.id.list_view);
		
		friends = new ArrayList<FriendTime>();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.add_friend) {
			showFriendDialog();
		}
		return super.onOptionsItemSelected(item);
	}

	private void showFriendDialog() {
		DialogFragment df = new DialogFragment() {
			@Override
			public Dialog onCreateDialog(Bundle savedInstanceState) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				builder.setTitle("Add your friend");
				LayoutInflater inflater = getActivity().getLayoutInflater();
				View friendView = inflater
						.inflate(R.layout.friend_dialog, null);
				builder.setView(friendView);
				final EditText editText = (EditText) friendView
						.findViewById(R.id.name_entry);
				final TimePicker timePicker = (TimePicker) friendView
						.findViewById(R.id.time_picker);
				builder.setNegativeButton(android.R.string.cancel, null);
				builder.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								String name = editText.getText().toString();
								int hour = timePicker.getCurrentHour();
								int minute = timePicker.getCurrentMinute();
								friends.add(new FriendTime(name, hour, minute));
							}
						});
				return builder.create();
			}
		};
		df.show(getFragmentManager(), "add_friend");
	}

}
