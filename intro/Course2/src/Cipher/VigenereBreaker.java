package Cipher;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import edu.duke.FileResource;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder answer = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i+=totalSlices) {
            answer.append(message.charAt(i));
        }
        return answer.toString();
    }

    public int[] tryKeyLength(String encrypted, int  klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarBreaker cb = new CaesarBreaker();
        for (int i = 0; i < klength; i++) {
            key[i] = cb.getkey(sliceString(encrypted, i, klength));           
        }
        return key;
    }

    public HashMap<String,HashSet<String>> readDictionary() {
        String[] labels ={"Danish","Dutch","English","French","German","Italian","Portuguese","Spanish"};
        HashMap<String,HashSet<String>> hm = new HashMap<>();
        
        for(String s:labels){
            HashSet<String> hs = new HashSet<>();
            FileResource fr = new FileResource("dictionaries/"+s);
            for( String line: fr.lines()){
                hs.add(line.toLowerCase());
            }
            hm.put(s, hs);
        }
        return hm;
    }

    public int countWords(String message, HashSet<String> dictionary) {
       String[] msg = message.split("\\W+");
       int count = 0;
       for(String word: msg){
            if (dictionary.contains(word)) count++;
       }
       return count;        
    }

    public char mostCommonCharin(HashSet<String> words) {
        HashMap<Character,Integer> count = new HashMap<>();
        words.forEach(word->{
            for(int i = 0; i < word.length(); i++){
                char currChar = word.charAt(i);
                if(!count.containsKey(currChar))
                    count.put(currChar, 1);
                else count.put(currChar,count.get(currChar)+1);

            }
        });
        int max = Collections.max(count.values());
        List<Character> list = count.entrySet().stream().filter(entry -> entry.getValue() == max).map(entry -> entry.getKey()).collect(Collectors.toList());
        return list.get(0);
    }

    public void breakForLanguage(String encrypted, HashMap<String,HashSet<String>> dictionary) {
        String result;
        double ratio = 0.0;
        String[] totalWords = encrypted.split("\\W+");
        for(String dic: dictionary.keySet()){
            char c =mostCommonCharin(dictionary.get(dic));
            for (int i = 1; i < 100; i++) {
                int[] key = tryKeyLength(encrypted, i, c);
                VigenereCipher vc = new VigenereCipher(key);
                result = vc.decrypt(encrypted);
                int max = countWords(result.toLowerCase(), dictionary.get(dic));
                ratio = ((double) max)/totalWords.length;
                if(ratio>0.75){
                    System.out.println(result.substring(0,200));
                    System.out.printf("Language: %s, commom char: %c, slices: %d, words found: %d, total words: %d, ratio: %s%n",dic,c,i,max,totalWords.length,ratio);   
                    break;
                } 
            }
        }       
    }

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String msg = fr.asString();
        HashMap<String,HashSet<String>> dictionary = readDictionary();
        breakForLanguage(msg,dictionary); 
    }
}
