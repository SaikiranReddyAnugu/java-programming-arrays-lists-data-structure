import edu.duke.FileResource;

public class CaeserCipher {
    //encrypting the string using the given key and also maintaining case senstivity
    public String encrypt(String input, int key) {
        String alphabets= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabets = alphabets.substring(key) + alphabets.substring(0, key);
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char ch = encrypted.charAt(i);
            if (Character.isLetter(ch)) {
                char chUp = Character.toUpperCase(ch);
                int pos = alphabets.indexOf(chUp);
                char replace = shiftedAlphabets.charAt(pos);
                if (chUp == ch) {
                    encrypted.setCharAt(i, replace);
                } else {
                    encrypted.setCharAt(i, Character.toLowerCase(replace));
                }
            }
        }

        return encrypted.toString();
    }

    public void testCaesar() {
        int key = 2;
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println(message);
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
//encrypting the given string using 2 keys,odd places are encrypted using key1  and even places by key2
    public String encryptTwoKeys(String input, int key1, int key2) {
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabets1 = abc.substring(key1) + abc.substring(0, key1);
        String shiftedAlphabets2 = abc.substring(key2) + abc.substring(0, key2);
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char ch = encrypted.charAt(i);
            if (Character.isLetter(ch)) {
                char chUpper = Character.toUpperCase(ch);
                int pos = abc.indexOf(chUpper);
                char replacee;
                if (i % 2 == 0) {
                    replacee = shiftedAlphabets1.charAt(pos);
                } else {
                    replacee = shiftedAlphabets2.charAt(pos);
                }
                if (chUpper == ch) {
                    encrypted.setCharAt(i, replacee);
                } else {
                    encrypted.setCharAt(i, Character.toLowerCase(replacee));
                }
            }
        }

        return encrypted.toString();
    }

    public void testEncryptTwoKeys() {
        System.out.println(encryptTwoKeys("First Legion", 23, 17));
    }

}





