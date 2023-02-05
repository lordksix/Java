package wordgram;

import java.util.ArrayList;
import java.util.Random;

import markovdriver.IMarkovModel;

public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int myOrder) {
        myRandom = new Random();
        this.myOrder = myOrder;
    }
    @Override
    public void setRandom(int seed) {
        myRandom = new Random(seed);
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
    private ArrayList<String> getFollows(WordGram kGram) {
	    ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos<myText.length){
            int start = indexOf(myText,kGram, pos);
            if(start == -1) break;
            if(start + myOrder >= myText.length-1) break;
            String next = myText[start+myOrder];
            follows.add(next);
            pos = start+1;
        }
	    return follows;
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
    @Override
    public String toString() {
        return "MarkovModel with wordgram of order "+myOrder+ " seed ";
    }
}
