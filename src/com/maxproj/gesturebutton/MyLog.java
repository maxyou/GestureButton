package com.maxproj.gesturebutton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


//import android.widget.Toast;


/**
 * http://stackoverflow.com/questions/2018263/android-logging
 * 
 * improved by Max You
 *
 */
public class MyLog {

	public static final String DEBUG = "debug";
	
	
	
	public static int LEVEL = android.util.Log.DEBUG;

	/**
	 *	switch:
	 *		outputSwitch = 0
	 *			no output
	 * 		outputSwitch = 1
	 * 			output by tagFilter
	 * 		outputSwitch = 2
	 * 			output all
	 */
	public static int outputSwitch = 2;	
	public static String tagFilter = "";
	public static int log2FileSwitch = 0;

	/**
	 * Log到文件
	 */
	public static final String logFileName = "GestureButton_log";
	public static File logFile = null;

	static public void log2File(String log) {

		logFile = new File(MyConfig.pathRoot,
				logFileName);

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(logFile,
					true));
			bw.write(log + "\n");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	static public void d(String tag, String msgFormat, Object... args) {
		
		if(outputSwitch == 0)
			return;
		
		if(outputSwitch == 1){
			if(!tagFilter.equals(tag)){
				return;
			}
		}
		
		if (LEVEL <= android.util.Log.DEBUG) {
			android.util.Log.d(tag, String.format(msgFormat, args));
			
			if(log2FileSwitch == 1){
				log2File(tag +": " + String.format(msgFormat, args));
			}			
		}
	}

//	static public void d(String tag, Throwable t, String msgFormat,
//			Object... args) {
//		
//		if(output != 1)
//			return;
//		
//		if (LEVEL <= android.util.Log.DEBUG) {
//			android.util.Log.d(tag, String.format(msgFormat, args), t);
//		}
//	}

}
