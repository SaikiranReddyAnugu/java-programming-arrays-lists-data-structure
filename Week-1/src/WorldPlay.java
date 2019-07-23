public class WorldPlay {
//returns true for vowels otherwise false
    public Boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        Boolean flag = false;
        String vowels = "aieou";
        if (vowels.indexOf(ch) != -1) {
            flag = true;
        }
        return flag;
    }

    public void testIsVowel() {
        char ch = 'x';
        Boolean answer = isVowel(ch);
        System.out.println(answer);
    }
    //replacing vowels in a string by given character
    public String replaceVowels(String phrase, char ch) {

        StringBuilder replaced = new StringBuilder(phrase);
        for (int i = 0; i < replaced.length(); i++) {
            if (isVowel(replaced.charAt(i))) {
                replaced.setCharAt(i, ch);
            }
        }
        return replaced.toString();
    }

    public void testReplaceVowels() {
        String phrase = "Hello World";
        char ch = '*';
        String replaced = replaceVowels(phrase, ch);
        System.out.println(replaced);
    }
//replacing even positions occurance by * and odd positions occurance by + for a given character in a string
    public String emphasize(String phrase, char ch) {
        StringBuilder replaced = new StringBuilder(phrase);
        for (int i = 0; i < replaced.length(); i++) {
            if (replaced.charAt(i) == ch || Character.toLowerCase(replaced.charAt(i)) == ch) {
                if (i % 2 == 0) {
                    replaced.setCharAt(i, '*');
                }
                else {
                    replaced.setCharAt(i, '+');
                }
            }
        }
        return replaced.toString();
    }

    public void testEmphasize() {
        String phrase = "iuefh gjh ahgd djk";
        char ch = 'g';
        String replaced = emphasize(phrase, ch);
        System.out.println(replaced);
    }
}
