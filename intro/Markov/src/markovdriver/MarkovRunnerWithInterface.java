package markovdriver;

import edu.duke.FileResource;
import wordgram.EfficientMarkovWord;
import wordgram.MarkovWord;

public class MarkovRunnerWithInterface {

    public void runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 1; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 70;
		long start = System.nanoTime();
        EfficientMarkovWord markov = new EfficientMarkovWord(3);
        markov.setRandom(371);
        runModel(markov, st, size);
		long elapsedTime = System.nanoTime() - start;
		System.out.println(elapsedTime);

		long start1 = System.nanoTime();
		MarkovWord markov1 = new MarkovWord(3);
		markov1.setRandom(371);
        runModel(markov1, st, size);
		long elapsedTime1 = System.nanoTime() - start1;
		System.out.println(elapsedTime1);
    }
    public static void main(String[] args) {
        MarkovRunnerWithInterface hw = new MarkovRunnerWithInterface();
        hw.runMarkov();
    }
}
