import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> counts;

    public CharactersInPlay() {
        characters = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
//updates the character and count array lists with the string and its frequency
    public void update(String person) {
        if (!characters.contains(person)) {
            characters.add(person);
            counts.add(1);
        } else {
            int index = characters.indexOf(person);
            int count = counts.get(index);
            counts.set(index, count + 1);
        }
    }
    //splits using . in each line then takes string before period and calls update
    public void findAllCharacters() {
        characters.clear();
        counts.clear();
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            if (line.contains(".")) {
                String person = line.split("\\.")[0].trim();
                update(person);
            }
        }

    }

    public void tester() {
        findAllCharacters();

        System.out.println("Number of characters: " + characters.size());
        for (int k = 0; k < characters.size(); k++) {
            int count = counts.get(k);
            if (count > 2) {
                System.out.println(characters.get(k) + " has " + count + " speaking parts");
            }
        }

        charactersWithNumParts(2, 10);
    }

//prints the characters which has frequency between num1 and num2
    public void charactersWithNumParts(int num1, int num2) {
        for (int k = 0; k < characters.size(); k++) {
            int count = counts.get(k);
            if (count >= num1 && count <= num2) {
                System.out.println(characters.get(k));
            }
        }
    }
}
