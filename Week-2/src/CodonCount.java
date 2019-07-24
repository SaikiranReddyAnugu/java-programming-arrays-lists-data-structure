import edu.duke.FileResource;

import java.util.HashMap;

public class CodonCount {
    private HashMap<String, Integer> map;

    public CodonCount() {
        map = new HashMap<String, Integer>();
        }
        //Divides the string into codons and inserts them with their count in a map
    private void buildCodonMap(int start, String dna) {
        map.clear();

        for (int i = start; i <= dna.length() - 3; i+=3) {
            String codon = dna.substring(i, i+3);
            if (map.containsKey(codon)) {
                int count = map.get(codon);
                map.put(codon, count + 1);
            } else {
                map.put(codon, 1);
            }
        }

    }

   // Returns a String, the codon in a reading frame that has the largest count.

    private String getMostCommonCodon() {
        int count = 0;
        String mostCommonCodon = "";
        for (String codon : map.keySet()) {
            int curr = map.get(codon);
            if (curr > count) {
                mostCommonCodon = codon;
                count = curr;
            }
        }
        return mostCommonCodon;
    }


    //Prints all the codons in the HashMap along with their counts if their count is between start and end, inclusive.

    private void printCodonCounts(int start, int end) {
        for (String codon : map.keySet()) {
            int count = map.get(codon);
            if (count >= start && count <= end) {
                System.out.println(codon + " " + count);
            }
        }
    }

    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase().trim();
        for (int start = 0; start <= 2; start++) {
            buildCodonMap(start, dna);
            System.out.println("Reading frame starting with " + start + " results in " + map.size() +
                    " unique codons");
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("and the most common codon is " + mostCommonCodon +
                    " with count " + map.get(mostCommonCodon));
            System.out.println("Counts of codons between 1 and 5 inclusive are: ");
            printCodonCounts(1,5);
            System.out.println();
        }
    }
}
