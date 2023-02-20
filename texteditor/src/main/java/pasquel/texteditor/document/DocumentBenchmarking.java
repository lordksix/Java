package pasquel.texteditor.document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/** A class for timing the EfficientDocument and BasicDocument classes
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 */

public class DocumentBenchmarking {

	
	public static void main(String [] args) {

	    // Run each test more than once to get bigger numbers and less noise.
	    // You can try playing around with this number.
	    int trials = 100;

	    // The text to test on
	    String textfile = "data/warAndPeace.txt";
		
	    // The amount of characters to increment each step
	    // You can play around with this
		int increment = 20000;

		// The number of steps to run.  
		// You can play around with this.
		int numSteps = 20;
		
		// THe number of characters to start with. 
		// You can play around with this.
		int start = 50000;

		// and prints out timing results as described in the assignment 
		// instructions and following the pseudocode below.
		for (int numToCheck = start; numToCheck < numSteps*increment + start; 
				numToCheck += increment)
		{

			System.out.print(numToCheck + "\t");

			String wordBench = getStringFromFile(textfile, numToCheck);

			long startTime = System.nanoTime();
			for (int i = 0; i < trials; i++) {
				BasicDocument bd = new BasicDocument(wordBench);
				double fleshScore = bd.getFleschScore();
				System.out.print(fleshScore + "\t");	
			}			
			long endTime = System.nanoTime();
			double passedTime = ((endTime-startTime)/1000000000.0);
			System.out.print(passedTime  + "\t");

			long startTime1 = System.nanoTime();
			for (int i = 0; i < trials; i++) {
				EfficientDocument ed = new EfficientDocument(textfile);
				double fleshScore1 = ed.getFleschScore();
				System.out.print(fleshScore1 + "\t");		
			}
			long endTime1 = System.nanoTime();
			double passedTime1 = ((endTime1-startTime1)/1000000000.0);
			System.out.print(passedTime1  + "\n");			 
		}
	
	}
	
	/** Get a specified number of characters from a text file
	 * 
	 * @param filename The file to read from
	 * @param numChars The number of characters to read
	 * @return The text string from the file with the appropriate number of characters
	 */
	public static String getStringFromFile(String filename, int numChars) {
		
		StringBuilder s = new StringBuilder();
		try {
			FileInputStream inputFile= new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(inputFile);
			BufferedReader bis = new BufferedReader(inputStream);
			int val;
			int count = 0;
			while ((val = bis.read()) != -1 && count < numChars) {
				s.append((char)val);
				count++;
			}
			if (count < numChars) {
				System.out.println("Warning: End of file reached at " + count + " characters.");
			}
			bis.close();
		}
		catch(Exception e)
		{
		  System.out.println(e);
		  System.exit(0);
		}
		
		
		return s.toString();
	}
	
}
