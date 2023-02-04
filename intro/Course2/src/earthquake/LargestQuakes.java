package earthquake;

import java.util.ArrayList;

public class LargestQuakes {
    public void findLargest(ArrayList<QuakeEntry> data) {
        getLargest(data, 1);
    }
    public void getLargest(ArrayList<QuakeEntry> data, int howMany) {
        ArrayList<QuakeEntry> quakeData = new ArrayList<>(data);
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        ArrayList<Integer> ansIdx = new ArrayList<>();
        for (int k = 0; k < howMany; k++) {            
            int minDist = 0 ;
            for (int i = 0; i < quakeData.size(); i++) {
                QuakeEntry quake = quakeData.get(i);
                if(quake.getMagnitude()>quakeData.get(minDist).getMagnitude())
                    minDist = i;
            }
            answer.add(quakeData.get(minDist));
            ansIdx.add(minDist);
            quakeData.remove(minDist);
        }
        for (int i = 0; i < answer.size(); i++) {
            System.out.printf("Index: %d. QuakeEntry: %s%n",ansIdx.get(i),answer.get(i));
        }
    }

    public void bigQuakes() {
	    EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> listqk = parser.read(source);
        System.out.println("read data for " + listqk.size() + " quakes");
        getLargest(listqk, 7);
	} 
/*     public static void main(String[] args) {
        LargestQuakes eqc = new LargestQuakes();
        eqc.bigQuakes();
    } */
}
