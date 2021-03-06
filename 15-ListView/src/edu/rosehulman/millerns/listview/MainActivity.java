package edu.rosehulman.millerns.listview;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button = (Button) findViewById(R.id.add_view_button);
		ListView listView = (ListView) findViewById(R.id.list_view);

		ArrayList<String> names = new ArrayList<String>();
		names.add("Nick");
		names.add("Min");
		names.add("Eric");
		names.add("Brandon");
		names.add("Alpha");
		names.add("Xiao");
		names.add("Joe");

		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, names);
		final RowNumberAdapter adapter = new RowNumberAdapter(this);
		listView.setAdapter(adapter);

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				adapter.addView();
				adapter.notifyDataSetChanged();
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(
						MainActivity.this,
						getString(R.string.toast_text)
								+ adapter.getItem(position), Toast.LENGTH_SHORT)
						.show();
			}
		});

	}

}
