import java.util.Arrays;

public class VigenereCipher {
    CaesarCipher[] ciphers;

    public VigenereCipher(int[] key) {
        ciphers = new CaesarCipher[key.length];
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }

    public String encrypt(String input) {
        StringBuilder message = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher currCipher = ciphers[cipherIndex];
            message.append(currCipher.encryptLetter(c));
            i++;
        }
        return message.toString();
    }

    public String decrypt(String input) {
        StringBuilder message = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher currCipher = ciphers[cipherIndex];
            message.append(currCipher.decryptLetter(c));
            i++;
        }
        return message.toString();
    }

    public String toString() {
        return Arrays.toString(ciphers);
    }
}
