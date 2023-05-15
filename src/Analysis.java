import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Analysis class, counting frequency of characters
public class Analysis {
    // Hold count of characters
    private HashMap<Character, Integer> count = new HashMap<>();
    // Total of characters
    private int total = 0;
    // Text in file
    private String text = "";
    // Frequency data
    private String analysis = "";
    // Able to read file
    private boolean status = false;

    // Read file and count frequency of characters
    private void readFile(String fileName) {
        // Try to read file
        try (Scanner S = new Scanner(new File(fileName))) {
            String line;

            // Setup hashmap
            for (char position = 'A'; position <= 'Z'; position++) {
                count.put(position, 0);
            }

            // Read line
            while (S.hasNextLine()) {
                line = S.nextLine();
                // Add line to text
                text += line.toLowerCase() + "\n";

                // Grab character
                for (int position = 0; position < line.length(); position++) {
                    // Check if character is part of the alphabet
                    if (Character.isAlphabetic(line.charAt(position))) {
                        // Update hashmap with corresponding letter
                        count.put(line.toUpperCase().charAt(position),
                                count.get(line.toUpperCase().charAt(position)) + 1);
                        // Increase total
                        total++;
                    }
                }
            }

            // Update status to true
            status = true;
        }
        // If file doesn't exist
        catch (FileNotFoundException error) {
            // Update status to false
            status = false;
        }
    }

    // Get character frequency
    public boolean characterAnalysis(String fileName) {
        // Read file
        readFile(fileName);

        // If able to read file
        if (status) {
            // Transfer character frequency into a string
            for (Map.Entry<Character, Integer> letter : count.entrySet()) {
                analysis += letter.getKey() + " " + letter.getValue() + " "
                + (Math.round((double) letter.getValue() / total * 10000.0) / 100.0) + "%\n";
            }

            // Return true
            return true;
        }
        // Not able to read file
        else {
            // Return false
            return false;
        }
    }

    // Get character frequency data
    public String getAnalysis() {
        return analysis;
    }

    // Get text from file
    public String getText() {
        return text;
    }
}
