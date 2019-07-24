public class Tester {
    public static void main(String args[]){
 Tester t=new Tester();
 t.testLogAnalyzer();
    }   public void testLogAnalyzer(){

       LogAnalyzer l=new LogAnalyzer();
       l.readFile("src/Datasets/weblog3-short_log");
        l.printAll();

      // System.out.println( l.countUniqueIPs());
       // System.out.println(l.uniqueIPVisitsOnDay("Sep 14"));
       // System.out.println(l.countUniqueIPsInRange(200,299));
      //System.out.println(l.iPsForDays());
    }}
