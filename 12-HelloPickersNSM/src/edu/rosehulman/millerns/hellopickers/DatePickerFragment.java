package edu.rosehulman.millerns.hellopickers;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

public class DatePickerFragment extends DialogFragment implements
		OnDateSetListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {
		String monthString = "";
		switch (month) {
		case Calendar.JANUARY:
			monthString = "January";
			break;
		case Calendar.FEBRUARY:
			monthString = "February";
			break;
		case Calendar.MARCH:
			monthString = "March";
			break;
		case Calendar.APRIL:
			monthString = "April";
			break;
		case Calendar.MAY:
			monthString = "May";
			break;
		case Calendar.JUNE:
			monthString = "June";
			break;
		case Calendar.JULY:
			monthString = "July";
			break;
		case Calendar.AUGUST:
			monthString = "August";
			break;
		case Calendar.SEPTEMBER:
			monthString = "September";
			break;
		case Calendar.OCTOBER:
			monthString = "October";
			break;
		case Calendar.NOVEMBER:
			monthString = "November";
			break;
		case Calendar.DECEMBER:
			monthString = "December";
			break;
		case Calendar.UNDECIMBER:
			monthString = "Undecimber";
			break;
		}
		String dateString = String.format("You picked the date %s %d, %d.",
				monthString, day, year);
		Toast.makeText(getActivity(), dateString, Toast.LENGTH_SHORT).show();
	}

}
