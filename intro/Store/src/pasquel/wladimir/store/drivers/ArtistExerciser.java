package pasquel.wladimir.store.drivers;

import java.util.TreeSet;
import static java.lang.System.out;
import pasquel.wladimir.store.Artist;

public class ArtistExerciser {
    public static void main(String[] args) {
        Artist hotPlate = new Artist();
        hotPlate.setName("Hot Plate");

        TreeSet<String> instrumets1 = new TreeSet<>();
        instrumets1.add("Piano");
        instrumets1.add("Clarinet");
        instrumets1.add("Hurdy Gurdy");
        instrumets1.add("Tuba");
        hotPlate.addMember("Tom", instrumets1);

        TreeSet<String> instrumets2 = new TreeSet<>();
        instrumets2.add("Guitar");
        instrumets2.add("Saxophone");
        instrumets2.add("Brass Drums");
        hotPlate.addMember("Steve", instrumets2);

        printMemberInstruments(hotPlate, "Tom");
        printMemberInstruments(hotPlate, "Steve");
    }

    public static void printMemberInstruments(Artist artist, String memberName) {
        out.println(artist.getName() + " band member " + " plays : ");
        artist.getInstruments(memberName).forEach(e->out.println('\t' + e));
    }
}
