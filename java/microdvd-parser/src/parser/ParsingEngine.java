package parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingEngine {
	BufferedReader reader;
	BufferedWriter writer;
	Charset charset;
	String filePath;
	
	public ParsingEngine() {
		charset = charset = Charset.forName("ISO-8859-1");
	}
	
	public void setFilePath(String newPath) throws IOException {
		filePath = newPath;
		setSourceFile();
		setOutputFile();
	}
	
	public void setSourceFile() throws IOException {
		Path path = Paths.get(filePath);
		reader = Files.newBufferedReader(path, charset);
	}
	
	public void setOutputFile() throws IOException {
		String newPath = filePath+"_";
		Path path = Paths.get(newPath);
		writer = Files.newBufferedWriter(path, charset);	
	}
		
	public void moveFrames(int numberOfFrames) throws RuntimeException, IOException {
		String line, dataStr;
		int startFrame, endFrame;
		final Pattern pattern = Pattern.compile("\\{([0-9]+)\\}\\{([0-9]+)\\}(.*)");
		Scanner s = new Scanner(reader);
		while (s.hasNextLine()) {

			dataStr = s.findInLine(pattern);
			Matcher m = pattern.matcher(dataStr);
			m.find();
			startFrame = Integer.parseInt(m.group(1)) + numberOfFrames;
			
			if (startFrame < 0) throw new NegativeStartFrameException();
			endFrame = Integer.parseInt(m.group(2)) + numberOfFrames;
			
			writer.write("{"+startFrame+"}{"+endFrame+"}"+m.group(3)+"\n");
			if (s.hasNextLine()) line = s.nextLine();
		}
		
		writer.close();
	}
}
