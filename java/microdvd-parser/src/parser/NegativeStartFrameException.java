package parser;

public class NegativeStartFrameException extends RuntimeException {
	public NegativeStartFrameException() {
		System.out.println("First frame below zero.");
	}
}
