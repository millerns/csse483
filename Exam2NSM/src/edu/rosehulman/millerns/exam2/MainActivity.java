package edu.rosehulman.millerns.exam2;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener,
		MultiChoiceModeListener {

	private static final String FRIEND_ARRAY_KEY = "FRIEND_ARRAY_KEY";
	public static final String FRIEND_NAME_KEY = "FRIEND_NAME_KEY";
	private static final String ADD_FRIEND_TAG = "add_friend";
	private static final String DELETE_FRIEND_TAG = "delete_friend";
	private ArrayList<FriendTime> mFriends;
	private FriendAdapter mAdapter;
	private ListView mListView;
	private boolean[] mChecked;

	// private ActionMode mActionMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mFriends = new ArrayList<FriendTime>();

		if (savedInstanceState != null) {
			String[] friends = savedInstanceState
					.getStringArray(FRIEND_ARRAY_KEY);
			for (String s : friends) {
				mFriends.add(new FriendTime(s));
			}
		} else {
			// Add some friends
			mFriends.add(new FriendTime("Nick", 10, 20));
			mFriends.add(new FriendTime("Scott", 3, 4));
			mFriends.add(new FriendTime("Kenny", 15, 36));
			mFriends.add(new FriendTime("Peter", 7, 8));

		}

		mAdapter = new FriendAdapter(this, mFriends);

		mListView = (ListView) findViewById(R.id.list_view);
		mListView.setAdapter(mAdapter);

		mListView.setOnItemClickListener(this);
		// listView.setOnItemLongClickListener(this);
		mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		mListView.setMultiChoiceModeListener(this);

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
				builder.setTitle(R.string.add_friend_title);
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
								if (name.length() < 1) {
									Toast.makeText(getActivity(),
											R.string.name_error_prompt,
											Toast.LENGTH_LONG).show();
									return;
								}
								int hour = timePicker.getCurrentHour();
								int minute = timePicker.getCurrentMinute();
								mFriends.add(new FriendTime(name, hour, minute));
							}
						});
				return builder.create();
			}
		};
		df.show(getFragmentManager(), ADD_FRIEND_TAG);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view,
			final int position, long id) {
		// Bring up deletion dialog
		DialogFragment df = new DialogFragment() {
			@Override
			public Dialog onCreateDialog(Bundle savedInstanceState) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				builder.setTitle(R.string.deletion_title);
				builder.setMessage(R.string.deletion_message);
				builder.setNegativeButton(android.R.string.cancel, null);
				builder.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// Remove the item at position
								mFriends.remove(position);
								// Update the adapter
								mAdapter.notifyDataSetChanged();
							}
						});
				return builder.create();
			}
		};
		df.show(getFragmentManager(), DELETE_FRIEND_TAG);
	}

	@Override
	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		MenuInflater inflater = mode.getMenuInflater();
		inflater.inflate(R.menu.context_menu, menu);
		mChecked = new boolean[mFriends.size()];
		for (int i = 0; i < mChecked.length; i++) {
			mChecked[i] = false;
		}
		return true;
	}

	@Override
	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		return false;
	}

	@Override
	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.group:
			// send intent
			String[] friends = new String[mListView.getCheckedItemCount()];
			int count = 0;
			SparseBooleanArray checkedItems = mListView
					.getCheckedItemPositions();
			for (int i = 0; i < checkedItems.size(); i++) {
				if (checkedItems.get(i, false))
					friends[count++] = ((FriendTime) mListView
							.getItemAtPosition(i)).getName();
			}

			Intent intent = new Intent(this, FriendActivity.class);
			intent.putExtra(FRIEND_NAME_KEY, friends);
			startActivity(intent);
			return true;
		default:
			return false;
		}
	}

	@Override
	public void onDestroyActionMode(ActionMode mode) {
	}

	@Override
	public void onItemCheckedStateChanged(ActionMode mode, int position,
			long id, boolean checked) {
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		String[] friendArray = new String[mFriends.size()];
		int count = 0;
		for (FriendTime ft : mFriends) {
			friendArray[count++] = ft.getName() + "&" + ft.getTime();
		}
		outState.putStringArray(FRIEND_ARRAY_KEY, friendArray);
	}

}
