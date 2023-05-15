// Cipher class, keeping track of changes
public class Cipher {
    // Analysis object, character frequency data
    private final Analysis analysis = new Analysis();
    // Characters left in unchanged in text file
    private char[] encryptLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    // Characters left to change
    private char[] decryptLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    // Text from file
    private StringBuilder text;
    // Character frequency data
    private String analysisData;

    // Read file and get data
    public boolean setup(String fileName) {
        // Read file and start character frequency
        boolean status = analysis.characterAnalysis(fileName);
        // Get text from file
        text = new StringBuilder(analysis.getText());
        // Get character frequency count
        analysisData = analysis.getAnalysis();
        // Return, if able to read file
        return status;
    }

    // Change character from file, to new character
    public void changeText(char encrypt, char decrypt) {
        // Go over every character in text
        for (int letter = 0; letter < text.length(); letter++) {
            // If character to change, change to new character
            if (text.charAt(letter) == encrypt) {
                text.setCharAt(letter, decrypt);
            }
        }

        // Change encrypted text to uppercase
        encrypt = Character.toUpperCase(encrypt);

        // Change encrypted and decrypted text to empty
        for (int i = 0; i < 26; i++) {
            if (encryptLetters[i] == encrypt) {
                encryptLetters[i] = ' ';
            }

            if (decryptLetters[i] == decrypt) {
                decryptLetters[i] = ' ';
            }
        }
    }

    // Get text
    public String getText() {
        return text.toString();
    }

    // Get character frequency data
    public String getAnalysisData() {
        return analysisData;
    }

    // Get characters left from text
    public String getEncrypt() {
        String encrypt = "";

        for (char letter : encryptLetters) {
            encrypt += letter;
        }

        return encrypt;
    }

    // Get characters left to use
    public String getDecrypt() {
        String decrypt = "";

        for (char letter : decryptLetters) {
            decrypt += letter;
        }

        return decrypt;
    }
}
