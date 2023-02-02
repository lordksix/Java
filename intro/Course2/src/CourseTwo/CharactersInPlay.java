package CourseTwo;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.duke.FileResource;

public class CharactersInPlay {
    private ArrayList<String> myChars;
    private ArrayList<Integer> myFreqs;
    
    public ArrayList<String> getMyChars() {
        return myChars;
    }
    public ArrayList<Integer> getMyFreqs() {
        return myFreqs;
    }
    public CharactersInPlay() {
        myChars = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    public void update(String person) {
        int index = myChars.indexOf(person);
        if (index == -1){
            myChars.add(person);
            myFreqs.add(1);
        }
        else {
            int freq = myFreqs.get(index);
            myFreqs.set(index,freq+1);
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

    public void findAllCharacters() {
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            if(line.length()>2){
                line =line.substring(2);
                //System.out.println(line);
                //System.out.println(line.split("[.]",2)[0]);
                Pattern p = Pattern.compile("[A-Z]{2,}[A-Z\s]*");
                Matcher m = p.matcher(line.split("[.]",2)[0]);
                if(m.matches()){
                    String word =m.group(0);
                    update(word);
                }
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2) {
        System.out.printf("Characteres with more than %d but less than %d dialogues:%n",num1,num2);
        for (int i = 0; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) >= num1 && myFreqs.get(i) <= num2){
                System.out.printf("%d time word %s%n",getMyFreqs().get(i),getMyChars().get(i));
            }
        }
    }
}
