package edu.rosehulman.millerns.exam1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button mGreenButton;
	private Button mRedButton;
	private Button mYellowButton;
	private Button mBlueButton;
	private Button mResetButton;
	private TextView mInstructionsView;

	private String mMemorySequence;
	private int mSequenceCount;

	private static final int NORMAL_FONT_SIZE = 18;
	private static final int SMALL_FONT_SIZE = 14;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mGreenButton = (Button) findViewById(R.id.green_button);
		mGreenButton.setOnClickListener(this);
		mRedButton = (Button) findViewById(R.id.red_button);
		mRedButton.setOnClickListener(this);
		mYellowButton = (Button) findViewById(R.id.yellow_button);
		mYellowButton.setOnClickListener(this);
		mBlueButton = (Button) findViewById(R.id.blue_button);
		mBlueButton.setOnClickListener(this);
		mResetButton = (Button) findViewById(R.id.reset_button);
		mResetButton.setOnClickListener(this);

		mInstructionsView = (TextView) findViewById(R.id.instruction_view);

		mMemorySequence = "";
		mSequenceCount = 0;

		updateDisplay();
	}

	@Override
	public void onClick(View v) {
		// Toast.makeText(this, "You clicked a button!", Toast.LENGTH_SHORT)
		// .show();
		if (v.getId() == mGreenButton.getId()) {
			addSequence("G");
		} else if (v.getId() == mRedButton.getId()) {
			addSequence("R");
		} else if (v.getId() == mYellowButton.getId()) {
			addSequence("Y");
		} else if (v.getId() == mBlueButton.getId()) {
			addSequence("B");
		} else if (v.getId() == mResetButton.getId()) {
			resetSequence();
		}
	}

	private void addSequence(String c) {
		if (mMemorySequence.equals("")) {
			mMemorySequence = c;
		} else {
			mMemorySequence += " " + c;
		}
		mSequenceCount++;
		updateDisplay();
	}

	private void resetSequence() {
		mMemorySequence = "";
		mSequenceCount = 0;
		updateDisplay();
	}

	private void updateDisplay() {
		if (mMemorySequence.equals("")) {
			mInstructionsView.setText(R.string.record);
			mInstructionsView.setTextSize(NORMAL_FONT_SIZE);
		} else {
			String instructions = getString(R.string.pressed) + " "
					+ mMemorySequence;
			mInstructionsView.setText(instructions);
			if (mSequenceCount == 11) {
				mInstructionsView.setTextSize(SMALL_FONT_SIZE);
			}
		}
	}
}
