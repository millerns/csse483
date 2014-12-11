package edu.rosehulman.millerns.lightsoutmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;

public class ChangeNumButtonsActivity extends Activity implements
		OnClickListener {

	int[] mButtonLabels = new int[] { 3, 5, 7, 9 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_num_buttons);

		// Recover the intent used to launch this activity
		Intent intent = getIntent();
		int nButtons = intent.getIntExtra(MainActivity.KEY_NUM_BUTTONS, 7);
		
		// Check the proper button based on the intent
		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		radioGroup.check(getResources().getIdentifier("radio" + nButtons, "id",
				getPackageName()));

		for (int i = 0; i < mButtonLabels.length; i++) {
			int id = getResources().getIdentifier("radio" + mButtonLabels[i],
					"id", getPackageName());
			findViewById(id).setOnClickListener(this);
		}


	}

	@Override
	public void onClick(View v) {
		Intent returnIntent = new Intent();
		for (int i = 0; i < mButtonLabels.length; i++) {
			int id = getResources().getIdentifier("radio" + mButtonLabels[i],
					"id", getPackageName());
			if (v.getId() == id) {
				Log.d("LOM", "You clicked radio button " + mButtonLabels[i]);
				returnIntent.putExtra(MainActivity.KEY_NUM_BUTTONS, mButtonLabels[i]);
			}
		}
		setResult(RESULT_OK, returnIntent);
		this.finish();
	}
}
