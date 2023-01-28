package BabyNames;
import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;

public class MiniProject {
    public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}

	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
        int totalNameBoys = 0;
		int totalGirls = 0;
        int totalnameGirls = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
                totalNameBoys++;
			}
			else {
				totalGirls += numBorn;
                totalnameGirls++;
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("female girls = " + totalGirls + " total names " + totalnameGirls);
		System.out.println("male boys = " + totalBoys+ " total names " + totalNameBoys);
	}

	public void testTotalBirths () {
		//FileResource fr = new FileResource();
		FileResource fr = new FileResource();
		totalBirths(fr);
	}
    
    public int getRank(int year, String name, String gender){
        int rank = -1;
        int counter = 0;
        String fileName = "testing/yob"+year+"short.csv";
        FileResource fr = new FileResource(fileName);
        for  (CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).contains(gender)){
                counter++;
                if(rec.get(0).contains(name)){
                   rank = counter;
                }
            }
        }
        return rank;
    }
    
    public String getName(int year, int rank, String gender){
        String name="";
        int counter = 0;
        String fileName = "testing/yob"+year+"short.csv";
        FileResource fr = new FileResource(fileName);
        for  (CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).contains(gender)){
                counter++;
                if(rank == counter){
                   name = rec.get(0);
                }
            }
        }
        if (name.isEmpty()|| rank==-1){return "NO NAME";
        } else{
        return name;}
    }
    
    public void whatIsNameInYear(int year, int newYear,String name, String gender){
        int rank = getRank(year, name, gender);
        String newname = getName(newYear, rank, gender);
        if(gender =="M"){gender="he";}else{gender="she";}
        System.out.println(name + " born in " + year + " would be " + newname +  "if "+ gender +" was born in " + newYear + ".");
    }

    public int yearOfHighestRank(String name, String gender){
        int highestRank=-1;
        int rank=0;
        boolean flag = false;
        int counter = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            counter = 0;
            rank=0;
            FileResource fr = new FileResource(f);
            for  (CSVRecord rec : fr.getCSVParser(false)){
                if(rec.get(1).contains(gender)){
                    counter++;
                    if(rec.get(0).contains(name)){
                        if (flag ==false){flag = true; highestRank=counter;}                       
                        rank = counter;
                    }
                }
            }
            if(flag&& (rank<highestRank)){highestRank=rank;}
        }
        return highestRank;
    }

    public double getAverageRank(String name, String gender){
        int total = 0;
        int counter =0;
        int counterYears =0;
        int rank = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            counter =0;
            rank = 0;
            FileResource fr = new FileResource(f);
            for  (CSVRecord rec : fr.getCSVParser(false)){
                if(rec.get(1).contains(gender)){
                    counter++;
                    if(rec.get(0).contains(name)){
                       rank = counter;
                    }
                }
            }total +=rank;counterYears++;
        }
        return ((double) total)/counterYears;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int totalBirths = 0;
        String fileName = "testing/yob"+year+"short.csv";
        FileResource fr = new FileResource(fileName);
        for  (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if(rec.get(1).contains(gender)){
                if(rec.get(0).contains(name)){break;}
                totalBirths += numBorn;
            }
        }return totalBirths;
    }


    public static void main(String[] args) {
        MiniProject hw = new MiniProject();
        System.out.println(hw.getAverageRank("Raul", "M"));

    }
    
}
