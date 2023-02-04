package earthquake;

import java.util.ArrayList;

public class QuakeSortWithTwoArrayLists {

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> in){
        for (int i = 0; i < in.size()-1; i++) {
            if(in.get(i).getMagnitude()>in.get(i+1).getMagnitude())
            {return false;}
        }
        return true;
    }
    public QuakeEntry getSmallestmagnitude (ArrayList<QuakeEntry> quakes){
        QuakeEntry min = quakes.get(0);
        for (QuakeEntry q : quakes) {
            if(q.getMagnitude()<min.getMagnitude()) min=q;
        }return min;
    }
    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in){
        ArrayList<QuakeEntry> out = new ArrayList<>();
        int count = 0;
        while(!in.isEmpty()){
            if(checkInSortedOrder(in)) break;
                System.out.printf("%d%n",count);
            QuakeEntry minElement = getSmallestmagnitude(in);
            count++;
            in.remove(minElement);
            out.add(minElement);
        } return out;
    }

    public QuakeEntry getSmallestDepth(ArrayList<QuakeEntry> quakes){
        QuakeEntry min = quakes.get(0);
        for (QuakeEntry q : quakes) {
            if(q.getDepth()>min.getDepth()) min=q;
        }return min;
    }
    public ArrayList<QuakeEntry> sortByDepth(ArrayList<QuakeEntry> in){
        ArrayList<QuakeEntry> out = new ArrayList<>();
        int count = 0;
        while(!in.isEmpty()){
            if(checkInSortedOrder(in)) break;
                System.out.printf("%d%n",count);
            QuakeEntry minElement = getSmallestmagnitude(in);
            count++;
            in.remove(minElement);
            out.add(minElement);
        } return out;
    }




    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        list = sortByMagnitude(list);
        list.forEach(e->System.out.println(e));
    }
    public static void main(String[] args) {
        QuakeSortWithTwoArrayLists hw = new QuakeSortWithTwoArrayLists();
        hw.testSort();
    }
}
