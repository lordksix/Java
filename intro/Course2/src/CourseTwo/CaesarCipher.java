package CourseTwo;
import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key, String mode) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphaLowerCase = "abcdefghijklmnopqrstuvwxyz";

        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        String shiftedLowerCase = alphaLowerCase.substring(key)+
        alphaLowerCase.substring(0,key);
        if(mode=="odd"){
            for(int i = 0; (2*i+1) < encrypted.length(); i++) {
                int index = 2*i+1;
                char currChar = encrypted.charAt(index);

                int idx = alphabet.indexOf(currChar);
                int idx1 = alphaLowerCase.indexOf(currChar);
                //If currChar is in the alphabet
                if(idx != -1){

                    char newChar = shiftedAlphabet.charAt(idx);     

                    encrypted.setCharAt(index, newChar);               
                }else if(idx1 !=-1){
                    char newChar2 = shiftedLowerCase.charAt(idx1);
                    encrypted.setCharAt(index, newChar2);
                }
            }
        }else if (mode =="even") {
            for(int i = 0; 2*i < encrypted.length(); i++) {
                int index = 2*i;
                char currChar = encrypted.charAt(index);

                int idx = alphabet.indexOf(currChar);
                int idx1 = alphaLowerCase.indexOf(currChar);
                //If currChar is in the alphabet
                if(idx != -1){

                    char newChar = shiftedAlphabet.charAt(idx);     

                    encrypted.setCharAt(index, newChar);               
                }else if(idx1 !=-1){
                    char newChar2 = shiftedLowerCase.charAt(idx1);
                    encrypted.setCharAt(index, newChar2);
                }
            }
        } else{
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
        }
        return encrypted.toString();
    }


    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key, "");
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key,"");
        System.out.println(decrypted);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        String encrypted = encrypt(input, key1, "even");
        String encrypted2 = encrypt(encrypted, key2, "odd");
        return encrypted2;
    }

    public static void main(String[] args) {
        CaesarCipher hw = new CaesarCipher();
        System.out.println(hw.encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }
}
