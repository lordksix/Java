package earthquake;

public class DistanceFilter implements Filter{
    private double distMax;
    private Location from;
    /** 
     * {@summary from is the coordinates of with class location, distance is in meter}
     */
    public DistanceFilter(double distMax, Location from) {this.distMax = distMax;this.from=from;}
    public boolean satisfies(QuakeEntry qe) {return qe.getLocation().distanceTo(from) <= distMax;}
    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "distanceFromFilter [distMax=" + distMax + ", from=" + from + "]";
    }
    
}
