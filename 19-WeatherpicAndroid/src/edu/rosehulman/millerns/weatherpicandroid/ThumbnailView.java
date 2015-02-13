package edu.rosehulman.millerns.weatherpicandroid;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ThumbnailView extends LinearLayout {

	private TextView mCaptionView;
	private ImageView mWeatherpicView;

	public ThumbnailView(Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.thumbnail_item, this);
		mCaptionView = (TextView) findViewById(R.id.weatherpic_caption);
		mWeatherpicView = (ImageView) findViewById(R.id.weatherpic_thumbnail);
	}

	public void setCaption(String caption) {
		mCaptionView.setText(caption);
	}

	public void setImageUrl(String url) {
		new DownloadImageTask().execute(url);
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
