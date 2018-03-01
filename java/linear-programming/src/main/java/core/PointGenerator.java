package core;

import java.util.ArrayList;
import java.util.Random;

public class PointGenerator {

	public PointGenerator(Integer numberOfVariables, Integer numberOfPoints, Double rangeMax) {
		this.numberOfVariables = numberOfVariables;
		this.numberOfPoints = numberOfPoints;
		this.rangeMax = rangeMax;
	}
	
	public ArrayList<Double[]> generateFirstLoopPoints() {
		ArrayList<Double[]> points = new ArrayList<Double[]>();
		
		for (int i = 0; i < numberOfPoints; i++) {
			Double[] coordinates = new Double[numberOfVariables];
			for (int j = 0; j < numberOfVariables; j++) {
				Random r = new Random();
				coordinates[j] = (rangeMax) * r.nextDouble();
			}
			points.add(coordinates);
		}
		
		return points;
	}
	
	private Integer numberOfPoints = 100;
	private Integer numberOfVariables;
	private Double rangeMax;
}
