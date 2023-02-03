package Cipher;

public class CaesarCipherOOP {
    private String alphabet;
    private String shiftedAlphabet;
    private int key;

    public CaesarCipherOOP(int key) {
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        this.key=key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        this.key = key;
    }

    private char transformLetter(char c, String from, String to) {
        String fromLowerCase = from.toLowerCase();
        String toLowerCase = to.toLowerCase();
        int idx = from.indexOf(c);
        int idx1 = fromLowerCase.indexOf(c);
        if (idx != -1) {
            return to.charAt(idx);
        }else if(idx1 !=-1){
            return toLowerCase.charAt(idx1);
        }
        return c;
    }

    private String transform(String input, String from, String to){
        StringBuilder encrypted = new StringBuilder(input);
        String fromLowerCase = from.toLowerCase();
        String toLowerCase = to.toLowerCase();
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = from.indexOf(currChar);
            int idx1 = fromLowerCase.indexOf(currChar);
            //If currChar is in the alphabet
            if(idx != -1){
                char newChar = to.charAt(idx);     
                encrypted.setCharAt(i, newChar);               
            }else if(idx1 !=-1){
                char newChar = toLowerCase.charAt(idx1);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    public char encryptLetter(char c) {
        return transformLetter(c, alphabet, shiftedAlphabet);
    }

    public char decryptLetter(char c) {
        return transformLetter(c, shiftedAlphabet, alphabet);
    }

    public String encrypt(String input) {
        return transform(input,alphabet, shiftedAlphabet);
        
    }

    public String decrypt(String input){
        return transform(input,shiftedAlphabet,alphabet);
    }

    public String toString() {
        return "" + key;
    }
}
