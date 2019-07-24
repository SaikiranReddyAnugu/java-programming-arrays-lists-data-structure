import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
//finds the unique words and also the frequency that words in a given string
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            word = word.toLowerCase();
            if (!myWords.contains(word)) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int index = myWords.indexOf(word);
                int frequency = myFreqs.get(index);
                myFreqs.set(index, frequency + 1);
            }
        }
    }
//return the index with maximum value in myFreqs
    public int findIndexOfMax() {
        int index = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > myFreqs.get(index)) {
                index = i;
            }
        }
        return index;
    }

    public void tester() {
        findUnique();
        for (int k = 0; k < myWords.size(); k++) {
            System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
        }
        System.out.println("Number of unique words: " + myWords.size());
        int index = findIndexOfMax();
        System.out.println("The word that occurs the most often are: " + myWords.get(index)+" "+myFreqs.get(index));
    }


}
