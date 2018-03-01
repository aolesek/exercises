package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LinProgProblem {
	public Point solve(Integer iter) {
		long start=System.currentTimeMillis();
		Point neighbor = findGreatestPoint(findFirstLoopPoints());
		
		System.out.println("\nNajwi?kszy punkt wylosowany w pierwszej iteracji");
		System.out.println(neighbor+"\n");
		
		Point point = findGreatestPoint(
				findSecondLoopPoints(neighbor.getCoordinates())
			);
		for (int i = 0; i < iter; i++) {
			point = findGreatestPoint(
					findSecondLoopPoints(point.getCoordinates())
					);
		}
		
		long stop=System.currentTimeMillis();
		
		System.out.println("\nNajwi?kszy punkt wylosowany ostatniej iteracji");
		System.out.println(point);
		
		Double time = (double) ((stop-start)/1000);
		
		
		System.out.println("Czas wykonania:"+time+"s");
		return point;
	}
	
	public LinProgProblem(String obj) {
		limitations = new ArrayList<Limit>();
		this.objective = obj;
	}
	public void addLimitation(Limit limit) {
		limitations.add(limit);
	}
	
	public void defineStandardLowerLimits() {
		// Generates lower limit for each variable
		for(int i = 1; i <= findNumberOfVariables(); i++) {
			String lim = "x"+i+" > 0";
			limitations.add(new Limit(lim));
		}
	}
	
	public void defineStandardUpperLimits() {
		Double maxRightSide = 0.0;
		for (Limit limit : limitations) {
			if (limit.getRightSide() > maxRightSide) {
				maxRightSide = limit.getRightSide();
			}
		}
		
		this.randomUpperLimit = maxRightSide;
		
		for (int i = 1; i <= findNumberOfVariables(); i++) {
			String lim = "x"+i+" < "+maxRightSide;
			limitations.add(new Limit(lim));
		}
	}
	
	public void defineCustomUpperLimits(Double maxValue) {
		for (int i = 1; i <= findNumberOfVariables(); i++) {
			String lim = "x"+i+" < "+maxValue;
			limitations.add(new Limit(lim));
		}
		
		this.randomUpperLimit = maxValue;
	}
	
	public void defineCustomUpperLimits(Double[] maxValues) {
		Double max = 0.0;
		
		for (int i = 1; i <= findNumberOfVariables(); i++) {
			if (maxValues[i-1] > max)
				max = maxValues[i-1];
			String lim = "x"+i+" < "+maxValues[i-1];
			limitations.add(new Limit(lim));
		}
		
		this.randomUpperLimit = max;
	}
	
	public String toString() {
		String contents = new String();
		for(Limit limit : limitations) {
			contents += limit.toString() + "\n";
		}
		
		return contents;
	}
	
	public ArrayList<Double[]> findFirstLoopPoints() {
		ArrayList<Double[]> points = new ArrayList<Double[]>();
		Boolean allPointsFound = false;
		while(!allPointsFound) {
			Double[] coordinates = new Double[findNumberOfVariables()];

			for (int j = 0; j < findNumberOfVariables(); j++) {
				Random r = new Random();
				coordinates[j] = (randomUpperLimit) * r.nextDouble();
			}
			
			
			Boolean isPointAppr = true;
			for (Limit limit : limitations) {
				if (limit.checkPoint(coordinates) == false) {
					isPointAppr = false;
					break;
				}
			}
			
			if (isPointAppr) {
				points.add(coordinates);
				if (info) {
					System.out.print("Znaleziono punkt:");
					System.out.print("[");
					for (int i = 0; i < findNumberOfVariables(); i++) {
						System.out.print(coordinates[i]+"  ");
					}
					System.out.println("]");
					System.out.println("??cznie znalezionych punktow: "+points.size());
				}

			}
			
			if (points.size() >= numberOfPoints)
				allPointsFound = true;
		}
		
		return points;	
	}
	
	public void setInfo(Boolean info) {
		this.info = info;
	}
	public Point findGreatestPoint(ArrayList<Double[]> points) {
		Double maxValue = -100.0;
		Point greatestPoint = null; 
		for(Double[] point : points) {
			Evaluator evaluator = new Evaluator(objective);
			evaluator.setVariables(point);
			if (evaluator.getLeftSide() > maxValue) {
				maxValue = evaluator.getLeftSide();
				greatestPoint = new Point(point, maxValue);
			}
		}
		
		return greatestPoint;
	}
	
	public ArrayList<Double[]> findSecondLoopPoints(Double[] neighbor) {
		this.secondLoopOffset = 0.8 * this.secondLoopOffset;
		ArrayList<Double[]> points = new ArrayList<Double[]>();
		Boolean allPointsFound = false;
		while(!allPointsFound) {
			Double[] coordinates = new Double[findNumberOfVariables()];

			for (int j = 0; j < findNumberOfVariables(); j++) {
				Random r = new Random();
				coordinates[j] = (neighbor[j] - secondLoopOffset) + ((neighbor[j] + secondLoopOffset) - (neighbor[j] - secondLoopOffset)) * r.nextDouble();
			}
			
			
			Boolean isPointAppr = true;
			for (Limit limit : limitations) {
				if (limit.checkPoint(coordinates) == false) {
					isPointAppr = false;
					break;
				}
			}
			
			if (isPointAppr) {
				points.add(coordinates);
				if (info) {
					System.out.println("Znaleziono punkt:");
					System.out.print("[");
					for (int i = 0; i < findNumberOfVariables(); i++) {
						System.out.print(coordinates[i]+"  ");
					}
					System.out.println("]");
					System.out.println("??cznie znalezionych punktow: "+points.size());
				}

			}
			
			if (points.size() >= numberOfPoints)
				allPointsFound = true;
		}
		
		return points;	
	}
	
	
	
	public Integer findNumberOfVariables() {
		int variablesQuantity = 0;
		for (Limit limit : limitations) {
			if (limit.getVariablesQuantity() > variablesQuantity) {
				variablesQuantity = limit.getVariablesQuantity();
			}
		}
		return variablesQuantity;
	}

	private String objective;
	private ArrayList<Limit> limitations;
	private Double randomUpperLimit;
	private Double secondLoopOffset = 0.5;
	private Integer numberOfPoints = 1000;
	private Boolean info = false;
	
	public Double getSecondLoopOffset() {
		return secondLoopOffset;
	}
	public void setSecondLoopOffset(Double secondLoopOffset) {
		this.secondLoopOffset = secondLoopOffset;
	}
	public Integer getNumberOfPoints() {
		return numberOfPoints;
	}
	public void setNumberOfPoints(Integer numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
	}
	
	
}
