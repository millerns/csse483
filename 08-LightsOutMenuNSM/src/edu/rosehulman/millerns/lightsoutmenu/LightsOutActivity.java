package edu.rosehulman.millerns.lightsoutmenu;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class LightsOutActivity extends Activity implements OnClickListener {

	private int mNumButtons;
	private LightsOutGame mGame;
	private ArrayList<Button> mButtons;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lights_out);

		Intent data = getIntent();
		mNumButtons = data.getIntExtra(MainActivity.KEY_NUM_BUTTONS, 7);
		Log.d(MainActivity.LOM, "Got " + mNumButtons + " buttons.");

		// Instance of the game
		this.mGame = new LightsOutGame(this.mNumButtons);

		// TableRow and array of buttons
		this.mButtons = new ArrayList<Button>();
		TableRow buttonRow = new TableRow(this);

		for (int i = 0; i < this.mNumButtons; i++) {
			Button button = new Button(this);
			button.setTag(Integer.valueOf(i));
			this.mButtons.add(button);
			buttonRow.addView(button);
			button.setOnClickListener(this);
		}
		TableLayout buttonTable = (TableLayout) findViewById(R.id.buttonTable);
		buttonTable.addView(buttonRow);

		Button newGame = (Button) findViewById(R.id.newGameButton);
		newGame.setOnClickListener(this);
		updateView();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.newGameButton) {
			Log.d(MainActivity.LOM, "New game pressed");
			this.mGame = new LightsOutGame(this.mNumButtons);
		} else {
			Log.d(MainActivity.LOM, "Button with tag " + v.getTag());
			this.mGame.pressedButtonAtIndex((Integer) (v.getTag()));
		}
		updateView();
	}

	private void updateView() {
		boolean win = mGame.checkForWin();
		for (int i = 0; i < mNumButtons; i++) {
			mButtons.get(i).setText("" + mGame.getValueAtIndex(i));
			mButtons.get(i).setEnabled(!win);
		}

		TextView textView = (TextView) findViewById(R.id.gameState);
		int nMoves = mGame.getNumPresses();
		String s;
		if (win) {
			if (nMoves == 1) {
				s = getString(R.string.you_won_one_move);
			} else {
				s = getString(R.string.you_won_format, nMoves);
			}
		} else {
			if (nMoves == 1) {
				s = getString(R.string.game_one_move);
			} else {
				s = getString(R.string.game_format, nMoves);
			}
		}
		textView.setText(s);
	}

}
