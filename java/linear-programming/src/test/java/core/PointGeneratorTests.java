package core;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointGeneratorTests {

	@Test
	public void test() {
		PointGenerator generator = new PointGenerator(8,10,11.0);
		for(Double[] point : generator.generateFirstLoopPoints()) {
			System.out.print("[");
			for (int i = 0; i < 8; i++) {
				System.out.print(point[i]+" ");
			}
			System.out.println("]");
		}
	}

}
