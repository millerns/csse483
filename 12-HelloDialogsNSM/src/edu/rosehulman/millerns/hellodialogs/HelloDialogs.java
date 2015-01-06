package edu.rosehulman.millerns.hellodialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import edu.rosehulman.hellodialogs.R;

public class HelloDialogs extends Activity {

	public static final String HD = "HD";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		((Button) findViewById(R.id.dialog_button_ok))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Log.d(HD,
								"Show alert dialog with one button to info user.");
						showAbout();
					}
				});
		((Button) findViewById(R.id.dialog_button_multiple_buttons))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Log.d(HD,
								"Show alert dialog with multiple options for the user.");
						showExit();
					}
				});
		((Button) findViewById(R.id.dialog_button_select_item))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Log.d(HD,
								"Show alert dialog with a list of options for the user.");
						showLearningSurvey();
					}
				});
		((Button) findViewById(R.id.dialog_button_custom))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Log.d(HD, "Show custom dialog subclass to the user.");
						showRoseDialog();
					}
				});
	}

	protected void showAbout() {
		DialogFragment df = new DialogFragment() {
			@Override
			public Dialog onCreateDialog(Bundle savedInstanceState) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				// set options
				builder.setTitle(R.string.about_dialogs);
				builder.setIcon(R.drawable.ic_dialog_alert);
				builder.setMessage(R.string.about_description);
				builder.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				return builder.create();
			}
		};
		df.show(getFragmentManager(), "about");
	}

	private void showExit() {
		DialogFragment df = new DialogFragment() {
			@Override
			public Dialog onCreateDialog(Bundle savedInstanceState) {
				AlertDialog.Builder exitBuilder = new AlertDialog.Builder(
						getActivity());
				exitBuilder.setTitle(R.string.toast_or_exit_question);
				exitBuilder.setIcon(R.drawable.ic_dialog_alert);
				exitBuilder.setNegativeButton(android.R.string.cancel, null);
				exitBuilder.setNeutralButton(R.string.toast,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Toast.makeText(getActivity(), R.string.toast,
										Toast.LENGTH_LONG).show();
								dialog.dismiss();
							}
						});
				exitBuilder.setPositiveButton(R.string.exit,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}
						});

				return exitBuilder.create();
			}
		};
		df.show(getFragmentManager(), "exit");
	}

	protected void showLearningSurvey() {
		DialogFragment df = new DialogFragment() {
			@Override
			public Dialog onCreateDialog(Bundle savedInstanceState) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				builder.setTitle(R.string.toast_or_exit_question);
				builder.setIcon(R.drawable.ic_dialog_alert);
				builder.setNegativeButton(android.R.string.cancel, null);
				return builder.create();
			}
		};
		df.show(getFragmentManager(), "exit");
	}

	protected void showRoseDialog() {
		// TODO: Show appropriate dialog
	}
}