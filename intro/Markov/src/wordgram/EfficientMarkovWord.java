package wordgram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import markovdriver.IMarkovModel;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> map;
    private int seed; 
    
    public EfficientMarkovWord(int myOrder) {
        myRandom = new Random();
        this.myOrder = myOrder;
        map= new HashMap<>();
    }
    
    public HashMap<WordGram, ArrayList<String>> getMap() {
        return map;
    }

    @Override
    public void setRandom(int seed) {
        myRandom = new Random(seed);
        this.seed = seed;
    }
    @Override
    public void setTraining(String text) {
        myText = text.split("\\s+");     
    }
    private int indexOf(String[] words, WordGram target, int start){
        for (int i = start; i < words.length-myOrder; i++) {
            WordGram word = new WordGram(words,i,myOrder);
            if(word.equals(target)) return i;
        }
        return -1;
    }
    @Override
    public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText,index,myOrder);
        for (int i = 0; i < kGram.length(); i++) {
            sb.append(kGram.wordAt(i));
            sb.append(" ");
        }        
		for(int k=0; k < numWords-myOrder; k++){     
		    ArrayList<String> follows = getFollows(kGram);
		    if (follows.size() == 0) {break;}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			kGram = kGram.shiftAdd(next);
		}
		return sb.toString().trim();
	}

    private ArrayList<String> getFollows(WordGram kGram) {
	    if(map.containsKey(kGram))
            return map.get(kGram);
        buildMap(kGram);
        return map.get(kGram);
    }

    public void buildMap(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<>();
        int pos = 0;
        while(pos<myText.length){
            int start = indexOf(myText,kGram, pos);
            if(start == -1) break;
            if(start + myOrder >= myText.length-1) break;
            String next = myText[start+myOrder];
            follows.add(next);
            pos = start+1;
        }
        map.put(kGram, follows);
    }
    @Override
    public String toString() {
        return "EfficientMarkovModel with wordgram of order "+myOrder+ " seed " +seed;
    }   
}
