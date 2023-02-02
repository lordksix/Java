package CourseTwo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        for (String word : resource.words()) {
            //String filteredWord = word.replaceFirst("[^a-zA-Z]", "" );
            //System.out.println("replace: "+filteredWord );
            //int wordlength = word.length();
            //if ( ! Character.isLetter(word.charAt(word.length()-1))){wordlength--;}
            //if (Character.isLetter(word.charAt(word.length()-1)) == false) {wordlength--;}
            int len=0;
            Pattern p = Pattern.compile("[^a-zA-Z]*([a-zA-Z]*.*[a-zA-Z]+)[^a-zA-Z]*");
            Matcher m = p.matcher(word);
            if(m.matches()){
                String filteredStr=m.group(1);
                System.out.println(filteredStr);
                //System.out.println("mathces: "+filteredStr);
                len=filteredStr.length();
            }
            if (len >= counts.length) {  	    	   
                len = counts.length - 1;  	  	
           } 
            counts[len]++;
            //System.out.println("length: "+len+" count: "+counts[len]);
        }
    }

    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int [] counts = new int [31];
        countWordLengths(fr,counts);
        for (int i=0; i<counts.length;i++) {
            if(counts[i]>0)
                System.out.printf("%d word of length: %d%n", counts[i], i);
        }    
    }
}


