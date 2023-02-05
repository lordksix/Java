package coursetwoapps;

import CourseTwo.WordLengths;


public class CypherApp {
    public static void main(String[] args) {
        WordLengths hw = new WordLengths();
        hw.testCountWordLengths();

       /*  CaesarCipher hw1 = new CaesarCipher();
        System.out.println("Cesar Cypher 1 keys");
        String msg="At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypt = hw1.encrypt(msg, 17);
        System.out.println(encrypt);        
        System.out.println("Cesar Cypher 2 keys");
        msg="Top ncmy qkff vi vguv vbg ycpx";
        System.out.println(hw1.encryptTwoKeys(msg, 24, 6)); */

        /* System.out.println("Cesar Breaker");
        CaesarBreaker hw2 = new CaesarBreaker();
        hw2.testDecrypt(encrypt);
        msg="Akag tjw Xibhr awoa aoee xakex znxag xwko";
        hw2.testDecryptTwoKeys(msg);
        hw2.testDecryptTwoKeys(); */
    } 
}
