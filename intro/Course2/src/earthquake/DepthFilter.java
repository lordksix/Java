package earthquake;
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author Wladimir
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    double min;
    double max;
    /**
     * 
     * {@summary for higher than, choose max 100, for lower than, choose min -300000}
     * @author Wladimir
     * @version (a version number or a date)
     */
    public DepthFilter(double min, double max) {this.min = min;this.max = max;}
    public boolean satisfies(QuakeEntry qe) {return qe.getDepth()<=max && qe.getDepth()>=min;}
    @Override
    public String toString() {
        return "DepthFilter [min=" + min + ", max=" + max + "]";
    }
     
}
