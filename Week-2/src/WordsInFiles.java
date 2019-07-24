import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
private HashMap<String, ArrayList<String>> map;
    public WordsInFiles() {
        map = new HashMap<String, ArrayList<String>>();
    }
    //Adds the word and filenames to map if they dont exist previously if word exists then adds the filename to its respective array list
    public void addWordsFromFile(File f){
        String filename=f.getName();
        FileResource fr=new FileResource(f);
        for(String word:fr.words()){
    if(map.containsKey(word)){
        ArrayList<String> al=map.get(word);
        if(!al.contains(filename)){
            al.add(filename);
        }}
        else{
            ArrayList<String> al=new ArrayList<String>();
            al.add(filename);
            map.put(word,al);
        }
    }
        }
        //selects each file from the directory and calls the addWordsFromFile method
    private void buildWordFileMap() {
        map.clear();
        DirectoryResource dir = new DirectoryResource();
        for (File f : dir.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    //Returns the maximum number of files any word appears in
    private int maxNumber() {
        int max = 0;
        for (String word : map.keySet()) {
            int count = map.get(word).size();
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    //Returns an ArrayList of words that appear in exactly number files.

    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();
        for (String word : map.keySet()) {
            int count = map.get(word).size();
            if (count == number) {
                words.add(word);
            }
        }
        return words;
    }

    /**
     * Prints the names of the files this word appears in, one filename per line.
     */
    private void printFilesln(String word) {
        ArrayList<String> filenames = map.get(word);
        for (int i = 0; i < filenames.size(); i++) {
            System.out.println(filenames.get(i));
        }
    }

    public void tester() {
        buildWordFileMap();
        int max = maxNumber();
        System.out.println("Maximum number of files any word appears in: " + max);
        System.out.println("All the words that are in " + max + " files:");
        ArrayList<String> words = wordsInNumFiles(max);
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            System.out.println("The word " + word + " appears in the following files: ");
            printFilesln(word);

        }
        System.out.println("Count of words that appear in " + max + " files: " + words.size());
        System.out.println("To answer the quiz: ");
        words = wordsInNumFiles(3);
        System.out.println("Words in 3 files: " + words);
    }
    }


