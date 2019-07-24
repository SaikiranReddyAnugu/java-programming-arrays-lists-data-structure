import edu.duke.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class LogAnalyzer {


        private ArrayList<LogEntry> records;

        public LogAnalyzer() {

            records = new ArrayList<LogEntry>();
        }
//Read a file and creates logEntry object by parsing each lin efile with weblogparse and then adds them to arrayList
        public void readFile(String filename) {
            FileResource fr = new FileResource(filename);
            for (String line : fr.lines()) {
                LogEntry le = WebLogParser.parseEntry(line);
                records.add(le);
            }
        }
        //Prints all the logs
        public void printAll() {

            for (LogEntry le : records) {
                System.out.println(le);
            }
        }
        //Return the number of unique ip adresses
        public int countUniqueIPs() {
          ArrayList<String> unique = new ArrayList<String>();
          for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!unique.contains(ip)) {
                unique.add(ip);
            }
           }
          return unique.size();
    }

        //Prints the log entries that have status code greater than num
        public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }


     //Returns an ArrayList of Strings of unique Ipaddresses that had access on the given day.

       public ArrayList<String> uniqueIPVisitsOnDay(String day) {
          ArrayList<String> addresses = new ArrayList<String>();
          for (LogEntry le : records) {
            String d = le.getAccessTime().toString();
            String ip = le.getIpAddress();
            if (d.contains(day) && !addresses.contains(ip)) {
                addresses.add(ip);
            }
        }
        return addresses;
    }

    //Returns the number of unique IP addresses that have a status code in the range from low to high, inclusive.

        public int countUniqueIPsInRange(int low, int high) {
          HashMap<String, Integer> unique = new HashMap<String, Integer>();
          for (LogEntry le : records) {
            String ip = le.getIpAddress();
            int status = le.getStatusCode();
            if ( status >= low && status <= high && !unique.containsKey(ip)) {
                unique.put(ip, status);
            }
        }
        return unique.size();
    }

// Maps an IP address to the number of times this IP address visited the website.
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (map.containsKey(ip)) {
                int num = map.get(ip);
                map.put(ip, num + 1);
            } else {
                map.put(ip, 1);
            }
        }
        return map;
    }

    // Returns the maximum number of visits to this website by a single IP address.

    public int mostNumberVisitsByIP(HashMap<String, Integer> map) {
        int max = 0;
        for (String ip : map.keySet()) {
            int curr = map.get(ip);
            if (curr > max) {
                max = curr;
            }
        }
        return max;
    }

  // Returns an ArrayList of Strings of IP addresses that all have the maximum number of visits.

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
        ArrayList<String> mostVisitedIPs = new ArrayList<String>();
        int max = mostNumberVisitsByIP(map);
        for (String ip : map.keySet()) {
            if (map.get(ip) == max) {
                mostVisitedIPs.add(ip);
            }
        }
        return mostVisitedIPs;
    }

    //Returns a HashMap that uses records and maps days from web logs to an ArrayList of IP addresses that occurred on that day

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> Map = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            String date = le.getAccessTime().toString().substring(4,10);
            if (!Map.containsKey(date)) {
                ArrayList<String> ips = new ArrayList<String>();
                ips.add(ip);
                Map.put(date, ips);
            } else {
                ArrayList<String> ips = Map.get(date);
                ips.add(ip);
                Map.put(date, ips);
            }
        }
        return Map;
    }

    //Determines the day that has the most IP address visits.

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> Map) {
        String maxDay = "";
        for (String date : Map.keySet()) {
            ArrayList<String> ips = Map.get(date);
            if (maxDay.equals("") || ips.size() > Map.get(maxDay).size()) {
                maxDay = date;
            }
        }
        return maxDay;
    }

    //Returns an ArrayList<String> of IP addresses that had the most accesses on the given day.

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> Map, String date) {
        ArrayList<String> ips = Map.get(date);
        HashMap<String, Integer> visitmap = new HashMap<String, Integer>();
        for (String ip : ips) {
            if (visitmap.containsKey(ip)) {
                int num = visitmap.get(ip);
                visitmap.put(ip, num + 1);
            } else {
                visitmap.put(ip, 1);
            }
        }

        return iPsMostVisits(visitmap);
    }

}
