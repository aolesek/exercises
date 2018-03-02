package parser;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.*;

public class Parser {

	public static void main(String[] args) throws IOException {
		ParsingEngine parser = new ParsingEngine();
		parser.setFilePath("friends.sub");
		parser.moveFrames(7);

	}

}