package earthquake;

import java.util.ArrayList;

public class QuakeSortInPlace {

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from){
        int minIdx= from;
        for (int i = from+1; i < quakes.size(); i++) {
            if(quakes.get(i).getMagnitude()<quakes.get(minIdx).getMagnitude()){
                minIdx=i;}
        }return minIdx;}

    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
        int minIdx= from;
        for (int i = from+1; i < quakes.size(); i++) {
            if(quakes.get(i).getDepth()>quakes.get(minIdx).getDepth()){
                minIdx=i;}
        }return minIdx;}

    public void sortByMagnitude(ArrayList<QuakeEntry> in){
        int count =0;
        for (int i = 0; i < in.size(); i++) {
            if(checkInSortedOrder(in)) {System.out.println(count);break;}
            int minIdx = getSmallestMagnitude(in, i);
            count++;
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
        }
    }   
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        int count =0;
        for (int i = 0; i<in.size(); i++) {
            if(checkInSortedOrder(in)) {System.out.println(count);break;}
            int minIdx = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
            System.out.println(i);
        }
    } 

    public void onePassBubbleSort(ArrayList<QuakeEntry> in, int numSorted){
            if(in.get(numSorted+1).getMagnitude()<in.get(numSorted).getMagnitude()){
                QuakeEntry qi = in.get(numSorted);
                QuakeEntry qmin = in.get(numSorted+1);
                in.set(numSorted, qmin);
                in.set(numSorted+1, qi);
            }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        for (int i = 0; i < in.size()-1; i++) {
            for (int j = 0; j < in.size()-1-i; j++) {
                onePassBubbleSort(in, j);
            }
        }
    }

    public void onePassBubbleSortDepth(ArrayList<QuakeEntry> in, int numSorted){
        if(in.get(numSorted+1).getDepth()>in.get(numSorted).getDepth()){
            QuakeEntry qi = in.get(numSorted);
            QuakeEntry qmin = in.get(numSorted+1);
            in.set(numSorted, qmin);
            in.set(numSorted+1, qi);
        }
    }

    public void sortByDepthWithBubbleSort(ArrayList<QuakeEntry> in){
        for (int i = 0; i < in.size()-1; i++) {
            for (int j = 0; j < in.size()-1-i; j++) {
                onePassBubbleSortDepth(in, j);
            }
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> in){
        for (int i = 0; i < in.size()-1; i++) {
            if(in.get(i).getMagnitude()>in.get(i+1).getMagnitude())
            {return false;}
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortCheck(ArrayList<QuakeEntry> in){
        for (int i = 0; i < in.size()-1; i++) {
            if(checkInSortedOrder(in)) {System.out.printf("%d%n",i);break;}
            for (int j = 0; j < in.size()-1-i; j++) {
                onePassBubbleSort(in, j);                           
            }
        }
    }

    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        sortByMagnitudeWithBubbleSortCheck(list);
        list.forEach(e->System.out.println(e));
        checkInSortedOrder(list);
    }
    public static void main(String[] args) {
        QuakeSortInPlace hw = new QuakeSortInPlace();
        hw.testSort();
    }
}
