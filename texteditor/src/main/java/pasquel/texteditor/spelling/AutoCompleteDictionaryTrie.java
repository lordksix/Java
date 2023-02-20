package pasquel.texteditor.spelling;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie(){
		root = new TrieNode();
		size=0;
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word){
	    word = word.toLowerCase();
		TrieNode temp=root;
		Set<Character> tempSet = temp.getValidNextCharacters();
		for (int i = 0; i < word.length(); i++) {
			if(tempSet.isEmpty() || !temp.getValidNextCharacters().contains(word.charAt(i)))
				temp=temp.insert(word.charAt(i));
			else
				temp=temp.getChild(word.charAt(i));				
		}
		if(temp.endsWord())
			return false;
		else{
			temp.setEndsWord(true);
			size++;
			return true;
		}
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size(){return size;}
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) {
	    s = s.toLowerCase();
		TrieNode temp=root;
		for (int i = 0; i < s.length(); i++) {
			if(temp.getValidNextCharacters().contains(s.charAt(i)))
				temp=temp.getChild(s.charAt(i));
			else
				return false;
		}
		return temp.endsWord();
	}

	/** 
	 * Return a list, in order of increasing (non-decreasing) word length,
	 * containing the numCompletions shortest legal completions 
	 * of the prefix string. All legal completions must be valid words in the 
	 * dictionary. If the prefix itself is a valid word, it is included 
	 * in the list of returned words. 
	 * 
	 * The list of completions must contain 
	 * all of the shortest completions, but when there are ties, it may break 
	 * them in any order. For example, if there the prefix string is "ste" and 
	 * only the words "step", "stem", "stew", "steer" and "steep" are in the 
	 * dictionary, when the user asks for 4 completions, the list must include 
	 * "step", "stem" and "stew", but may include either the word 
	 * "steer" or "steep".
	 * 
	 * If this string prefix is not in the trie, it returns an empty list.
	 * 
	 * @param prefix The text to use at the word stem
	 * @param numCompletions The maximum number of predictions desired.
	 * @return A list containing the up to numCompletions best predictions
	 */@Override
    public List<String> predictCompletions(String prefix, int numCompletions) {
		prefix = prefix.toLowerCase();
		TrieNode temp = root;
		LinkedList<String> result = new LinkedList<>();
		if (numCompletions==0) return result;
		for (int i = 0; i < prefix.length(); i++) {
			if(temp.getValidNextCharacters().contains(prefix.charAt(i)))
				temp=temp.getChild(prefix.charAt(i));
			else
				return result;
		}	
		if(temp.endsWord()){
			result.add(temp.getText());
			if(result.size()==numCompletions)
				return result;
		}
		LinkedList<TrieNode> parNode = new LinkedList<>();
		Set<Character> parSet = temp.getValidNextCharacters();
		for (Character c : parSet) {
			parNode.addLast(temp.getChild(c));
		}			
		while(result.size()<numCompletions){
			for (TrieNode trieNode : parNode) {
				if(trieNode.endsWord()){
					result.addLast(trieNode.getText());
				}
				if(result.size()==numCompletions) return result;
			}
			LinkedList<TrieNode> childNode = new LinkedList<>();
			for (TrieNode node : parNode) {
				Set<Character> parSet1 = node.getValidNextCharacters();
				for (Character c : parSet1) {
					childNode.add(node.getChild(c));
				}			
			}
			parNode = new LinkedList<>(childNode);
			if (childNode.isEmpty()) break;
		}
        return result;
    }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	
	public static void main(String[] args) {

		AutoCompleteDictionaryTrie smallDict = new AutoCompleteDictionaryTrie();
		AutoCompleteDictionaryTrie largeDict = new AutoCompleteDictionaryTrie();
		String dictFile = "data/words.small.txt"; 
		smallDict.addWord("Hello");
		smallDict.addWord("HElLo");
		smallDict.addWord("help");
		smallDict.addWord("he");
		smallDict.addWord("hem");
		smallDict.addWord("hot");
		smallDict.addWord("hey");
		smallDict.addWord("a");
		smallDict.addWord("subsequent");	
		DictionaryLoader.loadDictionary(largeDict, dictFile);
		System.out.println(" Autocomplete");
		List<String> completions = smallDict.predictCompletions("", 0);
		System.out.println(completions);
		completions = smallDict.predictCompletions("",  4);
		System.out.println(completions);
		completions = smallDict.predictCompletions("he", 2);
		System.out.println(completions);
	}
	
}