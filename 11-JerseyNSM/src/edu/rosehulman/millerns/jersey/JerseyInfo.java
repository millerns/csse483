package edu.rosehulman.millerns.jersey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class JerseyInfo extends Activity implements OnClickListener {

	private Button mCancelButton;
	private Button mOkButton;
	private EditText mNameEdit;
	private EditText mNumberEdit;
	private ToggleButton mColorToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jersey_info);

		Intent intent = getIntent();
		String name = intent.getStringExtra(MainActivity.JERSEY_NAME_KEY);
		int number = intent.getIntExtra(MainActivity.JERSEY_NUMBER_KEY,
				MainActivity.DEFAULT_JERSEY_NUMBER);
		boolean jerseyIsRed = intent.getBooleanExtra(
				MainActivity.JERSEY_COLOR_KEY,
				MainActivity.DEFAULT_JERSEY_COLOR);

		mNameEdit = (EditText) findViewById(R.id.name_edit);
		mNameEdit.setText(name);
		mNumberEdit = (EditText) findViewById(R.id.number_edit);
		mNumberEdit.setText("" + number);
		mColorToggle = (ToggleButton) findViewById(R.id.jersey_color_toggle);
		mColorToggle.setChecked(jerseyIsRed);

		mCancelButton = (Button) findViewById(R.id.cancel_button);
		mCancelButton.setOnClickListener(this);
		mOkButton = (Button) findViewById(R.id.ok_button);
		mOkButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jersey_info, menu);
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
		if (v.getId() == mCancelButton.getId()) {
			Intent returnIntent = new Intent();
			setResult(RESULT_CANCELED, returnIntent);
			this.finish();
		} else if (v.getId() == mOkButton.getId()) {
			int number = MainActivity.DEFAULT_JERSEY_NUMBER;
			try {
				number = Integer.parseInt(mNumberEdit.getText().toString());
			} catch (NumberFormatException e) {
				Toast.makeText(this,
						"Number field must contain only an integer.",
						Toast.LENGTH_SHORT).show();
				return;
			}
			String name = mNameEdit.getText().toString();
			boolean jerseyIsRed = mColorToggle.isChecked();
			Intent returnIntent = new Intent();
			returnIntent.putExtra(MainActivity.JERSEY_NAME_KEY, name);
			returnIntent.putExtra(MainActivity.JERSEY_NUMBER_KEY, number);
			returnIntent.putExtra(MainActivity.JERSEY_COLOR_KEY, jerseyIsRed);
			setResult(RESULT_OK, returnIntent);
			this.finish();
		}

	}

}
