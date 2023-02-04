package earthquake;

/**
 * Write a description of class MinMaxFilter here.
 * {@summary for higher than, choose max 20, for lower than, choose min -20}
 * @author Wladimir
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
    private double magMin;
    private double magMax;
    /**
     * 
     * {@summary for higher than, choose max 20, for lower than, choose min -20}
     * @author Wladimir
     * @version (a version number or a date)
     */
    public MagnitudeFilter(double min,double max) {
        magMin = min;
        magMax = max;
    }
    public boolean satisfies(QuakeEntry qe) {return qe.getMagnitude() >= magMin &&qe.getMagnitude() <= magMax;}
    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "MinMagFilter [magMin=" + magMin + ", magMax=" + magMax + "]";
    }
    
     
}
