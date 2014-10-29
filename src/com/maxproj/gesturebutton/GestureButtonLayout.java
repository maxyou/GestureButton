package com.maxproj.gesturebutton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;


public class GestureButtonLayout extends FrameLayout {

	public GestureButtonLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public GestureButtonLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}


    public GestureButtonLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
    public boolean dispatchTouchEvent(MotionEvent event) {
    	MyLog.d(MyLog.DEBUG, "dispatchTouchEvent()!");
        if (isEnabled()) {
//            final boolean cancelDispatch = (mIsGesturing || (mCurrentGesture != null &&
//                    mCurrentGesture.getStrokesCount() > 0 && mPreviousWasGesturing)) &&
//                    mInterceptEvents;
//
//            processEvent(event);
//
//            if (cancelDispatch) {
//                event.setAction(MotionEvent.ACTION_CANCEL);
//            }

            super.dispatchTouchEvent(event);

            return true;
        }

        return super.dispatchTouchEvent(event);
    }
//
//    private boolean processEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                touchDown(event);
//                invalidate();
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                if (mIsListeningForGestures) {
//                    Rect rect = touchMove(event);
//                    if (rect != null) {
//                        invalidate(rect);
//                    }
//                    return true;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                if (mIsListeningForGestures) {
//                    touchUp(event, false);
//                    invalidate();
//                    return true;
//                }
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                if (mIsListeningForGestures) {
//                    touchUp(event, true);
//                    invalidate();
//                    return true;
//                }
//        }
//
//        return false;
//    }
//
//    private void touchDown(MotionEvent event) {
//        mIsListeningForGestures = true;
//
//        float x = event.getX();
//        float y = event.getY();
//
//        mX = x;
//        mY = y;
//
//        mTotalLength = 0;
//        mIsGesturing = false;
//
//        if (mGestureStrokeType == GESTURE_STROKE_TYPE_SINGLE || mResetGesture) {
//            if (mHandleGestureActions) setCurrentColor(mUncertainGestureColor);
//            mResetGesture = false;
//            mCurrentGesture = null;
//            mPath.rewind();
//        } else if (mCurrentGesture == null || mCurrentGesture.getStrokesCount() == 0) {
//            if (mHandleGestureActions) setCurrentColor(mUncertainGestureColor);
//        }
//
//        // if there is fading out going on, stop it.
//        if (mFadingHasStarted) {
//            cancelClearAnimation();
//        } else if (mIsFadingOut) {
//            setPaintAlpha(255);
//            mIsFadingOut = false;
//            mFadingHasStarted = false;
//            removeCallbacks(mFadingOut);
//        }
//
//        if (mCurrentGesture == null) {
//            mCurrentGesture = new Gesture();
//        }
//
//        mStrokeBuffer.add(new GesturePoint(x, y, event.getEventTime()));
//        mPath.moveTo(x, y);
//
//        final int border = mInvalidateExtraBorder;
//        mInvalidRect.set((int) x - border, (int) y - border, (int) x + border, (int) y + border);
//
//        mCurveEndX = x;
//        mCurveEndY = y;
//
//        // pass the event to handlers
//        final ArrayList<OnGestureListener> listeners = mOnGestureListeners;
//        final int count = listeners.size();
//        for (int i = 0; i < count; i++) {
//            listeners.get(i).onGestureStarted(this, event);
//        }
//    }
//
//    private Rect touchMove(MotionEvent event) {
//        Rect areaToRefresh = null;
//
//        final float x = event.getX();
//        final float y = event.getY();
//
//        final float previousX = mX;
//        final float previousY = mY;
//
//        final float dx = Math.abs(x - previousX);
//        final float dy = Math.abs(y - previousY);
//
//        if (dx >= GestureStroke.TOUCH_TOLERANCE || dy >= GestureStroke.TOUCH_TOLERANCE) {
//            areaToRefresh = mInvalidRect;
//
//            // start with the curve end
//            final int border = mInvalidateExtraBorder;
//            areaToRefresh.set((int) mCurveEndX - border, (int) mCurveEndY - border,
//                    (int) mCurveEndX + border, (int) mCurveEndY + border);
//
//            float cX = mCurveEndX = (x + previousX) / 2;
//            float cY = mCurveEndY = (y + previousY) / 2;
//
//            mPath.quadTo(previousX, previousY, cX, cY);
//
//            // union with the control point of the new curve
//            areaToRefresh.union((int) previousX - border, (int) previousY - border,
//                    (int) previousX + border, (int) previousY + border);
//
//            // union with the end point of the new curve
//            areaToRefresh.union((int) cX - border, (int) cY - border,
//                    (int) cX + border, (int) cY + border);
//
//            mX = x;
//            mY = y;
//
//            mStrokeBuffer.add(new GesturePoint(x, y, event.getEventTime()));
//
//            if (mHandleGestureActions && !mIsGesturing) {
//                mTotalLength += (float) Math.hypot(dx, dy);
//
//                if (mTotalLength > mGestureStrokeLengthThreshold) {
//                    final OrientedBoundingBox box =
//                            GestureUtils.computeOrientedBoundingBox(mStrokeBuffer);
//
//                    float angle = Math.abs(box.orientation);
//                    if (angle > 90) {
//                        angle = 180 - angle;
//                    }
//
//                    if (box.squareness > mGestureStrokeSquarenessTreshold ||
//                            (mOrientation == ORIENTATION_VERTICAL ?
//                                    angle < mGestureStrokeAngleThreshold :
//                                    angle > mGestureStrokeAngleThreshold)) {
//
//                        mIsGesturing = true;
//                        setCurrentColor(mCertainGestureColor);
//
//                        final ArrayList<OnGesturingListener> listeners = mOnGesturingListeners;
//                        int count = listeners.size();
//                        for (int i = 0; i < count; i++) {
//                            listeners.get(i).onGesturingStarted(this);
//                        }
//                    }
//                }
//            }
//
//            // pass the event to handlers
//            final ArrayList<OnGestureListener> listeners = mOnGestureListeners;
//            final int count = listeners.size();
//            for (int i = 0; i < count; i++) {
//                listeners.get(i).onGesture(this, event);
//            }
//        }
//
//        return areaToRefresh;
//    }
//
//    private void touchUp(MotionEvent event, boolean cancel) {
//        mIsListeningForGestures = false;
//
//        // A gesture wasn't started or was cancelled
//        if (mCurrentGesture != null) {
//            // add the stroke to the current gesture
//            mCurrentGesture.addStroke(new GestureStroke(mStrokeBuffer));
//
//            if (!cancel) {
//                // pass the event to handlers
//                final ArrayList<OnGestureListener> listeners = mOnGestureListeners;
//                int count = listeners.size();
//                for (int i = 0; i < count; i++) {
//                    listeners.get(i).onGestureEnded(this, event);
//                }
//
//                clear(mHandleGestureActions && mFadeEnabled, mHandleGestureActions && mIsGesturing,
//                        false);
//            } else {
//                cancelGesture(event);
//
//            }
//        } else {
//            cancelGesture(event);
//        }
//
//        mStrokeBuffer.clear();
//        mPreviousWasGesturing = mIsGesturing;
//        mIsGesturing = false;
//
//        final ArrayList<OnGesturingListener> listeners = mOnGesturingListeners;
//        int count = listeners.size();
//        for (int i = 0; i < count; i++) {
//            listeners.get(i).onGesturingEnded(this);
//        }
//    }

}
