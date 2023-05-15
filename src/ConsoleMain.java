import java.util.Scanner;

// Start of console version of program
public class ConsoleMain {
    public static void main(String[] args) {
        // Create cipher object and variables
        Cipher cipher = new Cipher();
        Scanner input = new Scanner(System.in);
        String fileName;

        // Ask for file
        System.out.print("Encrypted filename: ");
        fileName = input.nextLine();

        // If file exist
        if (cipher.setup(fileName)) {
            char encrypt = ' ';
            char decrypt;

            // Display character frequency data
            System.out.print("CHARACTER ANALYSIS:\n" + cipher.getAnalysisData());

            // Keep changing text until users types "!"
            while (encrypt != '!') {
                // Display text, left over encrypted and decrypted characters
                System.out.print("\nTEXT FILE:\n" + cipher.getText() + "Encrypted Letters: " + cipher.getEncrypt() +
                        "\nDecrypted Letters: " + cipher.getDecrypt());

                // Get encrypted character
                System.out.print("\nEncrypted Character: ");
                encrypt = input.next().toLowerCase().charAt(0);

                // Get decrypted character
                System.out.print("Decrypted Character: ");
                decrypt = input.next().toUpperCase().charAt(0);

                // Change encrypted character to decrypted character
                cipher.changeText(encrypt, decrypt);
            }
        }
        // If file doesn't exist
        else {
            System.out.println("STATUS: WAS NOT ABLE TO ANALYSIS FILE");
        }
    }
}
