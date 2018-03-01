package core;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinProgProblemTests {


	@Test
	public void ex1() {
		Integer iter = 10; //Liczba iteracji
		
		LinProgProblem problem = new LinProgProblem("6*x1 + 8*x2 + 5*x3 + 9*x4 max");
		problem.addLimitation(new Limit("2*x1 + x2 + x3 + 3*x4 < 5"));
		problem.addLimitation(new Limit("x1 + 3*x2 + x3 + 2*x4 < 3"));
		problem.defineStandardLowerLimits();
		
		problem.defineStandardUpperLimits();
		problem.setNumberOfPoints(100);		
		problem.setSecondLoopOffset(1.0);
		
		
		problem.setInfo(true);
		
		Point solution = problem.solve(10);	
		
		assertEquals(Double.valueOf(17.0), solution.getValue(), 0.5);
	}
	
	@Test
	public void ex2() {
	
		LinProgProblem problem = new LinProgProblem("2*x1 + x2 max");
		problem.addLimitation(new Limit("2*x1 + x2 < 4"));
		problem.addLimitation(new Limit("2*x1 + 3*x2 < 3"));
		problem.addLimitation(new Limit("4*x1 + x2 < 5"));
		problem.addLimitation(new Limit("x1 + 5*x2 < 1"));
		problem.defineStandardLowerLimits();
		
		problem.defineStandardUpperLimits();
		problem.setNumberOfPoints(100);		
		problem.setSecondLoopOffset(1.0);
		
		
		problem.setInfo(true);
		
		Point solution = problem.solve(20);	
		
		assertEquals(Double.valueOf(2), solution.getValue(), 0.5);
	}
	
	@Test
	public void ex3() {
	
		LinProgProblem problem = new LinProgProblem("2*x1 + -6*x2 max");
		problem.addLimitation(new Limit("-1*x1 + -1*x2 + -1*x3 < -2"));
		problem.addLimitation(new Limit("2*x1 + -1*x2 + x3 < 1"));
		problem.defineStandardLowerLimits();
		
		problem.defineCustomUpperLimits(10.0);
		problem.setNumberOfPoints(100);		
		problem.setSecondLoopOffset(1.0);
		
		
		problem.setInfo(true);
		
		Point solution = problem.solve(10);	
		
		assertEquals(Double.valueOf(-3), solution.getValue(), 0.5);
	}
	
	@Test
	public void ex4() {
	
		LinProgProblem problem = new LinProgProblem("-1*x1 + -3*x2 + -1*x3 max");
		problem.addLimitation(new Limit("2*x1 + -5*x2 + x3 < -5"));
		problem.addLimitation(new Limit("2*x1 + -1*x2 + 2*x3 < 4"));
		problem.defineStandardLowerLimits();
		
		problem.defineCustomUpperLimits(10.0);
		problem.setNumberOfPoints(100);		
		problem.setSecondLoopOffset(1.0);
		
		
		problem.setInfo(true);
		
		Point solution = problem.solve(10);	
		
		assertEquals(Double.valueOf(-3), solution.getValue(), 0.5);
	}
	
	@Test
	public void ex5() {
	
		LinProgProblem problem = new LinProgProblem("x1 + 3*x2 max");
		problem.addLimitation(new Limit("-1*x1 + -1*x2 < -3"));
		problem.addLimitation(new Limit("-1*x1 + x2 < -1"));
		problem.addLimitation(new Limit("x1 + 2*x2 < 4"));
		problem.defineStandardLowerLimits();
		
		problem.defineStandardUpperLimits();
		problem.setNumberOfPoints(100);		
		problem.setSecondLoopOffset(1.0);
		
		
		problem.setInfo(true);
		
		Point solution = problem.solve(10);	
		
		assertEquals(Double.valueOf(5), solution.getValue(), 0.5);
	}
	
	// @Test
	public void ex6() {
	
		LinProgProblem problem = new LinProgProblem("x1 + 3*x2 max");
		problem.addLimitation(new Limit("-1*x1 + -1*x2 < -3"));
		problem.addLimitation(new Limit("-1*x1 + x2 < -1"));
		problem.addLimitation(new Limit("x1 + 2*x2 < 2"));
		problem.defineStandardLowerLimits();
		
		problem.defineStandardUpperLimits();
		problem.setNumberOfPoints(100);		
		problem.setSecondLoopOffset(1.0);
		
		
		problem.setInfo(true);
		
		Point solution = problem.solve(5);	
		
		assertEquals(Double.valueOf(5), solution.getValue(), 0.5);
	}
	
	

}
