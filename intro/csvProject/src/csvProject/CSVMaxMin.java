package csvProject;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class CSVMaxMin {

	public CSVParser tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        return parser;
    }

    public CSVRecord hottestHourInFile(CSVParser parser) {
		//start with largestSoFar as nothing
		CSVRecord largestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			// use method to compare two records
			largestSoFar = getLargestOfTwo(currentRow, largestSoFar, "TemperatureF");
		}
		//The largestSoFar is the answer
		return largestSoFar;
	}

	public void testHottestInDay () {
		FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
		CSVRecord largest = hottestHourInFile(fr.getCSVParser());
		System.out.println("hottest temperature was " + largest.get("TemperatureF") +
				   " at " + largest.get("TimeEST"));
	}

	public CSVRecord hottestInManyDays() {
		CSVRecord largestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
			// use method to compare two records
			largestSoFar = getLargestOfTwo(currentRow, largestSoFar, "TemperatureF");
		}
		//The largestSoFar is the answer
		return largestSoFar;
	}

	public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar, String parameter) {
		//If largestSoFar is nothing
		if (largestSoFar == null) {largestSoFar = currentRow;}
		//Otherwise
		else if(parameter=="Humidity"){
			String smalleString = currentRow.get(parameter);
			if (smalleString.contains("N/A")== false ){
				double currentTemp = Double.parseDouble(smalleString);
				double smallestTemp = Double.parseDouble(largestSoFar.get(parameter));
				//Check if currentRow’s temperature > largestSoFarr’s
				if ((currentTemp != -9999) && (currentTemp > smallestTemp)) {
					//If so update largestSoFar to currentRow
					largestSoFar = currentRow;
				}
			}
		} else{
			double currentTemp = Double.parseDouble(currentRow.get(parameter));
			double smallestTemp = Double.parseDouble(largestSoFar.get(parameter));
			//Check if currentRow’s temperature > largestSoFar’s
			if ((currentTemp != -9999) && (currentTemp > smallestTemp)) {
				//If so update largestSoFar to currentRow
				largestSoFar = currentRow;
			}
		}
		return largestSoFar;
	}

	public CSVRecord getSmallesttOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar, String parameter) {
		//If largestSoFar is nothing
		if (smallestSoFar == null) {smallestSoFar = currentRow;}
		//Otherwise
		else if(parameter=="Humidity"){
			String smalleString = currentRow.get(parameter);
			if (smalleString.contains("N/A")== false ){
				double currentTemp = Double.parseDouble(smalleString);
				double smallestTemp = Double.parseDouble(smallestSoFar.get(parameter));
				//Check if currentRow’s temperature > smallestSoFar’s
				if ((currentTemp != -9999) && (currentTemp < smallestTemp)) {
					//If so update smallestSoFar to currentRow
					smallestSoFar = currentRow;
				}
			}
		} else{
			double currentTemp = Double.parseDouble(currentRow.get(parameter));
			double smallestTemp = Double.parseDouble(smallestSoFar.get(parameter));
			//Check if currentRow’s temperature > smallestSoFar’s
			if ((currentTemp != -9999) && (currentTemp < smallestTemp)) {
				//If so update smallestSoFar to currentRow
				smallestSoFar = currentRow;
			}
		}
		return smallestSoFar;
	}

	public void testHottestInManyDays () {
		CSVRecord largest = hottestInManyDays();
		System.out.println("hottest temperature was " + largest.get("TemperatureF") +
				   " at " + largest.get("DateUTC"));
	}

	public CSVRecord coldestHourInFile (CSVParser parser){
		//start with smallestSoFar as nothing
		CSVRecord smallestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			// use method to compare two records
			smallestSoFar = getSmallesttOfTwo(currentRow, smallestSoFar,"TemperatureF");
		}
		//The smallestSoFar is the answer
		return smallestSoFar;
	}

	public void testColdestInDay () {
		FileResource fr = new FileResource();
		CSVRecord largest = coldestHourInFile(fr.getCSVParser());
		System.out.println("Coldest temperature was " + largest.get("TemperatureF") +
				   " at " + largest.get("DateUTC"));
	}

	public File fileWithColdestTemperature (){
		CSVRecord coldestSoFar = null;
		File coldestFile = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			// use method to compare two records
			coldestSoFar = getSmallesttOfTwo(currentRow, coldestSoFar,"TemperatureF");
			if (coldestSoFar==currentRow){coldestFile=f;}
		}
		//The largestSoFar is the answer
		return coldestFile;
	}

	public void testFileWithColdestTemperature(){
		File coldestFile = fileWithColdestTemperature();
		System.out.println("Coldest day was in file " + coldestFile.getName());
		FileResource fr = new FileResource(coldestFile);
		CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
		System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
		CSVParser parser = fr.getCSVParser();
		System.out.println("All the Temperatures on the coldest day were:");
		for(CSVRecord record: parser){
			System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
		}

	}

	public CSVRecord coldestInManyDays() {
		CSVRecord smallestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			// use method to compare two records
			smallestSoFar = getSmallesttOfTwo(currentRow, smallestSoFar, "TemperatureF");
		}
		//The smallestSoFar is the answer
		return smallestSoFar;
	}

	public void testColdestInManyDays () {
		CSVRecord largest = coldestInManyDays();
		System.out.println("Coldest temperature was " + largest.get("TemperatureF") +
				   " at " + largest.get("DateUTC"));
	}

	public CSVRecord lowestHumidityInFile(CSVParser parser){
		//start with smallestSoFar as nothing
		CSVRecord smallestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			// use method to compare two records
			smallestSoFar = getSmallesttOfTwo(currentRow, smallestSoFar,"Humidity");
		}
		//The smallestSoFar is the answer
		return smallestSoFar;
	}

	public void testLowestHumidityInFile(){
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord csv = lowestHumidityInFile(parser);
		System.out.println("Lowest Humidity was " + csv.get("Humidity") +
				   " at " + csv.get("DateUTC"));
	}

	public CSVRecord lowestHumidityInManyFiles() {
		CSVRecord smallestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
			// use method to compare two records
			smallestSoFar = getSmallesttOfTwo(currentRow, smallestSoFar, "Humidity");
		}
		//The largestSoFar is the answer
		return smallestSoFar;
	}

	public void testLowestHumidityInManyFile() {
		CSVRecord lowest = lowestHumidityInManyFiles();
		System.out.println("Lowest Humidity was " + lowest.get("Humidity") +" at " + lowest.get("DateUTC"));
	}

	public double averageTemperatureInFile(CSVParser parser){
		double total = 0;
		int counter = 0;
		double currentTemp = 0;
		for (CSVRecord currentRow : parser) {
			currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			if(currentTemp!=-9999){
				total+=currentTemp;
				counter++;
			}
		}
		return total/counter;
	}
	
	public void testAverageTemperatureInFile(){
		System.out.println("Average temperature in file was " + averageTemperatureInFile(tester()));
	}

	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
		double total = 0;
		int counter = 0;
		double currentTemp = 0;
		String humidity = "";
		for (CSVRecord currentRow : parser) {
			currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			humidity = currentRow.get("Humidity");
			if(humidity != "N/A"){
				if(currentTemp!=-9999 && Double.parseDouble(humidity)>= value){
					total+=currentTemp;
					counter++;
				}
			}
		}
		if(counter>0){
		return total/counter;
		} else{return 0.0;}
	}

	public void testAverageTemperatureWithHighHumidityInFile(){
		CSVParser parser = tester();
		int input = 80;
		double avg = averageTemperatureWithHighHumidityInFile(parser, input);
		if(avg>0){
			System.out.println("Average temperature with humidity greater than " + input + " was " + avg);
		}else{
			System.out.println("No temperatures with that humidity");
		}
	}

    public static void main(String[] args) {
        CSVMaxMin hw = new CSVMaxMin();
        hw.testColdestInManyDays();
    }
}
