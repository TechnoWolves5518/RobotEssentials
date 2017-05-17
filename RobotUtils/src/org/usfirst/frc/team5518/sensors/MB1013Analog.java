package org.usfirst.frc.team5518.sensors;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Wrapper class for HRLV-MaxSonar-EZ
 * Ultrasonic Range finder for FRC applications
 * 
 * @author Hamzah Aslam
 *
 */
public class MB1013Analog extends AnalogInput {
	
	/** Inches in mm */
	public static final double INCHES_IN_MM = 0.0393701;
	
	/** Voltage powering sensor on Pin 6 */
	public static final int MAX_VOLTAGE = 5;
	
	/** Number of bits in voltage to yield mm */
	public static final int RESOLUTION = 5120;
	
	/** Minimum sensor range in mm */
	public static final int MIN_RANGE = 300;
	
	/** Maximum sensor range in mm */
	public static final int MAX_RANGE = 5000;

	/**
	 * Constructor for MB1013 Sensor
	 * 
	 * @param channel The analog channel to represent pin 3 on sensor.
	 * 				  0-3 are on-board 4-7 are on the MXP port.
	 */
	public MB1013Analog(int channel) {
		super(channel);
	}
	
	/**
	 * Convert voltage into mm
	 * 
	 * @return The range in mm
	 */
	public double getRangeMM() {
		return getVoltage() * (RESOLUTION / MAX_VOLTAGE);
	}
	
	/**
	 * Get the sensor range in cm
	 * 
	 * @return The range in cm
	 */
	public double getRangeCM() {
		return getRangeMM() / 100;
	}
	
	/**
	 * Get the sensor range in inches
	 * 
	 * @return The range in inches
	 */
	public double getRangeIN() {
		return getRangeMM() * INCHES_IN_MM;
	}

}
