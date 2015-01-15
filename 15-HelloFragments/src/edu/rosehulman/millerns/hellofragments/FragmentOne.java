package edu.rosehulman.millerns.hellofragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentOne extends Fragment implements OnClickListener {

	private OnDataPassToTwoListener mListener;
	private int buttonCount;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_one, container, false);
		Button button = (Button) v.findViewById(R.id.button_one);
		button.setOnClickListener(this);
		buttonCount = 0;
		return v;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnDataPassToTwoListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnDataPassToTwoListener.");
		}
	}

	@Override
	public void onClick(View v) {
		mListener.passDataToTwo("" + ++buttonCount);
	}

	public void setColor(int rgb) {
		getActivity().findViewById(R.id.background).setBackgroundColor(rgb);
	}
}
