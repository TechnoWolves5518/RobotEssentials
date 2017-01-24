package org.usfirst.frc.team5518.utils;

import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * 
 * Class for useful methods
 * for robot programming
 * 
 * @author Hamzah Aslam
 *
 */
public class RobotUtils 
{	
	/**
	 * 
	 * @param sensor
	 * @return
	 */
	public static double avgDistance(Ultrasonic sensor) {
		return avgDistance(sensor, 1000);
	}
	
	/**
	 * 
	 * @param sensor
	 * @param interval
	 * @return
	 */
	public static double avgDistance(Ultrasonic sensor, long interval)
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while(true)
				{
					try {
						sensor.getRangeInches();
						Thread.sleep(interval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		return avgDistance(0);
	}
	
	/**
	 * 
	 * @param values
	 * @return
	 */
	public static double avgDistance(double... values)
	{
		int total = 0;
		
		// Find sum all of the values
		for (int i = 0; i < values.length; i++) {
			total += values[i];
		}
		
		return total / values.length;
	}
}
