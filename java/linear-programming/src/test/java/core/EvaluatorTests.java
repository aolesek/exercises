package core;

import static org.junit.Assert.*;

import org.junit.Test;

public class EvaluatorTests {

	@Test
	public void evaluatingTest() {
		Evaluator ev1 = new Evaluator("6 < 7");
		assertEquals(ev1.getResult(), Boolean.valueOf(true));
		
		Evaluator ev2 = new Evaluator("-1 > 11");
		assertEquals(ev2.getResult(), Boolean.valueOf(false));
	}
	
	@Test
	public void leftSideNumericalValueTest() {
		Evaluator ev1 = new Evaluator("8 < 7");
		assertEquals(ev1.getLeftSide(), Double.valueOf(8.0),0.1);
		
		Evaluator ev2 = new Evaluator("0.33  > 11");
		assertEquals(ev2.getLeftSide(), Double.valueOf(0.333), 0.1);
	}
	
	@Test
	public void rightSideNumericalValueTest() {
		Evaluator ev1 = new Evaluator("6 < 7");
		assertEquals(ev1.getRightSide(), Double.valueOf(7.0),0.1);
		
		Evaluator ev2 = new Evaluator("0.33 > 11");
		assertEquals(ev2.getRightSide(), Double.valueOf(11.0),0.1);
	}
	
	@Test
	public void complexTermTest() {
		Double[] variables = {-7.0, 8.0, 9.0};
		
		Evaluator ev1 = new Evaluator("x1 < 11", variables);
		assertEquals(ev1.getLeftSide(), Double.valueOf(-7.0), 0.1);
		Evaluator ev2 = new Evaluator("x2 < 11", variables);
		assertEquals(ev2.getLeftSide(), Double.valueOf(8.0), 0.1);
		Evaluator ev3 = new Evaluator("x3 < 11", variables);
		assertEquals(ev3.getLeftSide(), Double.valueOf(9.0), 0.1);
	}
	
	@Test
	public void complexTermWithMulTest() {
		Double[] variables = {-7.0, 8.0, 9.0};
		
		Evaluator ev1 = new Evaluator("-2*x1 < 11", variables);
		assertEquals(ev1.getLeftSide(), Double.valueOf(14.0), 0.1);
	}
	
	@Test
	public void complexTermWithExpTest() {
		Double[] variables = {-7.0, 8.0, 9.0};
		
		Evaluator ev1 = new Evaluator("x3^2 < 11", variables);
		assertEquals(ev1.getLeftSide(), Double.valueOf(81.0), 0.1);
	}
	
	@Test
	public void complexTermWithExpAndMulTest() {
		Double[] variables = {-7.0, -5.0, 9.0};
		
		Evaluator ev1 = new Evaluator("3*x2^2 < 11", variables);
		assertEquals(ev1.getLeftSide(), Double.valueOf(75.0), 0.1);
	}
	
	@Test
	public void multipleFactorsComplexTermTest() {
		Double[] variables = {-2.0, 3.0, -4.0};
		
		Evaluator ev1 = new Evaluator("x1 + 2*x2^3 + x3^2 > 123", variables);
		assertEquals(ev1.getLeftSide(), Double.valueOf(68.0), 0.1);
		assertEquals(ev1.getRightSide(), Double.valueOf(123.0), 0.1);
		assertFalse(ev1.getResult());
				
		Evaluator ev2 = new Evaluator("-2 * x1 + 2*x2^3 + x3^2 > 11", variables);
		assertEquals(ev2.getLeftSide(), Double.valueOf(74.0), 0.1);
		assertEquals(ev2.getRightSide(), Double.valueOf(11.0), 0.1);
		assertEquals(ev2.getVariablesQuantity(), Integer.valueOf(3));
		assertTrue(ev2.getResult());
		
		Double[] variables2 = {-10.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0};
		Evaluator ev3 = new Evaluator("x1 + x2 + x3 + x4 + x5 + x6 + x7 + x8 + x9 + x10 + x11 > 11", variables2);
		assertEquals(ev3.getLeftSide(), Double.valueOf(1.0), 0.1);
		assertEquals(ev3.getVariablesQuantity(), Integer.valueOf(11));
		
		Double[] variables3 = {-10.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 5.0};

		
		Evaluator ev4 = new Evaluator("x11 > 11", variables2);
		ev4.setVariables(variables3);
		assertEquals(ev4.getLeftSide(), Double.valueOf(5.0), 0.1);
		assertEquals(ev4.getVariablesQuantity(), Integer.valueOf(11));
		
		

	}


}
