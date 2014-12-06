package edu.rosehulman.millerns.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	private TicTacToeGame mGame;
	private Button mNewGameButton;
	private Button[][] mButtons;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mGame = new TicTacToeGame(this);
		mNewGameButton = (Button) findViewById(R.id.new_game_button);
		mNewGameButton.setOnClickListener(this);
		
		mButtons = new Button[TicTacToeGame.NUM_ROWS][TicTacToeGame.NUM_COLUMNS];
		//mButtons[0][0] = (Button) findViewById(R.id.button00);
		
		for (int row = 0; row < TicTacToeGame.NUM_ROWS; row++) {
			for (int col = 0; col < TicTacToeGame.NUM_COLUMNS; col++) {
				mButtons[row][col] = (Button) findViewById(getResources().getIdentifier("button"+row+col, "id", getPackageName()));				
				mButtons[row][col].setOnClickListener(this);				
			}
		}				
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.new_game_button) {
			// reset game
			mGame.resetGame();
			Log.d("TTT", "You pressed the new game button");
		}
		for (int row = 0; row < TicTacToeGame.NUM_ROWS; row++) {
			for (int col = 0; col < TicTacToeGame.NUM_COLUMNS; col++) {
				if (v.getId() == mButtons[row][col].getId()) {
					// pressed this button
					mGame.pressedButtonAtLocation(row, col);
				}
			}
		}
		updateView();
	}
	
	private void updateView() {
		// update game buttons
		for (int row = 0; row < TicTacToeGame.NUM_ROWS; row++) {
			for (int col = 0; col < TicTacToeGame.NUM_COLUMNS; col++) {
				mButtons[row][col].setText(mGame.stringForButtonAtLocation(row, col));
			}
		}
		// update game state text
		TextView gameStateTextView = (TextView) findViewById(R.id.game_state_text_view);
		gameStateTextView.setText(mGame.stringForGameState());
	}
}
