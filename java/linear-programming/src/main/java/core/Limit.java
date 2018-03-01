package core;

public class Limit {
	
	public Limit(String s) {
		this.equation = new Evaluator(s);
		this.rightSide = equation.getRightSide();
	}
	
	public Boolean checkPoint(Double[] point) {
		this.equation.setVariables(point);
		return equation.getResult();
	}
	
	public Integer getVariablesQuantity() {
		return equation.getVariablesQuantity();
	}
	
	public Double getRightSide() {
		return equation.getRightSide();
	}
	
	public Double getLeftSide() {
		return equation.getLeftSide();
	}
	
	public String toString() {
		return equation.toString();
	}

	
	private Evaluator equation;
	private Double rightSide;
}
