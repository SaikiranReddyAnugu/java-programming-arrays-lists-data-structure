public class CaesarCracker {
    char mostCommonLetter;

    public CaesarCracker() {
        mostCommonLetter = 'e';
    }

    public CaesarCracker(char c) {
        mostCommonLetter = c;
    }

    //returns the frequncy of each alphabet for the given message
    public int[] countLetters(String message){
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k < message.length(); k++){
            int dex = alphabets.indexOf(Character.toLowerCase(message.charAt(k)));
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
//returns most frequently occured alphabet
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k=0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
//breaks the key using countLetter and maxIndex methods
    public int getKey(String encrypted){
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int mostCommonPos = mostCommonLetter - 'a';
        int key = maxDex - mostCommonPos;
        if (maxDex < mostCommonPos) {
            key = 26 - (mostCommonPos-maxDex);
        }
        return key;
    }
//decrypts the message using key
    public String decrypt(String encrypted){
        int key = getKey(encrypted);
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(encrypted);

    }

}
