package markovwithinterfaceletter;

import java.util.ArrayList;

public class MarkovFour extends AbstractMarkovModel{

    @Override
    public String getRandomText(int numChars) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index, index+4);
        sb.append(key);
        for(int k=0; k < numChars-4; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size()==0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }         
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "MarkovModel of order 4";
    }
}
