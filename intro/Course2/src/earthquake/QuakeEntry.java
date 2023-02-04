package earthquake;

import java.util.Comparator;

public class QuakeEntry implements Comparable<QuakeEntry>{
	
	private Location myLocation;
	private String title;
	private double depth;
	private double magnitude;

	public QuakeEntry(double lat, double lon, double mag,String t, double d) {
		myLocation = new Location(lat,lon);		
		magnitude = mag;
		title = t;
		depth = d;
	}
	
	public Location getLocation(){return myLocation;}
	
	public double getMagnitude(){return magnitude;}
	
	public String getInfo(){return title;}
	
	public double getDepth(){return depth;}

	@Override
	public int compareTo(QuakeEntry loc) {
		return Double.compare(magnitude, loc.getMagnitude());
		/* double difflat = myLocation.getLatitude() - loc.myLocation.getLatitude();
		if (Math.abs(difflat) < 0.001) {
			double diff = myLocation.getLongitude() - loc.myLocation.getLongitude();
			if (diff < 0) return -1;
			if (diff > 0) return 1;
			return 0;
		}
		if (difflat < 0) return -1;
		if (difflat > 0) return 1;
		
		// never reached
		return 0; */
	}

	/**
     * the compare method that has two parameters, a QuakeEntry named q1 and a QuakeEntry named q2. 
	 * This method should compare the title of q1 and q2. If q1’s title comes before q2’s title in 
	 * alphabetical order, then this method should return a negative integer. If q1’s title comes 
	 * after q2’s title, then this method should return a positive integer. If q1’s title is the same 
	 * as q2’s title, then this method should compare the depth of the two earthquakes. If q1’s depth 
	 * is less than q2’s depth, then this method should return a negative number. If q1’s depth is 
	 * greater than q2’s depth, then this method should return a positive integer. Otherwise, this 
	 * method should return 0. 
     */
    public static Comparator<QuakeEntry> TitleAndDepthComparator = new Comparator<QuakeEntry>() {
        @Override
        public int compare(QuakeEntry e1, QuakeEntry e2) {
            int flag = e1.getInfo().compareTo(e2.getInfo());
			if(flag==0) flag = Double.compare(e1.getDepth(),e2.getDepth());
        	return flag;
        }
    };
	/**
	 * In this class you should write the compare method that has two parameters, a QuakeEntry named 
	 * q1 and a QuakeEntry named q2. This method should compare the last word in the title of q1 and q2. 
	 * If q1’s last word comes before q2’s last word in alphabetical order, then this method should return 
	 * a negative integer. If q1’s last word comes after q2’s last word, then this method should return a 
	 * positive integer. If q1’s last word is the same as q2’s last word, then this method should compare 
	 * the magnitude of the two earthquakes. If q1’s magnitude is less than q2’s magnitude, then this method 
	 * should return a negative number. If q1’s magnitude is greater than q2’s magnitude, then this method 
	 * should return a positive integer. Otherwise, this method should return 0.
	 *
     */

	public static Comparator<QuakeEntry> TitleLastAndMagnitudeComparator = new Comparator<QuakeEntry>() {
		@Override
		public int compare(QuakeEntry e1, QuakeEntry e2) {
			int flag = e1.getInfo().split("\\s")[e1.getInfo().split("\\s").length-1].compareTo(e2.getInfo().split("\\s")[e2.getInfo().split("\\s").length-1]);
			if(flag==0) flag = Double.compare(e1.getMagnitude(),e2.getMagnitude());
        	return flag;
		}
	};
	
	/**
     * the compare method that has two parameters, a QuakeEntry named q1 and a QuakeEntry named q2. 
	 * This method should compare the title of q1 and q2. If q1’s title comes before q2’s title in 
	 * alphabetical order, then this method should return a negative integer. If q1’s title comes 
	 * after q2’s title, then this method should return a positive integer. If q1’s title is the same 
	 * as q2’s title, then this method should compare the depth of the two earthquakes. If q1’s depth 
	 * is less than q2’s depth, then this method should return a negative number. If q1’s depth is 
	 * greater than q2’s depth, then this method should return a positive integer. Otherwise, this 
	 * method should return 0. 
     */
    public static Comparator<QuakeEntry> MagnitudeAndDepthComparator = new Comparator<QuakeEntry>() {
        @Override
        public int compare(QuakeEntry e1, QuakeEntry e2) {
            int flag = Double.compare(e1.getMagnitude(),e2.getMagnitude());
			if(flag==0) flag = Double.compare(e1.getDepth(),e2.getDepth());
        	return flag;
        }
    };

	public String toString(){
		return String.format("(%3.2f, %3.2f), mag = %3.2f, depth = %3.2f, title = %s", myLocation.getLatitude(),myLocation.getLongitude(),magnitude,depth,title);
	}
	
}
