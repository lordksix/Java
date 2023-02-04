package earthquake;

import java.util.ArrayList;

public class EarthQuakeClient {

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, MatchAllFilter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { answer.add(qe);}} 
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

    public void dumpCSV(ArrayList<QuakeEntry> list){
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
	
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        
        //Durham, NC
        //Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = getClosest(list, city, 10);
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }
        System.out.printf("Found %d that match that criteria", close.size());

    }

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        Location Tulsa = new Location(36.1314, -95.9372);
        MatchAllFilter maf = new MatchAllFilter();
        //maf.addFilter(new DepthFilter(-100000, -10000));
        maf.addFilter(new MagnitudeFilter(0.0,3.0));
        maf.addFilter(new PhraseFilter("any", "Ca"));
        maf.addFilter(new DistanceFilter(10000000, Tulsa));
        ArrayList<QuakeEntry> m7  = filter(list, maf); 
        for (QuakeEntry qe: m7) {System.out.println(qe);}
        System.out.printf("Found %d that match that criteria.%n", m7.size());
        System.out.println(maf);
    }
/* 
    public static void main(String[] args) {
        EarthQuakeClient eqc = new EarthQuakeClient();
        eqc.quakesWithFilter();
    } */
}
