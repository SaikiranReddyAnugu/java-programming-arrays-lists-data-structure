import edu.duke.FileResource;

import java.util.HashSet;

public class VigenereBreakerUnknownKey {

    //Returns a String consisting of every totalSlices-th character from message,starting at the whichSlice-th character.

        public String sliceString(String message, int whichSlice, int totalSlices) {
            String sliced = "";
            for (int i = whichSlice; i < message.length(); i += totalSlices) {
                char letter = message.charAt(i);
                sliced += letter;
            }
            return sliced;
        }



         //Finds the shift for each index in the key, fills in the key array and returns it.

        public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
            int[] key = new int[klength];
            for (int i = 0; i < klength; i++) {
                String sliced = sliceString(encrypted, i, klength);
                CaesarCracker cc = new CaesarCracker(mostCommon);
                key[i] = cc.getKey(sliced);
            }
            return key;
        }

//decrypts the file contents with the use of dictionary and breakForLanguage methods

        public void breakVigenere() {
            FileResource dictFile = new FileResource("./Datasets/dictionaries/English");
            HashSet<String> dictionary = readDictionary(dictFile);
            FileResource fr = new FileResource("./Datasets/athens_keyflute.txt");
            String message = fr.asString();
            String decrypted = breakForLanguage(message, dictionary);
            System.out.println(decrypted);
        }

    //for the given file with all words in dictionary is parsed by hashset
        public HashSet<String> readDictionary(FileResource fr) {
            HashSet<String> dictionaryWords = new HashSet<String>();
            for (String line : fr.lines()) {
                line = line.toLowerCase();
                dictionaryWords.add(line);
            }
            return dictionaryWords;
        }

     //splits the messages into words and checks that words with real words  and returns count of real words
        public int countWords(String message, HashSet<String> dictionary) {
            int realWords = 0;
            String[] words = message.split("\\W+");
            for (String word : words) {
                word = word.toLowerCase();
                if (dictionary.contains(word)) {
                    realWords++;
                }
            }
            return realWords;
        }

         //Finds which decryption gives the largest count of real words and returns that String decryption.

        public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
            int maxRealWords = 0;
            int finalKeyLength = 1;
            String decryptionWithRealWords = "";

            for (int keylength = 1; keylength <= 100; keylength++) {
                int[] key = tryKeyLength(encrypted, keylength, 'e');
                VigenereCipher vc = new VigenereCipher(key);
                String decrypted = vc.decrypt(encrypted);
                int realWords = countWords(decrypted, dictionary);
                if (realWords > maxRealWords) {
                    maxRealWords = realWords;
                    decryptionWithRealWords = decrypted;
                    finalKeyLength = keylength;
                }
            }
            return decryptionWithRealWords;
        }
    }

