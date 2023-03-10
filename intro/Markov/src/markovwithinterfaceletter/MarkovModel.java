package markovwithinterfaceletter;

import java.util.ArrayList;

public class MarkovModel extends AbstractMarkovModel{
    private int myOrder;  

    public MarkovModel(int myOrder) {
        this.myOrder = myOrder;
    }

    @Override
    public String getRandomText(int numChars) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-myOrder);
        String key = myText.substring(index, index+myOrder);
        sb.append(key);
        for(int k=0; k < numChars-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size()==0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }         
        return sb.toString();
    }

    @Override
    public String toString() {
        return "MarkovModel of order "+myOrder+ " seed ";
    }
    
}
