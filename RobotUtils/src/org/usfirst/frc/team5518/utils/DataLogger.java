package org.usfirst.frc.team5518.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Log values from the robot program to
 * a CSV file for easier access of analyzing
 * robot metrics
 * 
 * @author Hamzah Aslam
 * 
 * Adapted from Chris Gerth (FRC Team 1736) at following link:
 * https://github.com/RobotCasserole1736/RobotCasserole2016/blob/master/
 * RobotCasserole2016/src/org/usfirst/frc/team1736/robot/CsvLogger.java
 */
public class DataLogger {
	
	/** The path of the logfile directory */
	public static final String DIRECTORY = System.getProperty("user.home")
			+ File.separator + "csv";
	
	/** Whether or not the file is open to write to */
	private boolean isFileOpen = false;
	
	/** The writer to the CSV file */
	private BufferedWriter mWriter = null;
	
	/**
	 * 
	 * @param data_fields The header of the CSV file
	 * @param units_fields The header for the data units
	 */
	public DataLogger(String[] data_fields, String[] units_fields) {
		init(data_fields, units_fields);
	}
	
	/**
	 * Determines a unique file name, and opens a file in the data captures directory
	 * and writes the initial lines to it.
	 *        
	 * @param data_fields The header of the CSV file
	 * @param units_fields The header for the the data units
	 * @return Returns true on successful file write and false on failure.
	 */
	public boolean init(String[] data_fields, String[] units_fields ) {
		// Check whether writer is already open to write to
		if (isFileOpen) {
			System.out.println("Warning: CSV file is already open!");
			return false;
		}
		
		// Log in logfile and to console
		EventLogger.log("Initalizing CSV file...");
		
		try {
			// Get the path of the file
			String file = DIRECTORY + File.separator 
					+ getDateTimeString("yyyyMMdd_HHmmss") + ".csv";
			
			// Open output stream for writing to file
			FileWriter fstream = new FileWriter(file, true);
			mWriter = new BufferedWriter(fstream);
			isFileOpen = true;
			
			// Write specified headers to file
			for(String header : data_fields) {
				mWriter.write(header + ", ");
			}
			
			// Start writing to next line
			mWriter.write("\n");
			
			// Write specified units to file
			for (String header : units_fields){
				mWriter.write(header + ", ");
			}
			
			// Start writing to next line
			mWriter.write("\n");
		} catch (IOException e) {
			System.out.println("Error initalizing data file: " 
					+ e.getMessage());
			return false;
		}
		
		// Success in writing
		return true;
	}
	
	/**
	 * Writes data values to the CSV file, assuming it is open
	 * either with the constructor or the init method.
	 * 
	 * @param data_elements Values to record in the CSV file
	 * @return Returns true on successful file write and false on failure.
	 */	
	public boolean writeData(double... values){
		String line = "";
		
		// check if file is open to write to
		if (!isFileOpen) {
			System.out.println("Error: the data file is not yet opened, "
					+ "cannot write to it!");
			return false;
		}
			
		try {
			// Write specified data values to file
			for (double value : values) {
				line = line.concat(Double.toString(value) + ", ");
			}
			
			// Start writing on new line
			line = line.concat("\n");
			
			// Write line to file
			mWriter.write(line);
		} catch (IOException e) {
			EventLogger.log("Error writing to log file: " 
					+ e.getMessage());
			return false;
		}
		
		// Success in writing
		return true;
	}
	
	/**
	 * Force the BufferedWriter to sync all data
	 * to the CSV file
	 * 
	 * @return Whether or not the file has been synced.
	 */
	public boolean forceSync() {
		// check if file is open to write to
		if (!isFileOpen) {
			System.out.println("Error: the data file is not yet opened, "
					+ "cannot write to it!");
			return false;
		}
		
		// flush data from writer's buffer
		try {
			mWriter.flush();
		} catch (IOException e) {
			EventLogger.log("Error syncing data to file: "
					+ e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * Close writing to the CSV file
	 * to save system resources
	 * 
	 * @return Whether or not the operation was successful.
	 */
	public boolean close() {
		// check if file is open to write to
		if (!isFileOpen) {
			System.out.println("Error: data file is not yet opened, "
					+ "cannot write to it!");
			return false;
		}
		
		// try to close writer
		try {
			mWriter.close();
			isFileOpen = false;
		} catch (IOException e) {
			EventLogger.log("Error closing data file: "
					+ e.getMessage());
			return false;
		}
		
		return true;
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
