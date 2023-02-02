package CourseTwo;

public class CeaserCipherOOP {
    private String alphabet;
    private String shiftedAlphabet;
    private int key;

    public CeaserCipherOOP(int key) {
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

    public String encrypt(String input) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        String alphaLowerCase = alphabet.toLowerCase();
        String shiftedLowerCase = shiftedAlphabet.toLowerCase();
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

    public String decrypt(String msg){
        CeaserCipherOOP cc = new CeaserCipherOOP(26-key);
        return cc.encrypt(msg);
    }
}
