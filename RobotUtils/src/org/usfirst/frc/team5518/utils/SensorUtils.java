package org.usfirst.frc.team5518.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * Utils for proximity sensor
 * 
 * @author Hamzah Aslam
 */
public class ProximityUtils implements Callable<List<Double>> {

	private Ultrasonic sensor;  // ultrasonic sensor
	private long time;  // total length of time to poll ultrasonic
	private long interval;  // interval between ultrasonic polling
	
	private ProximityUtils(Ultrasonic sensor, long time, long interval) {
		this.sensor = sensor;
		this.time = time;
		this.interval = interval;
	}
	
	/**
	 * Find the average distance from
	 * an ultrasonic/proxmity sensor.
	 * 
	 * @param sensor Ultrasonic object
	 * @return Average distance
	 */
	public static double average(Ultrasonic sensor) {
		return average(sensor, 1000, 10);
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
	public static double average(Ultrasonic sensor, long time, long interval) {
		ProximityUtils task = new ProximityUtils(sensor, time, interval);
		ExecutorService threadPool = Executors.newFixedThreadPool(1);
		Future<List<Double>> future = threadPool.submit(task);
		
		// default list to 0
		List<Double> dist = new ArrayList<>();
		dist.add(0.0);
		
		// get distance using a callback
		try {
			dist = future.get(time, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return average(convertArray(dist));
	}
	
	/**
	 * Find the average distance from
	 * an ultrasonic/proximity sensor.
	 * 
	 * @param distances Distances from sensor
	 * @return Average distance
	 */
	public static double average(double... distances) {
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
	
	/**
	 * Poll ultrasonic for values
	 * at an interval
	 */
	@Override
	public List<Double> call() throws Exception {
		int counter = 0;
		List<Double> dist = new ArrayList<>();
		
		// add sensor value if doesn't exceed time
		while(counter < (time / interval)) {
			try {
				dist.add(sensor.getRangeInches());
				counter += 1;
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return dist;
	}
}
