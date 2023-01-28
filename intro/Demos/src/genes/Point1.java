package genes;
public class Point1 {
    private int x;
    private int y;
    public Point1(int startx, int starty){
        x = startx;
        y = starty;
    }
    public int getX(){ return x;}
    public int getY(){ return y;}
    public double distance(Point1 otherPt){
        int dx= x -otherPt.getX();
        int dy= y-otherPt.getY();
        return Math.sqrt(dx*dx +dy*dy);
    }
    public static void main(String[] args) {
        Point1 p1 = new Point1(3, 4);
        Point1 p2 = new Point1(6, 8);
        System.out.println(p1.distance(p2));
    }
}
