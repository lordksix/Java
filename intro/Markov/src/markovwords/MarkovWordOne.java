package markovwords;

import java.util.ArrayList;
import java.util.Random;

import markovdriver.IMarkovModel;
/**
* Write a description of class MarkovWordOne here.
* 
* @author Wladimir Pasquel 
* @version (a version number or a date)
*/

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.isEmpty()) {break;}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<>();
        int pos = 0;
        while(pos<myText.length){
            int start = indexOf(myText,key, pos);
            if(start == -1 || start + 1 >= myText.length-1) break;
            String next = myText[start+1];
            follows.add(next);
            pos = start +1;
        }
	    return follows;
    }   

    private int indexOf(String[] words, String target, int start){
        for (int i = start; i < words.length; i++) {
            if(words[i].equals(target)) return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "MarkovWordModel of order 1";
    }
    
}
