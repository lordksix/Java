package CourseTwo;

import edu.duke.FileResource;

public class TestCaesarCipher {
    private int[] countLetter(String message) {
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

    private static int maxIndex(int[] a) {
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
        return dkey;      
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

    public String breakCaesarCipherOneKey(String input) {
        CeaserCipherOOP cc = new CeaserCipherOOP(26-getkey(input));
        return cc.encrypt(input);
    }

    public void simpleTestsOneKey() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CeaserCipherOOP cc = new CeaserCipherOOP(18);
        String encrypted = cc.encrypt(message);
        System.out.println(encrypted);
        System.out.println("Finished");
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
        System.out.println("Finished");
        String decrypted2 = breakCaesarCipherOneKey(encrypted);
        System.out.println(decrypted2);
        System.out.println("Finished");
    }

    public String breakCaesarCipherTwoKeys(String input) {
        String halfEven = halfOfString(input, 0) ;
        String halfOdd = halfOfString(input, 1) ;
        CeaserCipherOOP cc = new CeaserCipherOOP(26-getkey(halfEven));
        CeaserCipherOOP cc1 = new CeaserCipherOOP(26-getkey(halfOdd));
        String dectryptedE = cc.encrypt(halfEven);
        String dectryptedO = cc1.encrypt(halfOdd);
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i <dectryptedE.length(); i++) {
            decrypted.append(dectryptedE.charAt(i));
            if(i<dectryptedO.length()) decrypted.append(dectryptedO.charAt(i));         
        }
        return decrypted.toString();
    }

    public void simpleTestsTwoKeys() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
        String encrypted = cc.encrypt(message);
        System.out.println(encrypted);
        System.out.println("Finished");
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
        System.out.println("Finished");
        String decrypted2 = breakCaesarCipherTwoKeys(encrypted);
        System.out.println(decrypted2);
        System.out.println("Finished");
    }

    public static void main(String[] args) {
        TestCaesarCipher hw = new TestCaesarCipher();
        hw.simpleTestsOneKey();
        hw.simpleTestsTwoKeys();
    }

}
