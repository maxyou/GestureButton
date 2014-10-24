package com.maxproj.gesturebutton;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.view.MotionEventCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;

public class GestureButton extends Activity {

	FrameLayout frameView;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_button);

		actionBar = getActionBar();

		frameView = (FrameLayout) findViewById(R.id.top_frame);

		init();

	}

	private void init() {
		frameView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = MotionEventCompat.getActionMasked(event);

				MyLog.d(MyLog.DEBUG, "=An event!");

				switch (action) {
				case (MotionEvent.ACTION_DOWN):
					MyLog.d(MyLog.DEBUG, "=Action was DOWN");
					return false;
				case (MotionEvent.ACTION_MOVE):
					MyLog.d(MyLog.DEBUG, "=Action was MOVE");
					return false;
				case (MotionEvent.ACTION_UP):
					MyLog.d(MyLog.DEBUG, "=Action was UP");
					return false;
				case (MotionEvent.ACTION_CANCEL):
					MyLog.d(MyLog.DEBUG, "=Action was CANCEL");
					return false;
				case (MotionEvent.ACTION_OUTSIDE):
					MyLog.d(MyLog.DEBUG, "=Movement occurred outside bounds "
							+ "of current screen element");
					return false;
				default:
					MyLog.d(MyLog.DEBUG, "=Action was unknown");
					return false;
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gesture_button, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_b1:
			MyLog.d(MyLog.DEBUG, "ActionBar b1!");
			return true;
		case R.id.action_settings:
			MyLog.d(MyLog.DEBUG, "ActionBar setting!");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
