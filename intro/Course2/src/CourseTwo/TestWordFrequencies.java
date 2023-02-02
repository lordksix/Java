package CourseTwo;

import java.util.ArrayList;

public class TestWordFrequencies {

    public void tester(){
        WordFrequencies wf = new WordFrequencies();
        wf.findUnique();
        System.out.println("# unique words: "+wf.getMyWords().size());
         int index = wf.findMax();
        System.out.println("max word/freq: "+wf.getMyWords().get(index)+" "+wf.getMyFreqs().get(index));
        /* for (int i = 0; i < wf.getMyWords().size(); i++) {
            System.out.printf("%d time word %s%n",wf.getMyFreqs().get(i),wf.getMyWords().get(i));
        } */
    }
    public void testerChar(){
        CharactersInPlay cp = new CharactersInPlay();
        cp.findAllCharacters();
        System.out.println("# unique characters: "+cp.getMyChars().size());
        int index = cp.findMax();
        System.out.println("max word/freq: "+cp.getMyChars().get(index)+" "+cp.getMyFreqs().get(index));
        /* for (int i = 0; i < cp.getMyChars().size(); i++) {
            System.out.printf("%d time word %s%n",cp.getMyFreqs().get(i),cp.getMyChars().get(i));
        }*/
        cp.charactersWithNumParts(10, 15); 
    }

    public void testWordsInFiles(){
        WordsInFiles wf = new WordsInFiles();
        wf.buildWordFileMap();
        //System.out.println(wf.getMyCount().entrySet().size());
        ArrayList<String> words = wf.wordsInNumFiles(5);
        System.out.println(words.size());
        ArrayList<String> words1 = wf.wordsInNumFiles(4);
        System.out.println(words1.size());
    }

    public void testGladLibMap(){
        GladLibMap hw =new GladLibMap();
        hw.makeStory();
		hw.totalWordsConsidered();
    }
    public static void main(String[] args) {
        TestWordFrequencies wf = new TestWordFrequencies();
        wf.testWordsInFiles();
    }
}
