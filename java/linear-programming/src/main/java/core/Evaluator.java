package core;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Evaluates equations or inequalities given in string.
 * @author arekd
 *
 */
public class Evaluator {

	public Evaluator(String equation) {
		this.equation = equation;
	}
	
	public Evaluator(String equation, Double[] variables) {
		this.equation = equation;
		this.variables = variables;
	}
	
	public void setVariables(Double[] variables) {
		this.variables = variables;
	}

	/**
	 * @return Value of the left side of an equation or inequality.
	 */
	public Boolean getResult() {
		processEquation();
		switch(sign) {
		case GREATER:
			return (leftSideValue > rightSideValue);
		case LESS:
			return (leftSideValue < rightSideValue);
		case EQUAL:
			return (leftSideValue == rightSideValue);
		}
		return false;
	}

	public Double getLeftSide() {
		processEquation();
		return leftSideValue;
	}

	public Double getRightSide() {
		processEquation();
		return rightSideValue;
	}
	
	public Integer getVariablesQuantity() {
		Pattern pattern = Pattern.compile("x([0-9][0-9]?)");
        Matcher matcher = pattern.matcher(equation);
        
        int maxIndex = 0;
        
        while(matcher.find()) {
        	int currentIndex;
        	currentIndex = Integer.parseInt(matcher.group(1));
        	if ( currentIndex > maxIndex) {
        		maxIndex = currentIndex;
        	}		
        }
        
		return maxIndex;
	}
	
	public String toString() {
		return equation;
	}
	
	
	private void processEquation() {
		
		if ( equation.contains(">") ) {
			this.sign = Sign.GREATER;
		} else if ( equation.contains("<") ) {
			this.sign = Sign.LESS;
		} else if ( equation.contains("=") ) {
			this.sign = Sign.EQUAL;
		} else if ( equation.contains("max") ) {
			this.sign = Sign.FUNCTION;
		} else {
			throw new IllegalArgumentException();
		}
		
		String[] parts = null;
		switch(sign) {
		case GREATER:
			parts = equation.split(Pattern.quote(">"));
			processLeftSide(parts[0]);
			processRightSide(parts[1]);
			break;
		case LESS:
			parts = equation.split(Pattern.quote("<"));
			processLeftSide(parts[0]);
			processRightSide(parts[1]);
			break;
		case EQUAL:
			parts = equation.split(Pattern.quote("="));
			processLeftSide(parts[0]);
			processRightSide(parts[1]);
			break;
		case FUNCTION:
			parts = equation.split(Pattern.quote("max"));
			processLeftSide(parts[0]);
			break;
		}
		

	}
	
	private void processLeftSide(String s) {
		if ( (variables != null) || !s.contains("x")) {
			leftSideValue = 0.0;
			rightSideValue = 0.0;
			String[] terms;
			terms = s.split(Pattern.quote("+"));
			for (String term : terms) {
				if (term.contains("x")) {
					leftSideValue += evaluateComplexTerm(term); 
				} else {
					leftSideValue += Double.parseDouble(term);
				}
			}
		}

	}
	
	/*
	 * Processes term with variables, multiplying and exponentiation.
	 * eg. a*x1^n
	 */
	private Double evaluateComplexTerm(String term) {
		Double a = 1.0, n = 1.0;
		if( term.contains("*") ) {
			String[] mul = term.split(Pattern.quote("*"));
			a = Double.parseDouble(mul[0]);
			if ( term.contains("^") ) {
				String[] exp = mul[1].split(Pattern.quote("^"));
				n = Double.parseDouble(exp[1]);
			}
		} else {
			if ( term.contains("^") ) {
				String[] exp = term.split(Pattern.quote("^"));
				n = Double.parseDouble(exp[1]);
			}
		}
		
		//Finding the x index
		final Pattern pattern = Pattern.compile("x([0-9][0-9]?)");
		Scanner s = new Scanner(term);
          
		String dataStr = s.findInLine(pattern);
		if (dataStr == null) {
			throw new IllegalArgumentException("Provided string does not contain valid value");
		}
		Matcher m = pattern.matcher(dataStr);
		m.find();
		Integer index = Integer.parseInt( m.group(1) ) - 1;
		
		return a * Math.pow(variables[index], n);
	}
	
	/*
	 * Converts right side into numerical value
	 */
	private void processRightSide(String s) {
		try {
			this.rightSideValue = Double.parseDouble(s);
		} catch (Exception e) {
			
		}
	}	
	
	private String equation;
	private Sign sign;
	private Double leftSideValue = 0.0, rightSideValue = 0.0;
	private Double[] variables;
	private Integer numberOfVariables = 0;

}
