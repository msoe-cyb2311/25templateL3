/*
 *
 * @Course: TODO
 * Lab 3 - Stream cipher
 * @author: TODO
 *
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();

        // Create the scene
        Scene scene = new Scene(root);

        // Configure the stage
        stage.setTitle("Image Encryption with Stream Ciphers");
        stage.setScene(scene);

        // Prevent window from being too small
        stage.setMinWidth(900);  // Accommodate image view and controls
        stage.setMinHeight(700);

        // Show the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
