package genes;
import edu.duke.*;
import java.io.File;

public class StringsThirdAssignments {

    public double cgRatio (String dna){
        dna = dna.toUpperCase();
        int counter = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'C' || dna.charAt(i)=='G'){counter++;}            
        }
        double result2=(dna.length());
        return counter/result2;
    }
    
    public void testCgRatio(String input, double expected){
        double result = cgRatio(input);
        System.out.println("TEST CASE for: " + input);
        if (expected==result) {
			System.out.println("success for " + input + " value: " + result);
		}
		else {
			System.out.println("mistake for input: " + input + " got: " + result + " not: " + expected);
		}
    }

    public int countCTG (String dna){
        dna = dna.toUpperCase();
        int counter =0;
        int index = dna.indexOf("CTG");
        while (true){
           if (index == -1 || index >= dna.length() - 2){break;}
           String found = dna.substring(index, index+3);
           counter++;
           System.out.println(found);
           index = dna.indexOf("CTG",index+3);
        }
        return counter;
    }

    public void testCountCTG(String input, int expected){
        int result = countCTG(input);
        System.out.println("TEST CASE for: " + input);
        if (expected==result) {
			System.out.println("success for " + input + " value: " + result);
		}
		else {
			System.out.println("mistake for input: " + input + " got: " + result + " not: " + expected);
		}
    }

    public void processGenes(StorageResource genes){
        String longestGene = "";
        int counter1 = 0;
        int counter2 = 0;
        int minLen = 60;
        for (String word : genes.data()) {
            
            int len =0;
            
            if(word.length()>minLen){
                System.out.println("Longer than"+ minLen+": " +word);
                counter1++;
                if (word.length()>len){
                    len=word.length();
                    longestGene=word;
                }
            }
            if(cgRatio(word)>0.35){
                System.out.println("Bigger than 0.35: " +word);
                counter2++;
            }
        }
        System.out.println("longest: " + longestGene);
        System.out.println("Longer than "+ minLen+": " + counter1);
        System.out.println("Total bigger than 0.35: " + counter2);
    }

    public void testProcessGenes(){
        StringsSecondAssignments test = new StringsSecondAssignments();
        StorageResource genes = new StorageResource();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            genes = test.getAllGenes1(f);
            int counter=0;
            for (String g : genes.data()) {
                counter++;
            }
            System.out.println("total Genes: " + counter);
            processGenes(genes);
            
        }
        
    }

    public static void main(String[] args) {
        StringsThirdAssignments hw = new StringsThirdAssignments();
        //hw.testCgRatio("ATGCCATAG", 4.0/9.0);
        //System.out.println(hw.countCTG("CTGCTGCTGCTGCTG"));
        hw.testProcessGenes();
    }
}
