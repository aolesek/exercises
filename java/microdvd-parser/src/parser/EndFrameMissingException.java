package parser;

public class EndFrameMissingException extends RuntimeException {
	public EndFrameMissingException() {
		System.out.println("Brak ostatniej klatki.");
	}
}
