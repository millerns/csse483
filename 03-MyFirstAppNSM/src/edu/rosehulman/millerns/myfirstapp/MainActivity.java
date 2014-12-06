package edu.rosehulman.millerns.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final EditText editText = (EditText) findViewById(R.id.edit_message);

		final Button sendButton = (Button) findViewById(R.id.send_button);
		sendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (editText.getText().toString()
						.equals(getString(R.string.secret))) {
					sendButton.setText(R.string.button_wow);
				} else {
					sendButton.setText(R.string.button_send);
				}
			}
		});
	}
}
