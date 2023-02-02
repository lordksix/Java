package CourseTwo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CodonCount {
    private HashMap<String,Integer> myCount;
    

    public CodonCount() {
        this.myCount = new HashMap<>();
    }

    public HashMap<String, Integer> getMyCount() {
        return myCount;
    }
    
    public void buildCodonMap(int start, String dna) {
        myCount.clear();
        dna = dna.toUpperCase();
        for (int i = start; i < dna.length(); i+=3) {
            if(i+3<dna.length()){
                String codon = dna.substring(i,i+3);
                if(!(myCount.containsKey(codon))) myCount.put(codon, 1);
                else  myCount.put(codon,myCount.get(codon)+1);
            }           
        }
    }

    public List<String> getMostCommonCodon() {
        int max = Collections.max(myCount.values());
        //String codons = myCount.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
        List<String> codons1 = myCount.entrySet().stream().filter(entry -> entry.getValue() == max).map(entry -> entry.getKey()).collect(Collectors.toList());
        return codons1;
    }

    public void printCodonCounts(int start, int end) {
        List<String> codons1 = myCount.entrySet().stream().filter(entry -> ((start<=entry.getValue()) && (entry.getValue()<=end))).map(entry -> entry.getKey()).collect(Collectors.toList());
        codons1.forEach(codon->System.out.printf("%s has %d repetions%n",codon,myCount.get(codon)));
    }
}
