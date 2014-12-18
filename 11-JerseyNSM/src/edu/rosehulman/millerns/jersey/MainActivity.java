package edu.rosehulman.millerns.jersey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private String mJerseyName;
	private int mJerseyNumber;
	private boolean mJerseyIsRed;

	private TextView mJerseyNameView;
	private TextView mJerseyNumberView;
	private ImageView mJerseyImageView;

	public static final String DEFAULT_JERSEY_NAME = "FISHER";
	public static final int DEFAULT_JERSEY_NUMBER = 42;
	public static final boolean DEFAULT_JERSEY_COLOR = true;

	public static final String JERSEY_NAME_KEY = "JERSEY_NAME_KEY";
	public static final String JERSEY_NUMBER_KEY = "JERSEY_NUMBER_KEY";
	public static final String JERSEY_COLOR_KEY = "JERSEY_COLOR_KEY";

	public static final int REQUEST_CODE_EDIT_JERSEY = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button editButton = (Button) findViewById(R.id.edit_button);
		editButton.setOnClickListener(this);

		mJerseyNameView = (TextView) findViewById(R.id.jersey_name);
		mJerseyNumberView = (TextView) findViewById(R.id.jersey_number);
		mJerseyImageView = (ImageView) findViewById(R.id.jersey);

		mJerseyName = DEFAULT_JERSEY_NAME;
		mJerseyNumber = DEFAULT_JERSEY_NUMBER;
		mJerseyIsRed = DEFAULT_JERSEY_COLOR;

		updateJersey();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	@Override
	public void onClick(View v) {
		Intent editIntent = new Intent(this, JerseyInfo.class);
		editIntent.putExtra(JERSEY_NAME_KEY, mJerseyName);
		editIntent.putExtra(JERSEY_NUMBER_KEY, mJerseyNumber);
		editIntent.putExtra(JERSEY_COLOR_KEY, mJerseyIsRed);
		startActivityForResult(editIntent, REQUEST_CODE_EDIT_JERSEY);
	}

	private void updateJersey() {
		mJerseyNameView.setText(mJerseyName);
		mJerseyNumberView.setText("" + mJerseyNumber);
		if (mJerseyIsRed) {
			mJerseyImageView.setImageResource(R.drawable.red_jersey);
		} else {
			mJerseyImageView.setImageResource(R.drawable.blue_jersey);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE_EDIT_JERSEY) {
			if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "Edit cancelled.", Toast.LENGTH_SHORT)
						.show();
			} else if (resultCode == RESULT_OK) {
				mJerseyName = data.getStringExtra(JERSEY_NAME_KEY);
				mJerseyNumber = data.getIntExtra(JERSEY_NUMBER_KEY,
						DEFAULT_JERSEY_NUMBER);
				mJerseyIsRed = data.getBooleanExtra(JERSEY_COLOR_KEY,
						DEFAULT_JERSEY_COLOR);
				updateJersey();
			}
		}
	}

}
