package core;

import static org.junit.Assert.*;

import org.junit.Test;

public class LimitTests {

	@Test
	public void evaluationTest() {
		Limit limit = new Limit("x1 + x2 + 1 < 3");
		Double[] point = {1.0, 1.0};
		assertFalse(limit.checkPoint(point));
		Double[] point2 = {0.0, 1.0};
		assertTrue(limit.checkPoint(point2));
		Double[] point3 = {0.4, 0.3};
		assertTrue(limit.checkPoint(point2));
	}

}
