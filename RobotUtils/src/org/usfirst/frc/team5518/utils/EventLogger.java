package org.usfirst.frc.team5518.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Log values and events from the robot program
 * to the console and a logfile 
 * 
 * @author Hamzah Aslam
 */
public class EventLogger {
	
	/** The path of the logfile directory */
	public static final String DIRECTORY = System.getProperty("user.home")
			+ File.separator + "logs";
	
	/**
	 * Log number values to the logfile and
	 * the console
	 * 
	 * @param tag The short description of the value
	 * @param values The decimal values to log
	 */
	public static void log(String tag, double... values) {
		for (double value : values) {
			log(tag + ": " + String.valueOf(value) + "\n");
		}
	}
	
	/**
	 * Log message to file and console
	 * 
	 * @param msg The message to write
	 */
	public static void log(String msg) {
		// add time to message
		final String message = getDateTimeString("MM/dd/yyyy HH:mm:ss") 
				+ " " + msg + "\n";
		
		// save to file in new thread
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// create the file
				File file = new File(DIRECTORY + File.separator
						+ getDateTimeString("yyyyMMdd") + ".txt");
				
				// create directory if not created
				file.mkdirs();
				
				// save to file
				try (FileWriter fw = new FileWriter(file, true);
						BufferedWriter out = new BufferedWriter(fw)) {
					out.write(message);
				} catch (Exception e) {
					System.out.println("Could not save log to file!");
				}
			}
		});
		thread.start();
		
		// print out to console
		System.out.println(message);
	}
	
	/**
	 * Get a string formatted with the 
	 * current date and time
	 * 
	 * @param format The format of the date and time
	 * @return The current date and time in the specified format
	 */
	private static String getDateTimeString(String format) {
		DateFormat df = new SimpleDateFormat(format);
		df.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
		
		return df.format(new Date());
	}

}
