package Cipher;

import java.io.File;
import java.util.HashSet;

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

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String msg = fr.asString();
        int[] key = tryKeyLength(msg, 4, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println(vc.decrypt(msg));
    }

    public void readDictionary() {
        HashSet<String> hs = new HashSet<>();
        FileResource fr = new FileResource();
        for( String line: fr.lines()){
            hs.add(line);
        }
    }
}
