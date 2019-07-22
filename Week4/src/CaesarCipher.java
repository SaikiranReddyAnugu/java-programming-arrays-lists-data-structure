public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int theKey;

    public CaesarCipher(int key) {
        theKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        alphabet = alphabet + alphabet.toLowerCase();
        shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
    }

    public String encrypt(String input){
return translate(input,alphabet,shiftedAlphabet) ;
    }
 public String decrypt(String input){
return translate(input,shiftedAlphabet,alphabet);
 }
 public char encryptLetter(char input){
        return translateLetter(input,alphabet,shiftedAlphabet);
 }
    public char decryptLetter(char input){
        return translateLetter(input,shiftedAlphabet,alphabet);
    }
//encrypts or decrypts the Letter with respect to given call
    public char translateLetter(char c,String alphabets,String shiftedAlphabets){
        int idx = alphabets.indexOf(c);
        if (idx != -1) {
            return shiftedAlphabets.charAt(idx);
        }
        return c;
    }
    //encrypts or decrypts the message with respect to given call
 public String translate(String input,String alphabets,String shiftedAlphabets){
        StringBuilder sb=new StringBuilder(input);
        for(int i=0;i<sb.length();i++){
            char currChar=sb.charAt(i);
            char newChar=translateLetter(currChar,alphabets,shiftedAlphabets);
            sb.setCharAt(i, newChar);
            }
        return sb.toString();
 }


}
