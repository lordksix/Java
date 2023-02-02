package CourseTwo;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;

public class TesterWebLogs {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer hw = new LogAnalyzer();
        hw.readFile();
        hw.printAll();
    }

    public void testUniqueIP(){
        LogAnalyzer hw = new LogAnalyzer();
        hw.readFile();
        int uniqueIPs = hw.countUniqueIps();
        System.out.printf("There are %d unique IP.%n",uniqueIPs);
        //hw.printAllHigherThanNum(400);
        //System.out.printf("Unique IP on %s:%n", "Mar 17");
        //hw.uniqueIPVisitsOnDay("Sep 14").forEach(e -> System.out.println(e));
        System.out.printf("Unique IP on %s are %d.%n", "Mar 17",hw.uniqueIPVisitsOnDay("Mar 17").size());
        hw.countUniqueIPsInRange(200, 299);
        hw.countUniqueIPsInRange(300, 399);
    }

    public void countTester(){
        LogAnalyzer hw = new LogAnalyzer();
        hw.readFile();
        //HashMap<String,Integer> counts = hw.countVisitsPerIps();
        //System.out.println(counts);
        //System.out.println(hw.mostNumberVisitsByIP(counts));
        //System.out.println(hw.iPsMostVisits(counts));
        HashMap<String, ArrayList<String>> maps = hw.iPsForDays();
        maps.forEach((k,v)->System.out.printf("%s has %d IPs.%n",k,v.size()));
        System.out.println(hw.iPsWithMostVisitsOnDay(hw.iPsForDays(),"Mar 17"));
        System.out.println(hw.dayWithMostIPVisits(maps));
    }

    public static void main(String[] args) {
        TesterWebLogs hw = new TesterWebLogs();
        hw.countTester();
    }
}
