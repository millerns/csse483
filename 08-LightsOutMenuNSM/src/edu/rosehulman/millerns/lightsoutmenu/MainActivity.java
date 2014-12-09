package edu.rosehulman.millerns.lightsoutmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button playButton = (Button) findViewById(R.id.play_button);
		playButton.setOnClickListener(this);
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
}
