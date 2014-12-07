package edu.rosehulman.millerns.lightsout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private LightsOutGame mGame;
	private Button mNewGameButton;
	private Button[] mLightButtons;
	final static int NUMBER_OF_LIGHTS = 7;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mGame = new LightsOutGame(NUMBER_OF_LIGHTS);
		mNewGameButton = (Button) findViewById(R.id.new_game_button);
		mNewGameButton.setOnClickListener(this);
		
		mLightButtons = new Button[NUMBER_OF_LIGHTS];
		for (int light = 0; light < NUMBER_OF_LIGHTS; light++) {
			mLightButtons[light] = (Button) findViewById(getResources().getIdentifier("light_button"+light, "id", getPackageName()));				
			mLightButtons[light].setOnClickListener(this);		
		}
		updateView();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.new_game_button) {
			// reset game
			mGame = new LightsOutGame(NUMBER_OF_LIGHTS);
			Log.d("LO", "You pressed the new game button");
			for (int light = 0; light < NUMBER_OF_LIGHTS; light++) {
				mLightButtons[light].setEnabled(true);	
			}
		}
		for (int light = 0; light < NUMBER_OF_LIGHTS; light++) {
			if (v.getId() == mLightButtons[light].getId()) {
				// pressed this button
				mGame.pressedButtonAtIndex(light);
			}
		}
		updateView();
	}
	
	private void updateView() {
		// update game buttons
		for (int light = 0; light < NUMBER_OF_LIGHTS; light++) {
			if (mGame.getValueAtIndex(light) == LightsOutGame.LIGHT_OFF){
				mLightButtons[light].setText(R.string.light_off);	
			} else {
				mLightButtons[light].setText(R.string.light_on);
			}
		}
		// update game state text
		TextView gameStateTextView = (TextView) findViewById(R.id.instructions);
		if (mGame.checkForWin()) {
			gameStateTextView.setText(R.string.win);
			for (int light = 0; light < NUMBER_OF_LIGHTS; light++) {
				mLightButtons[light].setEnabled(false);	
			}
		} else {
			gameStateTextView.setText(R.string.instructions);
		}
	}
}
