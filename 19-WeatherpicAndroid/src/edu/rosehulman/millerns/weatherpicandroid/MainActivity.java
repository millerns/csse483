package edu.rosehulman.millerns.weatherpicandroid;

import java.io.IOException;
import java.util.ArrayList;

import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.appspot.millerns_weatherpics.weatherpics.Weatherpics;
import com.appspot.millerns_weatherpics.weatherpics.Weatherpics.Weatherpic.List;
import com.appspot.millerns_weatherpics.weatherpics.model.Weatherpic;
import com.appspot.millerns_weatherpics.weatherpics.model.WeatherpicCollection;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

public class MainActivity extends ListActivity {

	public static final String WEATHERPIC_URL_KEY = "WEATHERPIC_URL_KEY";
	public static final String WEATHERPIC_CAPTION_KEY = "WEATHERPIC_CAPTION_KEY";
	private static final String WP = "WP";
	private Weatherpics mService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		getListView().setMultiChoiceModeListener(new MyMultiClickListener());

		Weatherpics.Builder builder = new Weatherpics.Builder(
				AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
		mService = builder.build();
		syncItems();

	}

	private class MyMultiClickListener implements MultiChoiceModeListener {

		private ArrayList<Weatherpic> mPicsToDelete = new ArrayList<Weatherpic>();

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			mode.getMenuInflater().inflate(R.menu.context, menu);
			mode.setTitle(R.string.context_delete_title);
			return true;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			switch (item.getItemId()) {
			case R.id.context_delete:
				deleteSelectedItems();
				mode.finish();
				return true;
			}
			return false;
		}

		@Override
		public void onItemCheckedStateChanged(ActionMode mode, int position,
				long id, boolean checked) {
			Weatherpic item = (Weatherpic) getListAdapter().getItem(position);
			if (checked) {
				mPicsToDelete.add(item);
			} else {
				mPicsToDelete.remove(item);
			}
			mode.setTitle("Selected " + mPicsToDelete.size() + " quotes");
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			// Purposefully empty
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			mPicsToDelete = new ArrayList<Weatherpic>();
			return true;
		}

		private void deleteSelectedItems() {
			for (Weatherpic pic : mPicsToDelete) {
				((WeatherpicArrayAdapter) getListAdapter()).remove(pic);
				new DeletePicTask().execute(pic.getEntityKey());
			}
			((WeatherpicArrayAdapter) getListAdapter()).notifyDataSetChanged();
			syncItems();
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		final Weatherpic currentPic = (Weatherpic) getListAdapter().getItem(
				position);
		Intent intent = new Intent(this, DetailedImage.class);
		intent.putExtra(WEATHERPIC_CAPTION_KEY, currentPic.getCaption());
		intent.putExtra(WEATHERPIC_URL_KEY, currentPic.getImageUrl());
		startActivity(intent);
		super.onListItemClick(l, v, position, id);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add:
			addItem();
			return true;
		case R.id.sync:
			syncItems();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void addItem() {
		DialogFragment df = new DialogFragment() {
			@Override
			public View onCreateView(LayoutInflater inflater,
					ViewGroup container, Bundle savedInstanceState) {
				View view = inflater.inflate(R.layout.dialog_add, container);
				getDialog().setTitle("Add a movie and quote");
				final Button confirmButton = (Button) view
						.findViewById(R.id.add_dialog_ok);
				final Button cancelButton = (Button) view
						.findViewById(R.id.add_dialog_cancel);
				final EditText imgCaptionEditText = (EditText) view
						.findViewById(R.id.add_dialog_caption);
				final EditText imgURLEditText = (EditText) view
						.findViewById(R.id.add_dialog_img_url);

				confirmButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String imgCaption = imgCaptionEditText.getText()
								.toString();
						String imgURL = imgURLEditText.getText().toString();
						if (imgURL.length() < 1)
							imgURL = randomImageUrl();
						Toast.makeText(
								MainActivity.this,
								"Got the caption " + imgCaption + " and URL "
										+ imgURL, Toast.LENGTH_LONG).show();
						// add the data and send to server
						Weatherpic currentPic = new Weatherpic();
						currentPic.setCaption(imgCaption);
						currentPic.setImageUrl(imgURL);
						((WeatherpicArrayAdapter) getListAdapter())
								.add(currentPic);
						((WeatherpicArrayAdapter) getListAdapter())
								.notifyDataSetChanged();

						new InsertWeatherpicTask().execute(currentPic);
						dismiss();
					}
				});

				cancelButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dismiss();
					}
				});
				return view;
			}
		};
		df.show(getFragmentManager(), "");

	}

	private void syncItems() {
		new QueryForPicsTask().execute();
	}

	class QueryForPicsTask extends AsyncTask<Void, Void, WeatherpicCollection> {

		@Override
		protected WeatherpicCollection doInBackground(Void... params) {
			WeatherpicCollection pics = null;
			try {
				List query = mService.weatherpic().list();
				query.setOrder("-last_touch_date_time");
				// For more than 50 pics, we need to deal with pageTokens.
				query.setLimit(50L);
				pics = query.execute();
			} catch (IOException e) {
				Log.e(WP, "Failed loading " + e);
			}
			return pics;
		}

		@Override
		protected void onPostExecute(WeatherpicCollection result) {
			super.onPostExecute(result);
			if (result == null) {
				Log.d(WP, "Failed loading, result is null");
				return;
			}

			WeatherpicArrayAdapter adapter = new WeatherpicArrayAdapter(
					MainActivity.this,
					android.R.layout.simple_expandable_list_item_2,
					android.R.id.text1, result.getItems());
			setListAdapter(adapter);
		}

	}

	class InsertWeatherpicTask extends AsyncTask<Weatherpic, Void, Weatherpic> {

		@Override
		protected Weatherpic doInBackground(Weatherpic... pics) {
			Weatherpic returnedPic = null;
			try {
				returnedPic = mService.weatherpic().insert(pics[0]).execute();
			} catch (IOException e) {
				Log.e(WP, "Failed inserting " + e);
			}
			return returnedPic;
		}

		@Override
		protected void onPostExecute(Weatherpic result) {
			//new QueryForPicsTask().execute();
		}
	}

	class DeletePicTask extends AsyncTask<String, Void, Weatherpic> {
		@Override
		protected Weatherpic doInBackground(String... entityKeys) {
			Weatherpic returnedPic = null;
			try {
				returnedPic = mService.weatherpic().delete(entityKeys[0])
						.execute();
			} catch (IOException e) {
				Log.e(WP, "Failed deleting " + e);
			}
			return returnedPic;
		}

		@Override
		protected void onPostExecute(Weatherpic result) {
			super.onPostExecute(result);
			if (result == null) {
				Log.e(WP, "Failed deleting, result is null");
				return;
			}
			syncItems();
		}
	}

	private String randomImageUrl() {
		String[] urls = new String[] {
				"http://upload.wikimedia.org/wikipedia/commons/0/04/Hurricane_Isabel_from_ISS.jpg",
				"http://daraint.org/wp-content/uploads/2010/12/hurricane_dennis-700x465.jpg",
				"http://tornado-facts.com/wp-content/uploads/2009/07/tornadoes1-300x181.jpg",
				"http://severe-wx.pbworks.com/f/tornado.jpg",
				"http://t.wallpaperweb.org/wallpaper/nature/1920x1080/Lightning_Storm_Over_Fort_Collins_Colorado.jpg",
				"http://www.legoengineering.com/wp-content/uploads/2013/06/earthquake.jpg",
				"http://gfxspeak.com/wp-content/uploads/2012/06/Cypress_LCluff_sm.jpg",
				"http://i.telegraph.co.uk/multimedia/archive/02405/weather-flood-sign_2405295b.jpg",
				"http://upload.wikimedia.org/wikipedia/commons/0/00/Flood102405.JPG",
				"http://upload.wikimedia.org/wikipedia/commons/6/6b/Mount_Carmel_forest_fire14.jpg" };
		return urls[(int) (Math.random() * urls.length)];
	}
}
