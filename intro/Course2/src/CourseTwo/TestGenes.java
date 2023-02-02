package CourseTwo;

import edu.duke.FileResource;

public class TestGenes {
    public void testCodonCount() {
        CodonCount hw = new CodonCount();
        FileResource fr = new FileResource();
        String dna = fr.asString().trim();
        for (int i = 0; i < 3; i++) {
        hw.buildCodonMap(i, dna);
        System.out.println("---");
        System.out.printf("Most commom %s with %d %n",hw.getMostCommonCodon().get(0),hw.getMyCount().get(hw.getMostCommonCodon().get(0)));
        System.out.printf("Total codons %d, start %d%n",hw.getMyCount().entrySet().size(),i);
        hw.printCodonCounts(6, 6);
        }
        
    }
    public static void main(String[] args) {
        TestGenes hw = new TestGenes();
        hw.testCodonCount();
    }
}
