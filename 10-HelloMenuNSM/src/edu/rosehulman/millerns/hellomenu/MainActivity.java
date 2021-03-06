package edu.rosehulman.millerns.hellomenu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static final String KEY_COLOR = "KEY_COLOR";
	static final String HM = "HM";
	private TextView mHelloTextView;

	private ActionMode mActionMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mHelloTextView = (TextView) findViewById(R.id.helloTextView);

		mHelloTextView.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				if (mActionMode != null) {
					return false;
				}
				MyActionModeCallback callback = new MyActionModeCallback();
				mActionMode = startActionMode(callback);
				mActionMode.setTitle(R.string.menu_context_title);
				return true;
			}
		});
	}

	class MyActionModeCallback implements ActionMode.Callback {

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			float size = mHelloTextView.getTextSize();
			switch (item.getItemId()) {
			case R.id.increase:
				size += 12;
				mHelloTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
				return true;
			case R.id.decrease:
				size -= 12;
				mHelloTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
				return true;
			}

			return false;
		}

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			mode.getMenuInflater().inflate(R.menu.context, menu);
			return true;
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			// TODO Auto-generated method stub
			mActionMode = null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} else if (id == R.id.red || id == R.id.green || id == R.id.blue) {
			Log.d(HM, "Color Clicked. Launching an activity.");
			Intent colorIntent = new Intent(this, ColorActivity.class);
			int color = Color.BLACK;
			if (id == R.id.red) {
				color = Color.RED;
			} else if (id == R.id.green) {
				color = Color.GREEN;
			} else {
				color = Color.BLUE;
			}
			colorIntent.putExtra(KEY_COLOR, color);
			startActivity(colorIntent);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
