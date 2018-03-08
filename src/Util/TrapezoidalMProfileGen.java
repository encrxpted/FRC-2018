package Util;

import java.util.ArrayList;
import java.util.List;

public class TrapezoidalMProfileGen {
	
	/** cruiseVelocity- units per 100ms
 	 * acceleration- units per 100ms per sec
	 * distance- distance to travel in encoder units*/
	private static List<Double> speedList = new ArrayList<Double>();
	private static List<Double> posList = new ArrayList<Double>();
	
	public double[][] generate(double cruiseVelocity, double acceleration, double distanceUnits) {
		double acceleration10ms = acceleration / 100; // units per 100ms per 10ms
		double timeToAccel10ms = cruiseVelocity / acceleration10ms; // time it takes get to cruise velocity in 10ms
		double cruiseVelocity10ms = cruiseVelocity / 10; // cruise velo in units per 10ms
		double totalAccelDistanceUnits = cruiseVelocity10ms * timeToAccel10ms; //distance traveled during accel and deccel
		
		int i = 0;
		// Writes a velocity starting from 0 that increments until it gets to the cruise velocity
		for(double s = 0; s < cruiseVelocity; s = s + acceleration10ms) {
			speedList.add(i, s);
			i++;
		}
		
		if(distanceUnits >= totalAccelDistanceUnits) {
			double cruiseDistanceUnits = distanceUnits - totalAccelDistanceUnits; // Distance to travel during cruise in units
			int timeToCruise10ms = (int) Math.round(cruiseDistanceUnits / cruiseVelocity10ms); // amount of time in 10ms to cruise
			for(int s = 0; s < timeToCruise10ms; s++) {
				speedList.add(i, cruiseVelocity);
				//speed[n] = cruiseVelocity;
				i++;
			}
		}
		else {
			System.out.println("too small distance"); //TODO make triangular motion profile for this case
		}
		
		// Writes the de-acceleration velocities with same method as above
		for(double s = cruiseVelocity; s >= 0; s = s - acceleration10ms) {
			speedList.add(i, s);
			i++;
		}
		
		double pos = 0; // Variable to help write the pos array
		for(int m = 0; m < speedList.size(); m++) {
			pos = pos + (speedList.get(m) / 10);
			posList.add(m, pos);
		}

		double [][]profile = new double[speedList.size()][2];
		for(int m = 0; m < speedList.size(); m++) {
			profile[m][0] = speedList.get(m);
			profile[m][1] = posList.get(m);
		}
		
		return profile;

	}
	
}