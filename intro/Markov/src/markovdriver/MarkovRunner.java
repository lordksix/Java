package markovdriver;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.FileResource;
import markovwords.MarkovWordOne;

public class MarkovRunner {
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //MarkovZero markov = new MarkovZero();
        MarkovWordOne markov = new MarkovWordOne();
        //MarkovTwo markov = new MarkovTwo();
        //MarkovModel markov = new MarkovModel();
        //String st= "this is a test yes this is a test.";
        markov.setTraining(st);
        markov.setRandom(175);
        for(int k=0; k < 1; k++){
            String text = markov.getRandomText(500);
            printOut(text);
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
    /* public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("th");
        System.out.println(follows.size());
    } */
    public static void main(String[] args) {
        MarkovRunner hw = new MarkovRunner();
        hw.runMarkov();
    }
    
}