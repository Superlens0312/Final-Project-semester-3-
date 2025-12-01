/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */ 
 package Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenny Manset
 */
public class IntrustionsController{
    @FXML
    private Button goTo;
    @FXML
    private TextArea CarSetupText;

    @FXML
    private TextArea buttonText;

    /**
     * button to go to crash lab simulation
     */
    @FXML
    void goToButton(ActionEvent event) {
             try {
            Stage stage = (Stage) goTo.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/Crash_Lab_GUI.fxml")
            );
            Scene slideshowScene = new Scene(loader.load());

            stage.setScene(slideshowScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}