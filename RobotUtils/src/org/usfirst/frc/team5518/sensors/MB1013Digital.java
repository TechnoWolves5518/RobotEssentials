package org.usfirst.frc.team5518.sensors;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * Wrapper class for HRLV-MaxSonar-EZ
 * Ultrasonic Range finder for FRC applications
 * 
 * @author Hamzah Aslam
 *
 */
public class MB1013Digital extends Ultrasonic {
	
	/** Time (sec) for the ping trigger pulse. **/
	private static final double kPingTime = 25 * 1e-6;
	
	/** Minimum sensor range in mm */
	public static final int MIN_RANGE = 300;
	
	/** Maximum sensor range in mm */
	public static final int MAX_RANGE = 5000;
	
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

	/**
	 * Constructor for MB1013 Sensor
	 * 
	 * @param pingChannel The digital output channel that sends the pulse to initiate the sensor
     *                    sending the ping. Pin 4 (Trigger --- greater than 20us) on sensor.
     * @param echoChannel The digital input channel that receives the echo. The length of time that
     *                    the echo is high represents the round trip time of the ping, and the
     *                    distance. Pin 2 (Pulse Output --- 1us per mm) on sensor.
	 */
	public MB1013Digital(DigitalOutput pingChannel, DigitalInput echoChannel) {
		super(pingChannel, echoChannel);
		setAutomaticMode(true);
	}
	
	
	/**
	 * Constructor for MB1013 Sensor
	 * 
	 * @param pingChannel The digital output channel that sends the pulse to initiate the sensor
     *                    sending the ping. Pin 4 (Trigger --- greater than 20us) on sensor.
     * @param echoChannel The digital input channel that receives the echo. The length of time that
     *                    the echo is high represents the round trip time of the ping, and the
     *                    distance. Pin 2 (Pulse Output --- 1us per mm) on sensor.
	 */
	public MB1013Digital(int pingChannel, int echoChannel) {
		super(pingChannel, echoChannel);
		setAutomaticMode(true);
	}
	
}
