package com.maxproj.gesturebutton;

import java.util.LinkedList;

import com.maxproj.gesturebutton.GestureButtonLayout.OnOverLayerTouchDownListener;
import com.maxproj.gesturebutton.GestureButtonLayout.OnOverLayerTouchMoveListener;
import com.maxproj.gesturebutton.GestureButtonLayout.OnOverLayerTouchUpListener;

import android.os.Bundle;
import android.os.Handler;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GestureButtonTest extends Activity {

	GestureButtonLayout gestureLayer;
	LinearLayout appLayer;
	ActionBar actionBar;

	class MovePath {
		float x;
		float y;
	}

	LinkedList<MovePath> mpl = new LinkedList<MovePath>();
	int moveThreshold = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_button);

		actionBar = getActionBar();

		gestureLayer = (GestureButtonLayout) findViewById(R.id.gesture_button_layer);

		final LinkedList<ImageButton> ibl = new LinkedList<ImageButton>();
		ImageButton ib0 = (ImageButton) findViewById(R.id.qb0);
		ibl.add(ib0);
		ImageButton ib1 = (ImageButton) findViewById(R.id.qb1);
		ibl.add(ib1);
		ImageButton ib2 = (ImageButton) findViewById(R.id.qb2);
		ibl.add(ib2);
		ImageButton ib3 = (ImageButton) findViewById(R.id.qb3);
		ibl.add(ib3);
		ImageButton ib4 = (ImageButton) findViewById(R.id.qb4);
		ibl.add(ib4);
		ImageButton ib5 = (ImageButton) findViewById(R.id.qb5);
		ibl.add(ib5);

		gestureLayer
				.setOverLayerTouchDownListener(new OnOverLayerTouchDownListener() {

					@Override
					public void onOverLayerTouchDown() {
						// TODO Auto-generated method stub
						mpl.clear();
					}
				});
		gestureLayer
				.setOverLayerTouchUpListener(new OnOverLayerTouchUpListener() {

					@Override
					public void onOverLayerTouchUp() {
						// TODO Auto-generated method stub
						// TODO Auto-generated method stub
			            new Handler().postDelayed(new Runnable() {

			                @Override
			                public void run() {
								for (int i = 0; i < ibl.size(); i++) {
									ImageButton ib = ibl.get(i);
									if (ib.getVisibility() != View.INVISIBLE) {
										ib.setVisibility(View.INVISIBLE);
									}
								}
								mpl.clear();
			                }
			            }, 2000);
					}
				});
		gestureLayer
				.setOverLayerTouchMoveListener(new OnOverLayerTouchMoveListener() {

					@Override
					public void onOverLayerTouchMove(float x, float y) {
						
						/**
						 * 保存一下轨迹，或许扩展需要
						 */
						MovePath mp = new MovePath();
						mp.x = x;
						mp.y = y;
						mpl.add(mp);

						for (int i = 0; i < ibl.size(); i++) {
							if (mpl.size() == moveThreshold + (i * 5)) {
								ImageButton ib = ibl.get(i);
								if (ib.getVisibility() != View.VISIBLE) {
									LayoutParams params = new LayoutParams(
											LayoutParams.WRAP_CONTENT,
											LayoutParams.WRAP_CONTENT);
									params.leftMargin = Math.round(x);
									params.topMargin = Math.round(y);
									params.width = 80;
									params.height = 80;
									ib.setLayoutParams(params);
									ib.setAlpha(0f);
									ib.setVisibility(View.VISIBLE);
									ObjectAnimator anim = ObjectAnimator
											.ofFloat(ib, "alpha", 0f, 1f);
									anim.setDuration(500);
									anim.start();
								}
							}
						}

					}
				});

		// appLayer = (LinearLayout)findViewById(R.id.app_layer);

		// Button b = new Button(this);
		// b.setText("1");
		// gestureLayer.addQuickButton(b);
		// b.setText("2");
		// gestureLayer.addQuickButton(b);
		// b.setText("3");
		// gestureLayer.addQuickButton(b);
		// init();

		// funcTest();

	}

	private void funcTest() {

		ObjectAnimator anim = ObjectAnimator.ofFloat(appLayer, "alpha", 0f, 1f,
				0.5f, 1f);
		anim.setDuration(4000);
		anim.start();
	}

	private void funcTest2() {

		ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater
				.loadAnimator(this, R.animator.fade);
		objectAnimator.setTarget(appLayer);
		objectAnimator.start();
	}

	private void funcTest3() {

		TextView tv = new TextView(this);
		tv.setText("new");

		DisplayMetrics metrics = getResources().getDisplayMetrics();
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(100, 200, 0, 0);
		// params.setMargins(100, metrics.heightPixels - 20, 100, 0);
		tv.setLayoutParams(params);
		gestureLayer.addView(tv);

	}

	private void funcTest4() {

		gestureLayer.removeAllViews();

	}

	private void ActionBarB1() {
		funcTest3();
	}

	private void ActionBarB2() {
		funcTest4();
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
					return true;
				case (MotionEvent.ACTION_MOVE):
					MyLog.d(MyLog.DEBUG, "gestureLayer=Action was MOVE");
					return true;
				case (MotionEvent.ACTION_UP):
					MyLog.d(MyLog.DEBUG, "gestureLayer=Action was UP");
					return true;
				case (MotionEvent.ACTION_CANCEL):
					MyLog.d(MyLog.DEBUG, "gestureLayer=Action was CANCEL");
					return true;
				case (MotionEvent.ACTION_OUTSIDE):
					MyLog.d(MyLog.DEBUG,
							"gestureLayer=Movement occurred outside bounds "
									+ "of current screen element");
					return true;
				default:
					MyLog.d(MyLog.DEBUG, "gestureLayer=Action was unknown");
					return true;
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
					return true;
				case (MotionEvent.ACTION_MOVE):
					MyLog.d(MyLog.DEBUG, "appLayer=Action was MOVE");
					return true;
				case (MotionEvent.ACTION_UP):
					MyLog.d(MyLog.DEBUG, "appLayer=Action was UP");
					return true;
				case (MotionEvent.ACTION_CANCEL):
					MyLog.d(MyLog.DEBUG, "appLayer=Action was CANCEL");
					return true;
				case (MotionEvent.ACTION_OUTSIDE):
					MyLog.d(MyLog.DEBUG,
							"appLayer=Movement occurred outside bounds "
									+ "of current screen element");
					return true;
				default:
					MyLog.d(MyLog.DEBUG, "appLayer=Action was unknown");
					return true;
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
		case R.id.action_b2:
			MyLog.d(MyLog.DEBUG, "ActionBar b2!");
			ActionBarB2();
			return true;
		case R.id.action_settings:
			MyLog.d(MyLog.DEBUG, "ActionBar setting!");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
