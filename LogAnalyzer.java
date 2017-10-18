import edu.duke.*;
import java.util.*;
public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    public LogAnalyzer(){
        records = new ArrayList<LogEntry>();
    }
    public void readFile(String fileName){
        records.clear();
        FileResource fr = new FileResource(fileName);
        for(String lines : fr.lines()){
            LogEntry object = WebLogParser.parseEntry(lines);
            records.add(object);
        }
    }
    
    public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        uniqueIPs.clear();
        for(LogEntry le : records){
            String ipAddr = le.getIpAddress();
            if(!uniqueIPs.contains(ipAddr)){
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }
    
    public void printAllHigherThanNum(int num){
        for(LogEntry le: records){
            int statusCode = le.getStatusCode();
            if(statusCode > num){
                System.out.println(le);
            }
        }
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> ipAddresses = new ArrayList<String>();
        for(LogEntry le : records){
            String date = le.getAccessTime().toString();
            if(date.contains(someday) && !ipAddresses.contains(le.getIpAddress())){
                ipAddresses.add(le.getIpAddress());
            }
        }
        return ipAddresses;
    }
    
    public int countUniqueIPsInRange(int low,int high){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry le : records){
            int statusCode = le.getStatusCode();
            if(statusCode>=low && statusCode<=high && !uniqueIPs.contains(le.getIpAddress())){
                uniqueIPs.add(le.getIpAddress());
            }
        }
        return uniqueIPs.size();
    }    
    
    public void printAll(){
        for(LogEntry le : records)
            System.out.println(le);
    }
    
    public HashMap<String,Integer> countVisitsPerIp(){
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for(LogEntry le : records){
            String ip = le.getIpAddress();
            if(!counts.containsKey(ip)){
                counts.put(ip,1);
            }
            else{
                counts.put(ip,counts.get(ip)+1);
            }
        }
        return counts;
    }
    
    public int mostNumberVisitsByIp(HashMap<String,Integer> counts){
        int max = 0;
        for(String keys : counts.keySet()){
            int value = counts.get(keys);
            if(max < value)
                max = value;
        }
        return max;
    }
    
    public ArrayList<String> iPsMostVisited(HashMap<String,Integer> counts){
        ArrayList<String> iPs = new ArrayList<String>();
        int max = mostNumberVisitsByIp(counts);
        for(String key : counts.keySet()){
            if(max == counts.get(key))
                iPs.add(key);
        }
        return iPs;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for(LogEntry le : records){
            String date= le.getAccessTime().toString().substring(4,10);
            if(!map.containsKey(date)){
                ArrayList<String> iPsOnDate = new ArrayList<String>();
                iPsOnDate.add(le.getIpAddress());
                map.put(date,iPsOnDate);
            }
            else{
                ArrayList<String> iPsOnDate = map.get(date);
                iPsOnDate.add(le.getIpAddress());
                map.put(date,iPsOnDate);
            }
        }
        return map;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map){
        int max=0;
        String maxDate = "";
        for(String key: map.keySet()){
            int size = map.get(key).size();
            if(max < size){
                max = size;
                maxDate = key;
            }
        }
        return maxDate;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map , String date){
        ArrayList<String> listOfMostVisitedIpsOnDate = null;
        if(map.containsKey(date)){
            ArrayList<String> listOfVisitedIpsOnDate = map.get(date);
            HashMap<String,Integer> iPsCountOnDate = new HashMap<String,Integer>();
            for(String ip : listOfVisitedIpsOnDate){
                if(!iPsCountOnDate.containsKey(ip)){
                    iPsCountOnDate.put(ip,1);
                }
                else{
                    iPsCountOnDate.put(ip,iPsCountOnDate.get(ip)+1);
                }
            }
            listOfMostVisitedIpsOnDate = iPsMostVisited(iPsCountOnDate);
        }
        return listOfMostVisitedIpsOnDate;
    }
}
