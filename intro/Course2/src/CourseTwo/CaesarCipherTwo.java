package CourseTwo;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;
    private int key2;

    public int getKey1() {
        return key1;
    }

    public int getKey2() {
        return key2;
    }

    public CaesarCipherTwo(int key1, int key2) {
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.shiftedAlphabet1 = alphabet.substring(key1)+
        alphabet.substring(0,key1);
        this.shiftedAlphabet2 = alphabet.substring(key2)+
        alphabet.substring(0,key2);
        this.key1 = key1;
        this.key2 = key2;
    }
    
    public void setKey(int key1, int key2) {
        this.shiftedAlphabet1 = alphabet.substring(key1)+
        alphabet.substring(0,key1);
        this.shiftedAlphabet2 = alphabet.substring(key2)+
        alphabet.substring(0,key2);
        this.key1 = key1;
        this.key2 = key2;
    }

    private String halfOfString(String msg, int mode) {
        StringBuilder encrypted = new StringBuilder();
        String alphaLowerCase = alphabet.toLowerCase();
        if(mode==1){
            String shiftedAlphabet = shiftedAlphabet2;
            String shiftedLowerCase = shiftedAlphabet.toLowerCase();
            for(int i=1; i < msg.length(); i=i+2) {
                char currChar = msg.charAt(i);
                int idx = alphabet.indexOf(currChar);
                int idx1 = alphaLowerCase.indexOf(currChar);
                if(idx != -1){
                    char newChar = shiftedAlphabet.charAt(idx); 
                    encrypted.append(newChar);
                }else if(idx1 !=-1){
                    char newChar = shiftedLowerCase.charAt(idx1); 
                    encrypted.append(newChar);}
                else{encrypted.append(currChar);}           
            }
        }else if (mode ==0) {
            String shiftedAlphabet = shiftedAlphabet1;
            String shiftedLowerCase = shiftedAlphabet.toLowerCase();
            for(int i = 0; i < msg.length(); i=i+2) {
                char currChar = msg.charAt(i);
                int idx = alphabet.indexOf(currChar);
                int idx1 = alphaLowerCase.indexOf(currChar);
                if(idx != -1){
                    char newChar = shiftedAlphabet.charAt(idx); 
                    encrypted.append(newChar);
                }else if(idx1 !=-1){
                    char newChar = shiftedLowerCase.charAt(idx1); 
                    encrypted.append(newChar);} 
                else{encrypted.append(currChar);}
            }
        }
        return encrypted.toString();
    }

    public String encrypt(String msg) {
        String halfEven = halfOfString(msg, 0) ;
        String halfOdd = halfOfString(msg, 1) ;
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i <halfEven.length(); i++) {
            encrypted.append(halfEven.charAt(i));
            if(i<halfOdd.length()) encrypted.append(halfOdd.charAt(i));         
        }
        return encrypted.toString();  
    }

    public String decrypt(String msg){
        CaesarCipherTwo cc = new CaesarCipherTwo(26-key1,26-key2);
        return cc.encrypt(msg);
    }
}
