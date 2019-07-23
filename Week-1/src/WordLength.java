import edu.duke.FileResource;

public class WordLength {
    //inserts the similar lengths count into the count array
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int length = 0;
            for (int i = 0; i < word.length(); i++) {
                if ((i == 0 || i == word.length() - 1) && !Character.isLetter(word.charAt(i))) {
                    break;
                }
                length++;
            }
            int lastIndex = counts.length - 1;
            if (length > lastIndex) {
                counts[lastIndex]++;
            } else {
                counts[length]++;
            }
        }
    }

    public void testCountWordLengths() {
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource, counts);
        for (int k = 0; k < counts.length; k++) {
            if (counts[k] > 0) {
                System.out.println(counts[k] + " words of length " + k);
            }
        }
        System.out.println( indexOfMax(counts));
    }

   //returns the index position of largest length element in values
    public int indexOfMax(int[] values) {
        int maxm = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[maxm] < values[i]) {
                maxm = i;
            }
        }
        return maxm;
    }

}