import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("short-test_log");
        logAnalyzer.printAll();
    }
    
    public void uniqueIpTester(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");   
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are "+ uniqueIPs + " unique IPs ");
    }
    public void testUniqueIpVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> ips = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println("Number of IPs on this day : "+ ips.size());
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("The number of ips in the range is :"+ la.countUniqueIPsInRange(200,299));
    }
    public void testPrintAllHigherThanNumber(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");   
        la.printAllHigherThanNum(400);
    }
    public void testCountVisitsPerIp(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIp();
        System.out.println(counts);
        System.out.println("Most number of visits by an IP :"+ la.mostNumberVisitsByIp(counts)); 
        ArrayList<String> iPs = la.iPsMostVisited(counts);
        System.out.println("most viewed by IPs : "+ iPs);
        HashMap<String,ArrayList<String>> iPsOnDate = la.iPsForDays();
        System.out.println("The map of date to IPs on that day \n"+ iPsOnDate);
        System.out.println("Day with most IP visit :"+ la.dayWithMostIPVisits(iPsOnDate));
        ArrayList<String> mostIPsInDate = la.iPsWithMostVisitsOnDay(iPsOnDate,"Sep 30");
        System.out.println(mostIPsInDate);
    }
}