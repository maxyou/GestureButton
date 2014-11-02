package com.maxproj.gesturebutton;

import java.util.LinkedList;

import com.maxproj.gesturebutton.GestureButtonLayout.OnOverLayerTouchDownListener;
import com.maxproj.gesturebutton.GestureButtonLayout.OnOverLayerTouchMoveListener;
import com.maxproj.gesturebutton.GestureButtonLayout.OnOverLayerTouchUpListener;

import android.os.Bundle;
import android.os.Handler;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GestureButtonTest extends Activity {

	GestureButtonLayout gestureLayer;
	LinearLayout appLayer;
	ActionBar actionBar;

	class MovePath {
		float x;
		float y;
	}

	LinkedList<MovePath> mpl = new LinkedList<MovePath>();
	final LinkedList<ImageButton> ibl = new LinkedList<ImageButton>();
	int moveThreshold = 5;// 稍微move一段之后出现按钮
	static int buttonDelayAndAnimation = 0;

	class AnimatorListenerOfObj implements AnimatorListener {
		Object mObj;

		public AnimatorListenerOfObj(Object obj) {
			mObj = obj;
		}

		@Override
		public void onAnimationStart(Animator animation) {
		}

		@Override
		public void onAnimationEnd(Animator animation) {
			((View) mObj).setVisibility(View.INVISIBLE);
			if (buttonDelayAndAnimation > 0) {
				buttonDelayAndAnimation--;
			}
		}

		@Override
		public void onAnimationCancel(Animator animation) {
		}

		@Override
		public void onAnimationRepeat(Animator animation) {
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_button);

		{// 保存上下文
			MyConfig.app = getApplicationContext();
			MyConfig.gestureButton = this;
		}

		actionBar = getActionBar();

		ImageButton ib0 = (ImageButton) findViewById(R.id.qb0);
		ib0.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MyConfig.app, "button 0 is pressed",
						Toast.LENGTH_LONG).show();
			}
		});
		ibl.add(ib0);
		ImageButton ib1 = (ImageButton) findViewById(R.id.qb1);
		ib1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MyConfig.app, "button 1 is pressed",
						Toast.LENGTH_LONG).show();
			}
		});
		ibl.add(ib1);
		ImageButton ib2 = (ImageButton) findViewById(R.id.qb2);
		ib2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MyConfig.app, "button 2 is pressed",
						Toast.LENGTH_LONG).show();
			}
		});
		ibl.add(ib2);
		ImageButton ib3 = (ImageButton) findViewById(R.id.qb3);
		ib3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MyConfig.app, "button 3 is pressed",
						Toast.LENGTH_LONG).show();
			}
		});
		ibl.add(ib3);
		ImageButton ib4 = (ImageButton) findViewById(R.id.qb4);
		ib4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MyConfig.app, "button 4 is pressed",
						Toast.LENGTH_LONG).show();
			}
		});
		ibl.add(ib4);
		ImageButton ib5 = (ImageButton) findViewById(R.id.qb5);
		ib5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MyConfig.app, "button 5 is pressed",
						Toast.LENGTH_LONG).show();
			}
		});
		ibl.add(ib5);

		gestureLayer = (GestureButtonLayout) findViewById(R.id.gesture_button_layer);
		gestureLayer
				.setOverLayerTouchDownListener(new OnOverLayerTouchDownListener() {

					@Override
					public void onOverLayerTouchDown() {
						mpl.clear();
					}
				});
		gestureLayer
				.setOverLayerTouchUpListener(new OnOverLayerTouchUpListener() {

					@Override
					public void onOverLayerTouchUp() {
						MyLog.d(MyLog.DEBUG,
								"buttonDelayAndAnimation when UP: "
										+ buttonDelayAndAnimation);
						if (buttonDelayAndAnimation == 0) {
							/**
							 * 统计这次UP会添加几个buttonDelayAndAnimation
							 * 这是假定1秒钟后button的visibilty不变
							 * 在极端的情况下，第二次滑动极快，或许导致问题
							 * 但没必要考虑
							 */
							for (int i = 0; i < ibl.size(); i++) {								
								if (ibl.get(i).getVisibility() != View.INVISIBLE) {
									buttonDelayAndAnimation++;									
								}
							}

							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									for (int i = 0; i < ibl.size(); i++) {
										ImageButton ib = ibl.get(i);
										if (ib.getVisibility() != View.INVISIBLE) {
											ObjectAnimator anim = ObjectAnimator
													.ofFloat(ib, "alpha", 1f,
															0f);
											anim.setDuration(500);
											anim.addListener(new AnimatorListenerOfObj(
													ib));
											anim.start();
										}
									}
									mpl.clear();
								}
							}, 1000);
						}
					}
				});
		gestureLayer
				.setOverLayerTouchMoveListener(new OnOverLayerTouchMoveListener() {

					@Override
					public void onOverLayerTouchMove(float x, float y) {

						/**
						 * 保存一下轨迹，或许以后扩展需要
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
		case R.id.action_settings:
			MyLog.d(MyLog.DEBUG, "ActionBar setting!");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
