package wordCounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MostRepeatedWords {
    public static void main(String[] args) {
        // TODO: If there are anything in parantheses, brackets, quotation marks, etc. program will not work properly. Will be fixed.

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:\\Users\\username\\Desktop\\text.txt")); // change the path to your text file
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Please check the path.");
            System.exit(1);
        }

        Map<String, Integer> map = getMap(scanner);
        print(map, 10);
    }

    /**
     * Reads the text file and returns a map with words as keys and their frequencies as values.
     *
     * @return a map with words as keys and their frequencies as values
     */
    private static Map<String, Integer> getMap(Scanner scanner) {
        Map<String, Integer> map = new HashMap<>();

        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();

            if (word.charAt(word.length() - 1) == '.' || word.charAt(word.length() - 1) == ',') { // remove punctuation marks from the end of the word
                word = word.substring(0, word.length() - 1);
            }
            if (word.length() < 4) { // ignore words with less than 4 characters because they are most likely to be prepositions, conjunctions, etc.
                continue;
            }
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
        return map;
    }

    /**
     * Prints the most frequent words in the map.
     *
     * @param map the map to be printed
     * @param n   the number of most frequent words to be printed
     */
    private static void print(Map<String, Integer> map, int n) {
        HashMap<String, Integer> temp = new HashMap<>(map);
        for (int i = 0; i < n; i++) {
            int max = 0;
            String mostFrequentWord = "";
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() > max) {
                    max = entry.getValue();
                    mostFrequentWord = entry.getKey();
                }
            }
            System.out.println(mostFrequentWord + " - " + max);
            temp.put(mostFrequentWord, map.get(mostFrequentWord));
            map.remove(mostFrequentWord);
        }
        map.putAll(temp); // restore the original map
    }
}
