package pasquel.texteditor;

import java.util.Random;

import pasquel.texteditor.document.Document;
import pasquel.texteditor.document.EfficientDocument;
import pasquel.texteditor.spelling.AutoComplete;
import pasquel.texteditor.spelling.AutoCompleteDictionaryTrie;
import pasquel.texteditor.spelling.Dictionary;
import pasquel.texteditor.spelling.DictionaryBST;
import pasquel.texteditor.spelling.DictionaryLoader;
import pasquel.texteditor.spelling.NearbyWords;
import pasquel.texteditor.spelling.SpellingSuggest;
import pasquel.texteditor.spelling.WPTree;
import pasquel.texteditor.spelling.WordPath;
import pasquel.texteditor.textgen.MarkovTextGenerator;
import pasquel.texteditor.textgen.MarkovTextGeneratorLoL;


public class LaunchClass {
	
	public String dictFile = "data/dict.txt";
	
	public LaunchClass() {
		super();
	}
	
	public Document getDocument(String text) {
		// Change this to BasicDocument(text) for week 1 only
		return new EfficientDocument(text);
	}
	
	public MarkovTextGenerator getMTG() {
		return new MarkovTextGeneratorLoL(new Random());
	}
	
	public WordPath getWordPath() {
		return new WPTree();
	}
	
    public AutoComplete getAutoComplete() {
        AutoCompleteDictionaryTrie tr = new AutoCompleteDictionaryTrie();
        DictionaryLoader.loadDictionary(tr, dictFile);
        return tr;
    }
    
    public Dictionary getDictionary() {
        Dictionary d = new DictionaryBST();
        DictionaryLoader.loadDictionary(d, dictFile);
    	return d;
    }
    
    public SpellingSuggest getSpellingSuggest(Dictionary dic) {
    	//return new SpellingSuggestNW(new spelling.NearbyWords(dic));
    	return new NearbyWords(dic);
    
    }
}
