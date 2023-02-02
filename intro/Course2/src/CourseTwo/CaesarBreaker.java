package CourseTwo;

import edu.duke.FileResource;

public class CaesarBreaker {
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

    public static int maxIndex(int[] a) {
        int MaxIndex = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > a[MaxIndex]) {
                MaxIndex=i;
            }
        }
        return MaxIndex;
    }

    public int getkey(String message) {
        int[] freqs = countLetter(message);
        int maxIdx = maxIndex(freqs);
        int dkey = maxIdx-4;
        if(maxIdx<4){dkey=26-(4-maxIdx);}
        System.out.println(dkey);
        return dkey;      
    }

    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        return cc.encrypt(encrypted, 26-getkey(encrypted));
    }

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

    public void testDecryptTwoKeys(String message) {
        String decrypted = decryptedTwoKeys(message);
        System.out.println(decrypted);       
    }
}
