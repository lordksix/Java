package pasquel.texteditor.textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author Wladimir Pasquel 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText){
		String[] myText = sourceText.split("\\s+");
		starter = myText[0];
		String prevWord = starter;
		for (int i = 1; i < myText.length; i++) {
			ListNode newNode = new ListNode(prevWord);
			if(wordList.contains(newNode)){
				int index = wordList.indexOf(newNode);
				wordList.get(index).addNextWord(myText[i]);
			}else {
				newNode.addNextWord(myText[i]);
				wordList.add(newNode);
			}
			prevWord = myText[i];
		}
		ListNode newNode1 = new ListNode(prevWord);
		if(wordList.contains(newNode1)){
			int index = wordList.indexOf(newNode1);
			wordList.get(index).addNextWord(myText[0]);
		}else {
			newNode1.addNextWord(myText[0]);
			wordList.add(newNode1);
		}
	}

	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		if(wordList.isEmpty()) return "";
		else if(numWords==0) return "";
		else{
	    String currWord = starter;
		StringBuilder output = new StringBuilder(currWord);
		for (int i = 0; i < numWords-1; i++) {
			output.append(" ");
			ListNode newNode = new ListNode(currWord);
			ListNode currNode = wordList.get(wordList.indexOf(newNode));
			currWord = currNode.getRandomNextWord(rnGenerator);
			output.append(currWord);
		}
		return output.toString();}
	}
	
	
	@Override
	public String toString()
	{
		StringBuilder toReturn = new StringBuilder("");
		for (ListNode n : wordList)
		{
			toReturn.append(n.toString());
		}
		return toReturn.toString();
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList = new LinkedList<>();
		train(sourceText);
	}
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString3 = "hi there hi Leo";
		System.out.println(textString3);
		gen.train(textString3);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		System.out.println();

		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.retrain(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		System.out.println();
		String repeat ="I don't know why you say goodbye, I say hello,";
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+ repeat +
				", hello, hello, "+ repeat +". "+"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+ repeat+". "+repeat+", hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+"Oh no. "+"You say goodbye and I say hello, hello, hello. "+
				repeat+", hello, hello, "+"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+"You say stop and I say go, go, go. "+"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		System.out.println();
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		int index = generator.nextInt(nextWords.size());
		return nextWords.get(index);
	}

	public String toString()
	{
		StringBuilder toReturn = new StringBuilder();
		toReturn.append(word + ": ");
		for (String s : nextWords) {
			toReturn.append(s + "->");
		}
		toReturn.append("\n");
		return toReturn.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			{
				return true;}
		if (obj == null)
			{
				return false;}
		if (!(obj instanceof ListNode)){
			return false;}
		ListNode other = (ListNode) obj;
		if (word == null) {
			if (other.word != null){
				return false;}
		} else if (!word.equals(other.word)){
			return false;}
		return true;
	}
	
}


