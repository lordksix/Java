package earthquake;

import java.util.ArrayList;
import java.util.Collections;

public class DifferentSorters {
    public void sortWithCompareTo() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list,QuakeEntry.MagnitudeAndDepthComparator);
        
        for (int i = 40; i <51; i++) {
            System.out.println(list.get(i));
        }
        int quakeNumber = 50;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }

    public static void main(String[] args) {
        DifferentSorters hw = new DifferentSorters();
        hw.sortWithCompareTo();
    }
}
