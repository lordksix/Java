package wordgram;
public class WordGram {
    private String[] myWords;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){return myWords.length;}

    public String toString(){
        String ret = "";
        for (int i = 0; i < myWords.length; i++) {
            ret+=myWords[i]+" ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(length()!=other.length())return false;
        for (int i = 0; i < myWords.length; i++) {
            if(!myWords[i].equals(other.wordAt(i))) return false;
        }
        return true;
    }

    public WordGram shiftAdd(String word) {
        String[] words = new String[myWords.length];
        for (int i = 0; i < words.length-1; i++) {
            words[i]=myWords[i+1];
        }
        words[words.length-1]= word;
        WordGram out = new WordGram(words, 0, words.length);
        return out;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

}
