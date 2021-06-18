package controlledAssessment;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class sharedFunctionsAndVariables {
	static String username;
	
	static JFrame f;
	
	static int appHeight = 800;
	static int appWidth = 800;
	
	//BufferedImage image = load("");
	public static BufferedImage load(String path) throws IOException{ //the is method takes in a string to find the image and returns it.
		BufferedImage img = ImageIO.read(new File(path)); //the bufferedImage uses the IO stream to read from the file
		return img;//returns the buffered image
	}
}
