package edu.rosehulman.millerns.hellopickers;

import java.util.Calendar;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

public class TimePickerFragment extends DialogFragment implements
		OnTimeSetListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);

		return new TimePickerDialog(getActivity(), this, hour, minute,
				DateFormat.is24HourFormat(getActivity()));
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		String period = "am";
		int hour = hourOfDay;
		if (hourOfDay > 11) {
			period = "pm";
			hour = hour - 12;
		}
		if (hour == 0) {
			hour = 12;
		}
		String timeString = String.format("You picked the time %d:%d %s.",
				hour, minute, period);
		Toast.makeText(getActivity(), timeString, Toast.LENGTH_SHORT).show();
	}

}
