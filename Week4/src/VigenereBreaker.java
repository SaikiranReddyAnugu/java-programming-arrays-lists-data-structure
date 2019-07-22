import edu.duke.*;

public class VigenereBreaker {

    //Returns a String consisting of every totalSlices-th character from message,starting at the whichSlice-th character.
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String slicedString = "";
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            char currChar = message.charAt(i);
            slicedString += currChar;
        }
        return slicedString;
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

//breaks the key and prints decrypted message
    public void breakVigenere() {
        FileResource fr = new FileResource("./Datasets/athens_keyflute.txt");
        String message = fr.asString();
        int[] key = tryKeyLength(message, 5, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(message);
        System.out.println(decrypted);
    }

    public void testTryKeyLength() {
        FileResource fr = new FileResource("./Datasets/athens_keyflute.txt");
        String message = fr.asString();
        int klength = "flute".length();
        int[] key = tryKeyLength(message, klength, 'e');
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


}
