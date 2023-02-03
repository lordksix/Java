package Cipher;

import edu.duke.*;

public class CaesarCipher {
    
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphaLowerCase = "abcdefghijklmnopqrstuvwxyz";

        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        String shiftedLowerCase = alphaLowerCase.substring(key)+
        alphaLowerCase.substring(0,key);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);

            int idx = alphabet.indexOf(currChar);
            int idx1 = alphaLowerCase.indexOf(currChar);
            //If currChar is in the alphabet
            if(idx != -1){

                char newChar = shiftedAlphabet.charAt(idx);     

                encrypted.setCharAt(i, newChar);               
            }else if(idx1 !=-1){
                char newChar2 = shiftedLowerCase.charAt(idx1);
                encrypted.setCharAt(i, newChar2);
            }
        }
        return encrypted.toString();
    }

    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
        System.out.println("key is " + key + "\n" + encrypted);
    }

    public void testCaesar(int key) {
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
        System.out.println("key is " + key + "\n" + encrypted);
    }

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

    public String encryptedTwoKeys(String msg, int key1, int key2){
        String halfEven = encrypt(halfOfString(msg, 0),key1) ;
        String halfOdd = encrypt(halfOfString(msg, 1),key2) ;
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i <halfEven.length(); i++) {
            encrypted.append(halfEven.charAt(i));
            if(i<halfOdd.length()) encrypted.append(halfOdd.charAt(i));         
        }
        return encrypted.toString();
    }
}
