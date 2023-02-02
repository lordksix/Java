package CourseTwo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class WordsInFiles {
    private HashMap<String,ArrayList<String>> myCount;

    public HashMap<String,ArrayList<String>> getMyCount() {return myCount;}

    public WordsInFiles() {this.myCount = new HashMap<>();}

    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for(String w : fr.words()){
            ArrayList<String> list = new ArrayList<>();
			w = w.toLowerCase();
            Pattern p = Pattern.compile("[^a-zA-Z]*([a-zA-Z]*.*[a-zA-Z]+)[^a-zA-Z]*");
            Matcher m = p.matcher(w);
            if(m.matches()){
                w=m.group(1);
                if (!myCount.containsKey(w)){
                    list.add(f.getName());
                    myCount.put(w,list);
                }
                else {
                    list=myCount.get(w);
                    if(!list.contains(f.getName())){
                        list.add(f.getName());
                        myCount.put(w,list);
                    }
                }
			}
		}        
    }
    public void buildWordFileMap() {
        myCount.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    public int maxNumber() {
        int max = Collections.max(myCount.entrySet(), (entry1, entry2) -> entry1.getValue().size()- entry2.getValue().size()).getValue().size();
        return max;
    }
    public ArrayList<String> wordsInNumFiles(int number){
        List<String> files = myCount.entrySet().stream().filter(entry -> entry.getValue().size() == number).map(entry -> entry.getKey()).collect(Collectors.toList());
        return new ArrayList<String>(files);
    }

    public void printFilesIn(String word){
        if(getMyCount().containsKey(word))
            getMyCount().get(word).forEach(s -> System.out.println(s));
        else
            System.out.printf("Did not find  %s in the files", word);
    }
}
