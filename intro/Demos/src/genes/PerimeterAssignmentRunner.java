package genes;
import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int numPoints = 0;
        for (Point currPt : s.getPoints()) {
            numPoints++;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        double avglength=getPerimeter(s)/getNumPoints(s);
        return avglength;
    }

    public double getLargestSide(Shape s) {
        double getLargest = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update largest by currDist
           if (currDist>=getLargest) {getLargest=currDist;}
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return getLargest;
    }

    public double getLargestX(Shape s) {
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        double largestX = prevPt.getX();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currX = currPt.getX();
            // Update totalPerim by currDist
            if (currX>=largestX) {largestX=currX;}
            // Update prevPt to be currPt
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPer = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPer = getPerimeter(s);
            if(currPer>largestPer){largestPer=currPer;}
        }
        return largestPer;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        double largestPer = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPer = getPerimeter(s);
            if(currPer>largestPer){largestPer=currPer;temp=f;}
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double avgLength =getAverageLength(s);
        double largestSide =getLargestSide(s);
        double largestX =getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + numPoints);
        System.out.println("avg size = " + avgLength);
        System.out.println("largest size = " + largestSide);
        System.out.println("largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        System.out.println("perimeter = " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("file with largest perimeter is " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);//directory
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        //pr.testFileWithLargestPerimeter();
        pr.printFileNames();
    }
}
