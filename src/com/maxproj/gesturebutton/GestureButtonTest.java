package com.maxproj.gesturebutton;

import android.os.Bundle;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.view.MotionEventCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class GestureButtonTest extends Activity {

	FrameLayout gestureLayer;
	LinearLayout appLayer;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_button);

		actionBar = getActionBar();

		gestureLayer = (FrameLayout)findViewById(R.id.gesture_button_layer);
		appLayer = (LinearLayout)findViewById(R.id.app_layer);
		
		init();
		
		//funcTest();

	}

	
	private void funcTest(){
		
        ObjectAnimator anim = ObjectAnimator.ofFloat(appLayer, "alpha", 0f, 1f, 0.5f, 1f);
        anim.setDuration(4000);
		anim.start();
	}
	
	private void funcTest2(){
		
		ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this,
			    R.animator.fade);
		objectAnimator.setTarget(appLayer);
		objectAnimator.start();
	}
	private void ActionBarB1(){
		//funcTest();
//		funcTest2();
		
		
	}
	
	private void init() {
		gestureLayer.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = MotionEventCompat.getActionMasked(event);

				MyLog.d(MyLog.DEBUG, "gestureLayer=An event!");

				switch (action) {
				case (MotionEvent.ACTION_DOWN):
					MyLog.d(MyLog.DEBUG, "gestureLayer=Action was DOWN");
					return false;
				case (MotionEvent.ACTION_MOVE):
					MyLog.d(MyLog.DEBUG, "gestureLayer=Action was MOVE");
					return false;
				case (MotionEvent.ACTION_UP):
					MyLog.d(MyLog.DEBUG, "gestureLayer=Action was UP");
					return false;
				case (MotionEvent.ACTION_CANCEL):
					MyLog.d(MyLog.DEBUG, "gestureLayer=Action was CANCEL");
					return false;
				case (MotionEvent.ACTION_OUTSIDE):
					MyLog.d(MyLog.DEBUG, "gestureLayer=Movement occurred outside bounds "
							+ "of current screen element");
					return false;
				default:
					MyLog.d(MyLog.DEBUG, "gestureLayer=Action was unknown");
					return false;
				}
			}
		});
	
		appLayer.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = MotionEventCompat.getActionMasked(event);

				MyLog.d(MyLog.DEBUG, "appLayer=An event!");

				switch (action) {
				case (MotionEvent.ACTION_DOWN):
					MyLog.d(MyLog.DEBUG, "appLayer=Action was DOWN");
					return false;
				case (MotionEvent.ACTION_MOVE):
					MyLog.d(MyLog.DEBUG, "appLayer=Action was MOVE");
					return false;
				case (MotionEvent.ACTION_UP):
					MyLog.d(MyLog.DEBUG, "appLayer=Action was UP");
					return false;
				case (MotionEvent.ACTION_CANCEL):
					MyLog.d(MyLog.DEBUG, "appLayer=Action was CANCEL");
					return false;
				case (MotionEvent.ACTION_OUTSIDE):
					MyLog.d(MyLog.DEBUG, "appLayer=Movement occurred outside bounds "
							+ "of current screen element");
					return false;
				default:
					MyLog.d(MyLog.DEBUG, "appLayer=Action was unknown");
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
			ActionBarB1();
			return true;
		case R.id.action_settings:
			MyLog.d(MyLog.DEBUG, "ActionBar setting!");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}


}
