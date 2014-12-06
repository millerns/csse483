package edu.rosehulman.millerns.hellobutton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private int mCount = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final TextView messageText = (TextView) findViewById(R.id.message_text);
        final Button incrementButton = (Button) findViewById(R.id.increment_button);
        
        incrementButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// update text
				mCount++;
				String s = getString(R.string.message_format, mCount);
				messageText.setText(s);
			}        	
        });
        
    }
}
