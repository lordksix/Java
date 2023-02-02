package CourseTwo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import edu.duke.FileResource;

/** 
 * {@summary Class the process data of web log files. Need to call the readFile method first}
 * @author Wladimir Pasquel
 * @lordksix
 */
public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<>();
    }
    /** 
     * {@summary Read a log file and creates a ArraList of all the Log Entries}
     */
    public void readFile () {    
        FileResource fr = new FileResource();
        for(String line: fr.lines()){
            records.add(WebLogParser.parseEntry(line));
        }
    }

    
    /** 
     * @return int: Quantity of Unique Ips in the log file
     */
    public int countUniqueIps(){
        return countVisitsPerIps().size();
    }

    
    /** 
     * {@summary Prints all the logs entry with status code higher than the parameter}
     * @param num
     */
    public void printAllHigherThanNum(int num){
        List<LogEntry> files = records.stream().filter(entry -> num < entry.getStatusCode() ).collect(Collectors.toList());
        System.out.printf("There are %d with statuc code higher than %d:%n",files.size(), num);
        files.forEach(e -> System.out.println(e));
    }

    
    /** 
     * {@summary Method that returns all the unique IPs in the selected day}
     * @param someday The selected day
     * @return Set<String>: Set of unique IPs that visit the selected day
     */
    public Set<String> uniqueIPVisitsOnDay(String someday){
        Set<String> files = records.stream().filter(entry -> entry.getAccessTime().toString().contains(someday)).map(entry->entry.getIpAddress()).collect(Collectors.toSet());
        return files;
    }

    
    /** 
     * {@summary Method that returns all the unique IPs in the range of status codes}
     * @param low
     * @param high
     * @return Set<String>: Set of unique IPs that within the range os status codes
     */
    public void countUniqueIPsInRange(int low, int high){
        Set<String> files = records.stream().filter(entry -> low <= entry.getStatusCode() 
        && entry.getStatusCode() <= high ).sorted(Comparator.comparing(LogEntry::getStatusCode,
        (s1,s2)->{return s1.compareTo(s2);})).map(entry->entry.getIpAddress()).collect(
            Collectors.toSet());
            System.out.printf("Total between %d and %d:%n",low, high);    
        files.forEach(e -> System.out.println(e));
        System.out.printf("Total between %d and %d: %d.%n",low, high,files.size());
    }

    
    /** 
     * {@summary Method that returns the counts of visits per unique IP from a list of IPs}
     * @param listIP
     * @return HashMap<String, Integer>
     */
    public HashMap<String,Integer> countVisitsPerIps(ArrayList<String> listIP){
        HashMap<String,Integer> counts = new HashMap<>();
        listIP.forEach(le->{
        if(!counts.containsKey(le)) counts.put(le, 1);
        else counts.put(le,counts.get(le)+1);});
        return counts;
    }

    
    /** 
     * {@summary Method that returns the counts of visits per unique IP from a records of the log file}
     * @return HashMap<String, Integer>
     */
    public HashMap<String,Integer> countVisitsPerIps(){
        HashMap<String,Integer> counts = new HashMap<>();
        records.forEach(le->{String ip=le.getIpAddress();
        if(!counts.containsKey(ip)) counts.put(ip, 1);
        else counts.put(ip,counts.get(ip)+1);});
        return counts;
    }

    
    /** 
     * {@summary Method that returns the max visitsof an IP from a records of the log file}
     * @param counts
     * @return int
     */
    public int mostNumberVisitsByIP(HashMap<String,Integer> counts) {
        return Collections.max(counts.values());
    }
    
    
    /** 
     * {@summary Method that returns the list of IP with the most visits}
     * @param counts
     * @return ArrayList<String>
     */
    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts) {
        List<String> listIP = counts.entrySet().stream().filter(entry -> entry.getValue() == mostNumberVisitsByIP(counts)).map(entry -> entry.getKey()).collect(Collectors.toList());
        return new ArrayList<>(listIP);
    }

    
    /** 
     * {@summary Method that returns a map of IP visits per day from the Logfile}
     * @return HashMap<String, ArrayList<String>>
     */
    public HashMap<String,ArrayList<String>> iPsForDays() {
        HashMap<String,ArrayList<String>> counts = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        for (LogEntry le : records) {
            list = new ArrayList<>();
            String leDate = le.getAccessTime().toString().substring(4,10);
            String ip=le.getIpAddress();
            if(!counts.containsKey(leDate)){                
                list.add(ip);
                counts.put(leDate, list);
            }
            else{
                list = counts.get(leDate);
                //if(!list.contains(ip)){
                    list.add(ip);
                    counts.put(leDate, list);
               //}
            }
        }
        return counts;
    }

    
    /** 
     * {@summary Method that list of IP with most visits in a selected day}
     * @param counts better use iPsForDays()
     * @param dates Date selected
     * @return ArrayList<String>
     */
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> counts, String dates) {
        ArrayList<String> listIP = new ArrayList<>();
        if(counts.containsKey(dates)){
            listIP=iPsMostVisits(countVisitsPerIps(counts.get(dates)));
        }
        else{listIP.add("No date founded");}
        return listIP;
    }

    
    /** 
     * {@summary List with all the days with max IP number, use with iPsForDays()}
     * @param counts = iPsForDays()
     * @return List<String>
     */
    public List<String> dayWithMostIPVisits(HashMap<String,ArrayList<String>> counts){
        int max = Collections.max(counts.entrySet(), (entry1, entry2) -> entry1.getValue().size()- entry2.getValue().size()).getValue().size();
        List<String> dayList = counts.entrySet().stream().filter(entry -> entry.getValue().size() == max).map(entry -> entry.getKey()).collect(Collectors.toList());
        return dayList;
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
    
}
