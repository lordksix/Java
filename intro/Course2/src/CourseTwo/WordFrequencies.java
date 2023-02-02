package CourseTwo;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.duke.FileResource;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public ArrayList<String> getMyWords() {
        return myWords;
    }

    public ArrayList<Integer> getMyFreqs() {
        return myFreqs;
    }

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique(){
        FileResource resource = new FileResource();
        myWords.clear();
        myFreqs.clear();
        for(String word : resource.words()){
            Pattern p = Pattern.compile("[^a-zA-Z]*([a-zA-Z]*.*[a-zA-Z]+)[^a-zA-Z]*");
            Matcher m = p.matcher(word);
            if(m.matches()){
                word =m.group(0).toLowerCase();
                int index = myWords.indexOf(word);
                if (index == -1){
                    myWords.add(word);
                    myFreqs.add(1);
                }
                else {
                    int freq = myFreqs.get(index);
                    myFreqs.set(index,freq+1);
                }
            }
        }
    }

    public int findMax(){
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) > max){
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    
}
