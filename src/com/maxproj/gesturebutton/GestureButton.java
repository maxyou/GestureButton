package com.maxproj.gesturebutton;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.MotionEventCompat;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class GestureButton extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_button);
		
		FrameLayout myView = (FrameLayout) findViewById(R.id.top_frame); 
		myView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
			    int action = MotionEventCompat.getActionMasked(event);
			    
			    MyLog.d(MyLog.DEBUG,"=An event!");
			    
			    switch(action) {
			        case (MotionEvent.ACTION_DOWN) :
			            MyLog.d(MyLog.DEBUG,"=Action was DOWN");
			            return false;
			        case (MotionEvent.ACTION_MOVE) :
			            MyLog.d(MyLog.DEBUG,"=Action was MOVE");
			            return false;
			        case (MotionEvent.ACTION_UP) :
			            MyLog.d(MyLog.DEBUG,"=Action was UP");
			            return false;
			        case (MotionEvent.ACTION_CANCEL) :
			            MyLog.d(MyLog.DEBUG,"=Action was CANCEL");
			            return false;
			        case (MotionEvent.ACTION_OUTSIDE) :
			            MyLog.d(MyLog.DEBUG,"=Movement occurred outside bounds " +
			                    "of current screen element");
			            return false;      
			        default : 
			        	MyLog.d(MyLog.DEBUG,"=Action was unknown");
			            return false;
			    }   
			}
		});


		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.l2_linearlayout); 
		linearLayout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
			    int action = MotionEventCompat.getActionMasked(event);
			    
			    MyLog.d(MyLog.DEBUG,"+An event!");
			    
			    switch(action) {
			        case (MotionEvent.ACTION_DOWN) :
			            MyLog.d(MyLog.DEBUG,"+Action was DOWN");
			            return false;
			        case (MotionEvent.ACTION_MOVE) :
			            MyLog.d(MyLog.DEBUG,"+Action was MOVE");
			            return false;
			        case (MotionEvent.ACTION_UP) :
			            MyLog.d(MyLog.DEBUG,"+Action was UP");
			            return false;
			        case (MotionEvent.ACTION_CANCEL) :
			            MyLog.d(MyLog.DEBUG,"+Action was CANCEL");
			            return false;
			        case (MotionEvent.ACTION_OUTSIDE) :
			            MyLog.d(MyLog.DEBUG,"+Movement occurred outside bounds " +
			                    "of current screen element");
			            return false;      
			        default : 
			        	MyLog.d(MyLog.DEBUG,"+Action was unknown");
			            return false;
			    }   
			}
		});

	}

	
	@Override
	public boolean onTouchEvent(MotionEvent event){ 
	        
	    int action = MotionEventCompat.getActionMasked(event);
	    
	    MyLog.d(MyLog.DEBUG,"An event!");
	    
	    switch(action) {
	        case (MotionEvent.ACTION_DOWN) :
	            MyLog.d(MyLog.DEBUG,"Action was DOWN");
	            return true;
	        case (MotionEvent.ACTION_MOVE) :
	            MyLog.d(MyLog.DEBUG,"Action was MOVE");
	            return true;
	        case (MotionEvent.ACTION_UP) :
	            MyLog.d(MyLog.DEBUG,"Action was UP");
	            return true;
	        case (MotionEvent.ACTION_CANCEL) :
	            MyLog.d(MyLog.DEBUG,"Action was CANCEL");
	            return true;
	        case (MotionEvent.ACTION_OUTSIDE) :
	            MyLog.d(MyLog.DEBUG,"Movement occurred outside bounds " +
	                    "of current screen element");
	            return true;      
	        default : 
	        	MyLog.d(MyLog.DEBUG,"Action was unknown");
	            return super.onTouchEvent(event);
	    }      
	}

	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gesture_button, menu);
		return true;
	}

}
