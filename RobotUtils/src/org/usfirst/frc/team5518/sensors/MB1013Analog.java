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
	
	/*
	 * Pin Out
	 * Pin 1 --- temp. sensor (optional)
	 * Pin 2 --- Pulse width with scale factor of 1us per mm
	 * Pin 3 --- Analog voltage output with scale factor of (VCC / 5120) per mm
	 * Pin 4 --- Hold high for ranging and low to stop. High for > 20 us to start ranging.
	 * Pin 5 --- Serial with RS232 format. Solder jumper on back for serial with TTL format.
	 * Pin 6 --- V+ or VCC operating on 2.5V-5.5V DC
	 * Pin 7 --- GND or ground for DC return
	 */
	
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
	public double getRangeInches() {
		return getRangeMM() * INCHES_IN_MM;
	}

}
