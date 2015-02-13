package edu.rosehulman.millerns.weatherpicandroid;

import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class DetailedImage extends Activity {

	private ImageView mWeatherpicView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed_image);

		mWeatherpicView = (ImageView) findViewById(R.id.weatherpic_detail);

		Intent intent = getIntent();
		String caption = intent
				.getStringExtra(MainActivity.WEATHERPIC_CAPTION_KEY);
		String url = intent.getStringExtra(MainActivity.WEATHERPIC_URL_KEY);

		this.setTitle(caption);
		new DownloadImageTask().execute(url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detailed_image, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		protected Bitmap doInBackground(String... urls) {
			Bitmap bitmap = null;
			try {
				InputStream in = new java.net.URL(urls[0]).openStream();
				bitmap = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			mWeatherpicView.setImageBitmap(result);
		}
	}
}
