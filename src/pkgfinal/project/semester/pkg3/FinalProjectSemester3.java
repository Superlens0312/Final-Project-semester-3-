/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkgfinal.project.semester.pkg3;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Lenny Manset
 */
public class FinalProjectSemester3 extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage)  {       
       BorderPane root = new BorderPane();
        root.setPadding(new Insets(12));

        // LEFT column: setup pane (two cars) + controls underneath
        CarSetUpWindow setupPane = new CarSetUpWindow();
        ControlsBox controls   = new ControlsBox();

        VBox left = new VBox(12, setupPane, controls);
        left.setPadding(new Insets(0, 8, 0, 0));
        root.setLeft(left);

        Scene scene = new Scene(root, 1400, 600);
        stage.setTitle("Crash-Lab");
        stage.setScene(scene);
        stage.show();
    }
}