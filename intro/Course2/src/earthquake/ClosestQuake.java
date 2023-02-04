package earthquake;

import java.util.ArrayList;

public class ClosestQuake {
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> data, double distMax, Location from) {      
        ArrayList<QuakeEntry> quakeData = new ArrayList<>(data);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    public ArrayList<QuakeEntry>getClosest(ArrayList<QuakeEntry> data, Location from, int howMany) {
        ArrayList<QuakeEntry> quakeData = new ArrayList<>(data);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int k = 0; k < howMany; k++) {            
            int minDist = 0 ;
            for (int i = 0; i < quakeData.size(); i++) {
                QuakeEntry quake = quakeData.get(i);
                if(quake.getLocation().distanceTo(from)<quakeData.get(minDist).getLocation().distanceTo(from))
                    minDist = i;
            }
            answer.add(quakeData.get(minDist));
            quakeData.remove(minDist);
        }
        return answer;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        
        //Durham, NC
        //Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        //ArrayList<QuakeEntry> close = getClosest(list, city, 10);
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }
        System.out.printf("Found %d that match that criteria", close.size());

    }
}
