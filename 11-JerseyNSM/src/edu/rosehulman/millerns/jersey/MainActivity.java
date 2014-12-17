package edu.rosehulman.millerns.jersey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private String mJerseyName;
	private int mJerseyNumber;

	private TextView mJerseyNameView;
	private TextView mJerseyNumberView;

	public static final String DEFAULT_JERSEY_NAME = "FISHER";
	public static final int DEFAULT_JERSEY_NUMBER = 42;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button editButton = (Button) findViewById(R.id.edit_button);
		editButton.setOnClickListener(this);

		mJerseyNameView = (TextView) findViewById(R.id.jersey_name);
		mJerseyNumberView = (TextView) findViewById(R.id.jersey_number);

		mJerseyName = DEFAULT_JERSEY_NAME;
		mJerseyNumber = DEFAULT_JERSEY_NUMBER;

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
		startActivity(editIntent);
	}

	private void updateJersey() {
		mJerseyNameView.setText(mJerseyName);
		mJerseyNumberView.setText("" + mJerseyNumber);
		// mJerseyNumberView.setText(mJerseyNumber);
	}

}
