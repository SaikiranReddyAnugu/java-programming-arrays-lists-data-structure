import edu.duke.FileResource;

public class CaeserBreaker {
    //count the frequency of letters in the given string and returns the most frequently occured index of letter
    public int countLetters(String s) {
        int[] freqs = new int[26];
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch)) {
                int index = alphabet.indexOf(Character.toUpperCase(ch));
                freqs[index]++;
            }
        }
        return maxIndex(freqs);
    }

  //return max index with maximum value
    public int maxIndex(int[] values) {
        int max = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[max] < values[i]) {
                max = i;
            }
        }
        return max;
    }

//breaks the key and returns it
    public int getKey(String s) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int maxIndex = countLetters(s);
        int key = maxIndex - alphabet.indexOf('E');
        if (key > 0) {
            return key;
        } else {
            return 26 + key;
        }

    }
//makes the string into half with respect start index
    public String halfOfString(String message, int start) {
        String halved = "";
        for (int i = start; i < message.length(); i += 2) {
            halved += message.charAt(i);
        }
        return halved;
    }

    public void testHalfOfString() {
        String message = "Qbkm Zgis";
        String halved = halfOfString(message,0);
            System.out.println(halved);

        halved = halfOfString(message,1);
        System.out.println(halved);
    }

    public void decrypt(String encrypted, int key) {
        CaeserCipher cc=new CaeserCipher();
        String message = cc.encrypt(encrypted, key);
        System.out.println(message);
    }

    public void testDecrypt() {
        String encrypted = "Grpq x qbpq pqofkd tfqe ilqp lc bbbbbbbbbbbbbbbbbp";
        int key = getKey(encrypted);
        decrypt(encrypted, 26 - key);
    }
//Splits the String into halves using halfOfString and finds 2 keys then decrypts the message using those keys
    public String decryptTwoKeys(String encrypted) {
        String halved1 = halfOfString(encrypted, 0);
        String halved2 = halfOfString(encrypted, 1);
        int key1 = getKey(halved1);
        int key2 = getKey(halved2);
        System.out.println("The two keys are: " + key1 + ", " + key2);
        CaeserCipher cc = new CaeserCipher();
        String message = cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
        return message;
    }

    public void testDecryptTwoKeys() {
        String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String message = decryptTwoKeys(encrypted);
        System.out.println("The decrypted message is " + message);

    }
}
