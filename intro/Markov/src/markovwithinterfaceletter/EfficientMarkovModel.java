package markovwithinterfaceletter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int myOrder;
    private HashMap<String,ArrayList<String>> map;  

    public EfficientMarkovModel(int myOrder) {
        this.myOrder = myOrder;
        map = new HashMap<>();
    }
    public void buildMap(String key) {
        ArrayList<String> follows = new ArrayList<>();
        int pos = 0;
        while(pos<myText.length()){
            int start = myText.indexOf(key, pos);
            if(start == -1) break;
            if(start +key.length() >= myText.length()-1) break;
            String next = myText.substring(start +key.length(),start +key.length()+1);
            follows.add(next);
            pos = start + key.length();
        }
        map.put(key, follows);
    }
    @Override
    protected ArrayList<String> getFollows(String key) {
        if(map.containsKey(key)) return map.get(key);
        buildMap(key);
        return map.get(key);
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
    
    public void printHashMapInfo() {
        System.out.printf("It has %d keys in the HashMap%n",map.size());
        ArrayList<Integer> sizes = new ArrayList<>();
        for (ArrayList<String> values : map.values()) {sizes.add(values.size());}
        int max = Collections.max(sizes);
        List<String> keyMax = map.entrySet().stream().filter(entry -> entry.getValue().size() == max).map(entry -> entry.getKey()).collect(Collectors.toList());
        System.out.printf("Size of the biggest key %s is %d%n",keyMax.get(0),max);
    }
    
    @Override
    public String toString() {
        return "EfficientMarkovModel of order "+myOrder;
    }
}
