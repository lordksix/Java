package CourseTwo;

public class WordPlay {
    public boolean isVowel(Character ch){
        boolean result = ch.equals('a')||ch.equals('e')||ch.equals('i')||ch.equals('o')||ch.equals('u');
        result = result||ch.equals('A')||ch.equals('E')||ch.equals('I')||ch.equals('O')||ch.equals('U');
        return result;
    }

    public String replaceVowels(String phrase, Character ch){
        StringBuilder newPhrase = new StringBuilder(phrase);
        ch = Character.toLowerCase(ch);
        for (int i = 0; i < newPhrase.length(); i++) {
            Character currChar = newPhrase.charAt(i);
            if(isVowel(currChar)){newPhrase.setCharAt(i,ch);}
        }
        return newPhrase.toString();
    }

    public String emphasize( String phrase, Character ch){
        StringBuilder newPhrase = new StringBuilder(phrase);
        for (int i = 0; i < newPhrase.length(); i++){
            Character currChar = newPhrase.charAt(i);
            if(currChar.equals(ch)){
                if(i%2==0){newPhrase.setCharAt(i,'*');}else{newPhrase.setCharAt(i,'+');}}
        }
        return newPhrase.toString();
    }


    public static void main(String[] args) {
        WordPlay hw = new WordPlay();
        System.out.println(hw.emphasize("dna ctgaaactga", 'a'));
    }
}
