package edu.rosehulman.millerns.hellopickers;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button timePicker = (Button) findViewById(R.id.pick_time);
		timePicker.setOnClickListener(this);
		Button datePicker = (Button) findViewById(R.id.pick_date);
		datePicker.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.pick_time) {
			TimePickerFragment newFragment = new TimePickerFragment();
			newFragment.show(getFragmentManager(), "timePicker");
		} else if (id == R.id.pick_date) {
			DatePickerFragment newFragment = new DatePickerFragment();
			newFragment.show(getFragmentManager(), "datePicker");
		}
	}

}
