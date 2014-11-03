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
	public static GestureButtonShow gestureButton = null;
	
	public static final String pathRoot = Environment
			.getExternalStorageDirectory().getPath();
	public static final String time_format = "yyyy-MM-dd";
	
	/**
	 * 模式
	 */
	public static int MODE_BASIC = 0;
	public static int MODE_PATH = 1;
	public static int MODE_LINE = 2;
	public static int mode = MODE_LINE;
	
	/**
	 * 门限
	 */
	public static int moveThreshold = 5;// 稍微move一段之后出现按钮
	public static int MODE_BASIC_GAP = 5;//BASIC模式下按钮的MOVE间隔
	public static int HandlerMargin = 0;//留两个间隔的位置，防止手指挡住按钮
	
	
	public static class MovePath {
		float x;
		float y;
	}
}
