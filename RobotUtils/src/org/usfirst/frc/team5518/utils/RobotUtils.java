package org.usfirst.frc.team5518.utils;

import java.util.ArrayList;
import java.util.List;

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
	 * Find the average distance from
	 * an ultrasonic/proxmity sensor.
	 * 
	 * @param sensor Ultrasonic object
	 * @return Average distance
	 */
	public static double avgDistance(Ultrasonic sensor) {
		return avgDistance(sensor, 1000, 10);
	}
	
	/**
	 * Find the average distance from
	 * an ultrasonic/proximity sensor.
	 * 
	 * @param sensor Ultrasonic object
	 * @param time Total time for polling in milliseconds
	 * @param interval Interval between polling in milliseconds
	 * @return Average distance
	 */
	public static double avgDistance(Ultrasonic sensor, long time, long interval) {
		List<Double> dist = new ArrayList<>();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						dist.add(sensor.getRangeInches());
						sensor.getRangeInches();
						Thread.sleep(time / interval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		return avgDistance(convertArray(dist));
	}
	
	/**
	 * Find the average distance from
	 * an ultrasonic/proximity sensor.
	 * 
	 * @param distances Distances from sensor
	 * @return Average distance
	 */
	public static double avgDistance(double... distances) {
		int total = 0;
		
		// Find sum all of the values
		for (int i = 0; i < distances.length; i++) {
			total += distances[i];
		}
		
		return total / distances.length;
	}
	
	/**
	 * Helper method to convert list of
	 * doubles to an array of doubles
	 * 
	 * @param values
	 * @return
	 */
	private static double[] convertArray(List<Double> values) {
		double[] array = new double[values.size()];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = values.get(i);
		}
		
		return array;
	}
}
