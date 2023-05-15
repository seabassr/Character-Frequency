import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

// Start of Java FX version of program
public class FxMain extends Application {
    // Create cipher object and FX elements
    private Cipher cipher = new Cipher();
    private Label text = new Label();
    private Label encryptLetters = new Label();
    private Label decryptLetters = new Label();
    private Label status = new Label();
    private TextField encrypt = new TextField();
    private TextField decrypt = new TextField();

    public static void main(String[] args) {
        launch(args);
    }

    // Start screen
    @Override
    public void start (Stage stage) {
        try {
            // Setup button to read file
            Button next = new Button("Analysis File");
            next.setOnAction(new nextHandler(stage, 1));
            status.setText("");

            // Setup elements vertical and center
            VBox vBox = new VBox(new Label("Filename:"), encrypt, next, status);
            vBox.setAlignment(Pos.CENTER);

            // Set scene and show stage
            stage.setScene(new Scene(vBox, 600, 500));
            stage.show();
        }
        catch (Exception error) {
            error.printStackTrace();
        }
    }

    // Handle the next scene
    private class nextHandler implements EventHandler<ActionEvent> {
        // Pass on stage and scene
        Stage stage;
        int scene;

        // Setup stage and scene
        public nextHandler(Stage stage, int scene) {
            this.stage = stage;
            this.scene = scene;
        }

        // Switch scene
        @Override
        public void handle(ActionEvent e) {
            // If scene is start (asking for file)
            if (scene == 1) {
                // If file exist
                if (cipher.setup(encrypt.getText())) {
                    // Clear text field and set character frequency scene
                    encrypt.clear();
                    stage.setScene(new Scene(analysisScene(stage), 600, 500));
                }
                // File doesn't exist
                else {
                    status.setText("File does not exist");
                }
            }
            // If scene is analysis scene
            else {
                // Set cipher scene
                stage.setScene(new Scene(cipherScene(), 600, 500));
            }
        }
    }

    // Change encrypt to decrypt character
    private class changeHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            // Change encrypt to decrypt character
            cipher.changeText(encrypt.getText().toLowerCase().charAt(0), decrypt.getText().toUpperCase().charAt(0));

            // Update text, encrypted and decrypted with change
            text.setText(cipher.getText());
            encryptLetters.setText("Encrypted Letters: " + cipher.getEncrypt());
            decryptLetters.setText("Decrypted Letters: " + cipher.getDecrypt());
            encrypt.clear();
            decrypt.clear();
        }
    }

    // VBox scene of character frequency
    private VBox analysisScene(Stage stage) {
        // Set label with text and button to next scene
        text.setText(cipher.getAnalysisData());
        Button updateBT = new Button("OK");
        updateBT.setOnAction(new nextHandler(stage, 2));

        // Setup elements vertical and center
        VBox vBox = new VBox(new Label("CHARACTER ANALYSIS:"), text, updateBT);
        vBox.setAlignment(Pos.CENTER);

        // Return VBox scene
        return vBox;
    }

    // VBox scene of cipher
    private VBox cipherScene() {
        // Set label with text, encrypted and decrypted characters, and button to change characters
        text.setText(cipher.getText());
        encryptLetters.setText("Encrypted Letters: " + cipher.getEncrypt());
        decryptLetters.setText("Decrypted Letters: " + cipher.getDecrypt());
        Button changeBT = new Button("Update Text");
        changeBT.setOnAction(new changeHandler());

        // Setup elements horizontal and center, encrypted text
        HBox encryptBox = new HBox(new Label("Encrypt Text: "), encrypt);
        encryptBox.setAlignment(Pos.CENTER);

        // Setup elements horizontal and center, decrypted text
        HBox decryptBox = new HBox(new Label("Decrypt Text: "), decrypt);
        decryptBox.setAlignment(Pos.CENTER);

        // Setup elements vertical and center
        VBox vBox = new VBox(new Label("TEXT FILE:"), text, encryptLetters, decryptLetters, encryptBox, decryptBox, changeBT);
        vBox.setAlignment(Pos.CENTER);

        // Return VBox scene
        return vBox;
    }
}