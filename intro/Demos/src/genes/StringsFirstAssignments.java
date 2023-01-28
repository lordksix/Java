package genes;
import edu.duke.*;

public class StringsFirstAssignments {
    public String findSimpleGene(String dna, String startColon, String stopColon) {
		int start = dna.toUpperCase().indexOf(startColon.toUpperCase());
		if (start == -1) {
			return "";
		}
		int stop = dna.toUpperCase().indexOf(stopColon.toUpperCase(), start+3);
		if ((stop != -1) && (stop - start) % 3 == 0) {
			return dna.substring(start, stop+3);
		}
		else {
			return "";
		}
	}
    public void test(String ap, String a, String startColon, String stopColon){
        String result = findSimpleGene(a,startColon,stopColon);
        System.out.println("TEST CASE for: " + a + " start: " +startColon + " end: " + stopColon);
        if (ap.equals(result)) {
			System.out.println("success for " + ap + " length " + ap.length());
		}
		else {
			System.out.println("mistake for input: " + a);
			System.out.println("got: " + result);
			System.out.println("not: " + ap);
		}
    }
    public void testSimpleGene() {
		String noAtg = "tggggtttaaataataataag";
		String noTaa = "atgcctaag";
		String noNo = "atcgcctaag";
		String nomulti = "cccatggggtttaaatcaataataggagagagagagagagttt";
		String multi = "cccatggggtttaaataataataggagagagagagagagttt";
        String expected = "atggggtttaaataataatag";
        String startColon ="atg";
        String stopColon = "tag";
        test(expected, multi, startColon, stopColon);
        test("", noTaa, startColon, stopColon);
        test("", noAtg, startColon, stopColon);
        test("", noNo, startColon, stopColon);
        test("", nomulti, startColon, stopColon);
    }
    public boolean twoOccurrences(String stringa, String stringb){
        int start = stringb.indexOf(stringa);
        if (start!=-1){
            int start2 = start = stringb.indexOf(stringa,start + stringa.length());
            if (start2!=-1){
                return true;
            }
        }
        return false;
    }
    public void testTwoOcurrences(){
        System.out.println(twoOccurrences("a", "banana"));
    }

    public void hrefFinder(){
        String stringa = "youtube";
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for(String word : ur.words()){
            String word2 = word.toLowerCase();
            int start = word2.indexOf(stringa);
            if (start!=-1){
                int start2 = word.indexOf("\"");
                if (start2!=-1){
                    int end2 =  word.indexOf("\"",start2+1);
                    System.out.println(word.substring(start2+1, end2));
                }
            }
        }
    }


    public static void main(String[] args) {
        StringsFirstAssignments newtest = new StringsFirstAssignments();
        newtest.testSimpleGene();
        //newtest.testTwoOcurrences();;
        //newtest.hrefFinder();
    }
}
