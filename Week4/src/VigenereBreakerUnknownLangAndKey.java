import edu.duke.FileResource;

import java.util.HashMap;
import java.util.HashSet;

public class VigenereBreakerUnknownLangAndKey {

        //Returns a String consisting of every totalSlices-th character from message,starting at the whichSlice-th character.

        public String sliceString(String message, int whichSlice, int totalSlices) {
            String sliced = "";
            for (int i = whichSlice; i < message.length(); i += totalSlices) {
                char letter = message.charAt(i);
                sliced += letter;
            }
            return sliced;
        }

        public void testSliceString() {
            System.out.println(sliceString("abcdefghijklm", 0, 3).equals("adgjm"));
            System.out.println(sliceString("abcdefghijklm", 1, 3).equals("behk"));
            System.out.println(sliceString("abcdefghijklm", 2, 3).equals("cfil"));
            System.out.println(sliceString("abcdefghijklm", 0, 4).equals("aeim"));
            System.out.println(sliceString("abcdefghijklm", 1, 4).equals("bfj"));
            System.out.println(sliceString("abcdefghijklm", 2, 4).equals("cgk"));
            System.out.println(sliceString("abcdefghijklm", 3, 4).equals("dhl"));
            System.out.println(sliceString("abcdefghijklm", 0, 5).equals("afk"));
            System.out.println(sliceString("abcdefghijklm", 1, 5).equals("bgl"));
            System.out.println(sliceString("abcdefghijklm", 2, 5).equals("chm"));
            System.out.println(sliceString("abcdefghijklm", 3, 5).equals("di"));
            System.out.println(sliceString("abcdefghijklm", 4, 5).equals("ej"));
        };


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

        public void testTryKeyLength() {
            FileResource fr = new FileResource("VigenereTestData/athens_keyflute.txt");
            String message = fr.asString();
            int klength = "flute".length();
            int[] key = tryKeyLength(message, klength, 'e');  // [5, 11, 20, 19, 4]
        }

    public void breakVigenere() {
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();

        // All the languages we have dictionaries for
        String[] dictionaries = new String[8];
        dictionaries[0] = "Danish";
        dictionaries[1] = "Dutch";
        dictionaries[2] = "English";
        dictionaries[3] = "French";
        dictionaries[4] = "German";
        dictionaries[5] = "Italian";
        dictionaries[6] = "Portuguese";
        dictionaries[7] = "Spanish";

        // For each dictionary file
        for (int i = 0; i < dictionaries.length; i++) {
            String languageName = dictionaries[i];
            FileResource dictFile = new FileResource("./Datasets/dictionaries/" + languageName);
            HashSet<String> dictionary = readDictionary(dictFile);
            languages.put(languageName, dictionary);
            System.out.println("Just added " + languageName);
        }

        FileResource fr = new FileResource("./Datasets/secretmessage3.txt");
        String message = fr.asString();
        breakForAllLangs(message, languages);
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

        //gives the most common char in the respective language
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> charFreq = new HashMap<Character, Integer>();
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!Character.isLetter(ch)) {
                    break;
                }
                if (charFreq.containsKey(ch)) {
                    int freq = charFreq.get(ch);
                    charFreq.put(ch, freq + 1);
                } else {
                    charFreq.put(ch, 1);
                }
            }
        }
        char mostCommonChar = ' ';
        int maxCount = 0;
        for (char ch : charFreq.keySet()) {
            int freq = charFreq.get(ch);
            if (Character.isSpaceChar(mostCommonChar)) {
                mostCommonChar = ch;
                maxCount = freq;
            } else {
                if (freq > maxCount) {
                    mostCommonChar = ch;
                    maxCount = freq;
                }
            }
        }
        return mostCommonChar;
    }

//gives the decrypted message in languages with respect to given key
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        for (String language : languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            System.out.println("Analyzing the text with " + language);
            String decrypted = breakForLanguage(encrypted, dictionary);
            System.out.println(decrypted);
        }
    }

        //Finds which decryption gives the largest count of real words and returns that String decryption.

        public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
            int maxRealWords = 0;
            int finalKeyLength = 1;
            String decryptionWithRealWords = "";
            char mostCommonChar = mostCommonCharIn(dictionary);

            for (int keylength = 1; keylength <= 100; keylength++) {
                int[] key = tryKeyLength(encrypted, keylength, mostCommonChar);
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



