package Cipher;

import edu.duke.FileResource;

public class CaesarBreaker {
    char mostCommon;
    
    public CaesarBreaker() {
        mostCommon = 'e';
    }
    
    public CaesarBreaker(char c) {
        mostCommon = c;
    }

    
    /** 
     * @param message
     * @return int[]
     */
    public int[] countLetter(String message) {
        String alphaLowerCase = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int index =alphaLowerCase.indexOf(ch);
            if(index!=-1)
                counts[index]++;
        }
        return counts;
    }

    
    /** 
     * @param a
     * @return int
     */
    public static int maxIndex(int[] a) {
        int MaxIndex = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > a[MaxIndex]) {
                MaxIndex=i;
            }
        }
        return MaxIndex;
    }

    
    /** 
     * @param message
     * @return int
     */
    public int getkey(String message) {
        int[] freqs = countLetter(message);
        int maxIdx = maxIndex(freqs);
        int mostCommonPos = mostCommon - 'a';
        int dkey = maxIdx-mostCommonPos;
        if(maxIdx<mostCommonPos){dkey=26-(mostCommonPos-maxIdx);}
        return dkey;      
    }

    
    /** 
     * @param encrypted
     * @return String
     */
    public String decrypt(String encrypted) {
        int key = getkey(encrypted);
        CaesarCipherOOP cc = new CaesarCipherOOP(key);
        return cc.decrypt(encrypted);
    }

    
    /** 
     * @param message
     */
    public void testDecrypt(String message) {
        String decrypted = decrypt(message);
        System.out.println(decrypted);       
    }

    public void testDecrypt() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        String decrypted = decrypt(message);
        System.out.println(decrypted);       
    }

    
    /** 
     * @param msg
     * @param mode
     * @return String
     */
    public String halfOfString(String msg, int mode) {
        StringBuilder half = new StringBuilder();
        if(mode==1){
            for(int i=1; i < msg.length(); i=i+2) {
                char currChar = msg.charAt(i);
                half.append(currChar);               
            }
        }else if (mode ==0) {
            for(int i = 0; i < msg.length(); i=i+2) {
                char currChar = msg.charAt(i);
                half.append(currChar);
            }
        }
        return half.toString();
    }

    
    /** 
     * @param encrypted
     * @return String
     */
    public String decryptedTwoKeys(String encrypted){
        String halfEven = decrypt(halfOfString(encrypted, 0)) ;
        String halfOdd = decrypt(halfOfString(encrypted, 1)) ;
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i <halfEven.length(); i++) {
            decrypted.append(halfEven.charAt(i));
            if(i<halfOdd.length()) decrypted.append(halfOdd.charAt(i));         
        }
        return decrypted.toString();
    }


    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        String decrypted = decryptedTwoKeys(message);
        System.out.println(decrypted);       
    }

    
    /** 
     * @param message
     */
    public void testDecryptTwoKeys(String message) {
        String decrypted = decryptedTwoKeys(message);
        System.out.println(decrypted);       
    }
}
