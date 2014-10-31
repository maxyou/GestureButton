package com.maxproj.gesturebutton;

import java.util.LinkedList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class GestureButtonLayout extends FrameLayout {
	Context mContext;
	LinkedList<Button> qbl = new LinkedList<Button>();

	int indexMax = 6;
	int mMoveCount = 0;

	
    public interface OnImageButtonChangeListener {
        void onImageButtonChange(boolean b);
    }
	
    OnImageButtonChangeListener mListener;
    
	public void setImageButtonChangeListener(OnImageButtonChangeListener l){
		mListener = l;
	}
	
	private void quickButtonInit() {
		MyLog.d(MyLog.DEBUG, "quickButtonInit()");
	}

	public void addQuickButton(Button b) {
		MyLog.d(MyLog.DEBUG, "addQuickButton !");
		if (qbl.size() >= indexMax) {
			MyLog.d(MyLog.DEBUG, "size >= indexMax !!");
			return;
		}
		b.setVisibility(View.GONE);
//		this.addView(b,qbl.size());
		qbl.add(b);
	}

	private void quickButtonShow() {
		MyLog.d(MyLog.DEBUG, "quickButtonShow()");
		for (int i = 0; i < qbl.size(); i++) {
			Button b = qbl.get(i);
			if (b != null) {
				LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				params.leftMargin = 100;
				params.topMargin = 200 + 100*i;
				b.setLayoutParams(params);
				b.setVisibility(View.VISIBLE);
			}
		}
		// invalidate();
	}

	private void quickButtonHide() {
		MyLog.d(MyLog.DEBUG, "quickButtonHide()");
		for (int i = 0; i < qbl.size(); i++) {
			if (qbl.get(i) != null) {
				qbl.get(i).setVisibility(View.GONE);
			}
		}
		// invalidate();
	}

	/**
	 * 这里需要保存轨迹 并且判断轨迹是否足够长 足够长则截断event的传播
	 */

	// public void addQuickButtonListener(int index, View.OnClickListener
	// listener) {
	// if ((index < 0) || (index >= indexMax)) {
	// MyLog.d(MyLog.DEBUG, "(index < 0) || (index >= indexMax)");
	// return;
	// }
	// }

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		MyLog.d(MyLog.DEBUG, "dispatchTouchEvent()! 1");
		if (isEnabled()) {

			/**
			 * 注意这段处理逻辑 首先本层判断是否手势，如果是手势，设置ACTION_CANCEL，也即不再传播
			 */

			processEvent(event);

			super.dispatchTouchEvent(event);
			MyLog.d(MyLog.DEBUG, "dispatchTouchEvent()! 2");
			return true;
		}

		return super.dispatchTouchEvent(event);
	}

	private boolean processEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touchDown(event);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			touchMove(event);
			break;
		case MotionEvent.ACTION_UP:
			touchUp(event);
			break;
		case MotionEvent.ACTION_CANCEL:
			break;
		}

		return false;
	}

	private void touchDown(MotionEvent event) {
		mMoveCount = 0;

		float x = event.getX();
		float y = event.getY();

	}

	private void touchMove(MotionEvent event) {
		mMoveCount++;
		MyLog.d(MyLog.DEBUG, "mMoveCount: " + mMoveCount);

		if (mMoveCount > 60) {
//			quickButtonShow();
			mListener.onImageButtonChange(true);
		}
		final float x = event.getX();
		final float y = event.getY();

	}

	private void touchUp(MotionEvent event) {
		mMoveCount = 0;
//		quickButtonHide();
		mListener.onImageButtonChange(false);
	}

	public GestureButtonLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		MyLog.d(MyLog.DEBUG, "GestureButtonLayout(3)");
		mContext = context;
		quickButtonInit();
	}

	public GestureButtonLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		MyLog.d(MyLog.DEBUG, "GestureButtonLayout(2)");
		mContext = context;
		quickButtonInit();
	}

	public GestureButtonLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		MyLog.d(MyLog.DEBUG, "GestureButtonLayout(1)");
		mContext = context;
		quickButtonInit();
	}
}
