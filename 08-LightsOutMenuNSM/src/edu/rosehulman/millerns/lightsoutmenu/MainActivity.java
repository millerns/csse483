package edu.rosehulman.millerns.lightsoutmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	public static final String LOM = "LOM";
	public static final String KEY_NUM_BUTTONS = "KEY_NUM_BUTTONS";
	private int mNumButtons = 7;
	private static final int REQUEST_CODE_CHANGE_BUTTON = 1;

	private Button mPlayButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mPlayButton = (Button) findViewById(R.id.play_button);
		mPlayButton.setOnClickListener(this);
		Button changeNumButtonsButton = (Button) findViewById(R.id.change_num_buttons_button);
		changeNumButtonsButton.setOnClickListener(this);
		Button aboutButton = (Button) findViewById(R.id.about_button);
		aboutButton.setOnClickListener(this);
		Button exitButton = (Button) findViewById(R.id.exit_button);
		exitButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.play_button:
			Log.d("LOM", "Play Button");
			break;
		case R.id.change_num_buttons_button:
			Log.d("LOM", "Change number of buttons button");
			Intent changeNumButtonsIntent = new Intent(this,
					ChangeNumButtonsActivity.class);
			changeNumButtonsIntent.putExtra(KEY_NUM_BUTTONS, mNumButtons);
			startActivityForResult(changeNumButtonsIntent,
					REQUEST_CODE_CHANGE_BUTTON);
			break;
		case R.id.about_button:
			Log.d("LOM", "About button");
			Intent aboutIntent = new Intent(this, AboutActivity.class);
			startActivity(aboutIntent);
			break;
		case R.id.exit_button:
			Log.d("LOM", "Exit button");
			break;
		default:
			Log.d("LOM", "Unknown");
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE_CHANGE_BUTTON
				&& resultCode == RESULT_OK) {
			mNumButtons = data.getIntExtra(KEY_NUM_BUTTONS, 7);
			// update the play string with the proper number of buttons
			String s = getString(R.string.play_button_format, mNumButtons);
			mPlayButton.setText(s);
		}
	}

}
