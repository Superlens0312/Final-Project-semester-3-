/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2488638
 */
public class TitleScreenController{
   
    /** Button that starts the marathon simulation screen. */
    @FXML
    private Button startButton;

    /** Button that closes the entire application. */
    @FXML
    private Button quitButton;

     /**
     * Called when the "Start Race" button is clicked.
     *
     * This method loads the slideshow screen. The slideshow
     * will automatically proceed to the marathon scene after
     * all runners have been shown.
     */
    @FXML
    private void onStartClicked() {
        try {
            Stage stage = (Stage) startButton.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/Instructions.fxml")
            );
            Scene slideshowScene = new Scene(loader.load());

            stage.setScene(slideshowScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when the "Quit" button is clicked.
     *
     * This method simply closes the JavaFX application.
     */
    @FXML
    private void onQuitClicked() {
        Platform.exit();
    }

   
}
