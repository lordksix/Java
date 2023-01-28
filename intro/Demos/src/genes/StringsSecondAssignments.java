package genes;

import edu.duke.*;
import java.io.File;

public class StringsSecondAssignments {

    public int findStopColon(String dna, int startIndex, String stopColon){
        //returns the index of the first occurrence of stopCodon that appears past 
        //startIndex and is a multiple of 3 away from startIndex. If there is no such 
        //stopCodon, this method returns the length of the dna strand.
        int currentIndex = dna.indexOf(stopColon,startIndex+3);
        while (currentIndex !=-1) {
            if((currentIndex - startIndex)%3==0){
                return currentIndex+3;
                
            }else{
                currentIndex = dna.indexOf(stopColon,currentIndex+1);
            }            
        }
        return dna.length();
    }
    
    public void testStopColon(String input, int expected, int startIndex, String stopColon){
        String strExpected = Integer.toString(expected);
        int result = findStopColon(input,startIndex,stopColon);
        System.out.println("TEST CASE for: " + input + " start Index: " +startIndex + " Stop Colon: " + stopColon);
        if (strExpected.equals(Integer.toString(result))) {
			System.out.println("success for " + input + " value: " + result);
		}
		else {
			System.out.println("mistake for input: " + input + " got: " + result + " not: " + expected);
		}
    }

    public void testFindStopColonVarios(){
        System.out.println("Test 1");
        testStopColon("atggggtttaaataataatag", 18, 0, "tag");
        System.out.println("Test 2");
        testStopColon("atggtagttaaataataatag", "atggtagttaaataataatag".length(), 0, "tga");
        System.out.println("Test 3");
        testStopColon("atggtagttaaataataatag", 9, 0, "taa");
    }

    public int findGeneIndex(String dna, int start){
        dna = dna.toUpperCase();
        int minIndex = 0;
		if (start == -1) {
			return 0;
		}
        int taaIndex = findStopColon(dna, start, "TAA");
        int tagIndex = findStopColon(dna, start, "TAG");
        int tgaIndex = findStopColon(dna, start, "TGA");
        if (taaIndex==-1 || (tgaIndex != -1 && tgaIndex <taaIndex)){
            minIndex =tgaIndex;
        }else{
            minIndex=taaIndex;
        }
        if (minIndex==-1 || (tagIndex != -1 && tagIndex <minIndex)){
            minIndex =tagIndex;
        }
        if (minIndex==-1) {return 0;}
        if (minIndex==dna.length()){return 0;}
        return minIndex;        
    }
    
    public String findGene(String dna, int start){
        int stopIndex = findGeneIndex(dna,start);
        if(stopIndex==0){return "";}
        return dna.substring(start,stopIndex); 

    }
    
    public void testFindGene(String input, String expected){
        input=input.toUpperCase();
        int start = input.indexOf("ATG");
        String result = findGene(input,start);
        System.out.println("TEST CASE for: " + input);
        if (expected.equals(result)) {
			System.out.println("success for " + input + " value: " + result);
		}
		else {
			System.out.println("mistake for input: " + input + " got: " + result + " not: " + expected);
		}
    }

    public void testFindGeneVarious(){
        System.out.println("Test 1");
        testFindGene("ctgcgggatggggtttaaataataatagctgba", "ATGGGGTTTAAATAA");
        System.out.println("Test 2");
        testFindGene("ctgcgggatggggtttaattaataataatagctgba", "ATGGGGTTTAATTAA");
        System.out.println("Test 3");
        testFindGene("ctgcgggatggggtttgtaattaataataatagctgba", "");
        System.out.println("Test 4");
        testFindGene("AATGCTAACTAGCTGACTAAT", "");
    }

    public void findManyGene(String input){
        input = input.toUpperCase();
        int counter = 0;
        int startIndex = input.indexOf("ATG");
        int currentIndex = findGeneIndex(input, startIndex);
        if  (currentIndex != 0){
            System.out.println(input.substring(startIndex, currentIndex) + "\n");
            counter++;
            while (currentIndex < input.length() ){
                int stopIndex = findGeneIndex(input, currentIndex);
            if(stopIndex==0){break;
            }else{
                System.out.println(input.substring(currentIndex, stopIndex) + "\n");
                currentIndex=stopIndex;
                counter++;}
            }
            System.out.println("Total found: " + counter);
        }
    }

    public StorageResource getAllGenes1(File f){
        StorageResource geneList = new StorageResource();
        FileResource fr = new FileResource(f);
        int counter = 0;
        for (String word : fr.words()) {
            word = word.toUpperCase();
            int startIndex = word.indexOf("ATG");
            int currentIndex = findGeneIndex(word, startIndex);
            while (currentIndex != 0){
                String currentGene =findGene(word, startIndex);
                if(currentGene.isEmpty()){break;}
                geneList.add(currentGene);
                startIndex = word.indexOf(currentGene, startIndex)+currentGene.length();
                counter++;
            }
        }
        if(counter==0){System.out.println("No genes found");}
        return geneList;
    }
   
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf("ATG");
        System.out.println(startIndex);
        while (true) {
            String currentGene =findGene(dna, startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            geneList.add(currentGene);
            //startIndex = findGeneIndex(dna, startIndex);
            startIndex = dna.indexOf(currentGene, startIndex)+currentGene.length();
        }
        return geneList;
    }
    
    public void testOn(String dna){
        System.out.println("Testing getAllGenes");
        StorageResource genes = getAllGenes(dna);
        int counter=0;
        for (String g : genes.data()) {
            System.out.println(g);
            counter++;
        }
        System.out.println(counter);
    }

    public void testGetAllGenes(File f){
        System.out.println("Testing getAllGenes");
        StorageResource genes = getAllGenes1(f);
        int counter=0;
        for (String g : genes.data()) {
            System.out.println(g);
            counter++;
        }
        System.out.println(counter);
    }

    public static void main(String[] args) {
        StringsSecondAssignments hw = new StringsSecondAssignments();
        //hw.testFindStopColonVarios();
        //hw.testFindGeneVarious();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            //hw.testGetAllGenes(f);
            FileResource fr = new FileResource(f);
            for (String word : fr.words()) {
                hw.findManyGene(word);
                //hw.testOn(word);
            }
        }
    }
}
