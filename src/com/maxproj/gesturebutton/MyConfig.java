package com.maxproj.gesturebutton;

import android.content.Context;
import android.os.Environment;

public class MyConfig {

	private MyConfig() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 系统相关
	 */
	public static Context app = null;
	public static GestureButton gestureButton = null;
	
	public static final String pathRoot = Environment
			.getExternalStorageDirectory().getPath();
	public static final String time_format = "yyyy-MM-dd";
	


	
}
