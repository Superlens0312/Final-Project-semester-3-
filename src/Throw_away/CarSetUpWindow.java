/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Throw_away;

import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

       //code from main
       //BorderPane root = new BorderPane();
       // root.setPadding(new Insets(12));

        // LEFT column: setup pane (two cars) + controls underneath
        //CarSetUpWindow setupPane = new CarSetUpWindow();
        //ControlsBox controls   = new ControlsBox();

        //VBox left = new VBox(12, setupPane, controls);
        //left.setPadding(new Insets(0, 8, 0, 0));
        //root.setLeft(left);

/**
 *
 * @author Lenny Manset
 */
public class CarSetUpWindow extends VBox {

     public CarSetUpWindow() {
        super(12);
        setPadding(new Insets(10));
        setPrefWidth(280);
        setFillWidth(true);

        TitledPane car1 = buildCarForm("Car 1");
        TitledPane car2 = buildCarForm("Car 2");

        getChildren().addAll(car1, car2);
    }
    //method that creates the ui for the cars this will
    private TitledPane buildCarForm(String title) {
        GridPane gp = new GridPane();
        gp.setHgap(8);
        gp.setVgap(8);
        gp.setPadding(new Insets(10));

        Label typeL = new Label("Type:");
        
        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Car", "Truck", "SUV","Motercycle");
        typeBox.setPrefWidth(150);

        Label massL = new Label("Mass (kg):");
        
        Slider massslider = new Slider(0, 1000, 500);
        massslider.setShowTickMarks(true);
        massslider.setShowTickLabels(true);
        massslider.setMajorTickUnit(250);
        massslider.setBlockIncrement(1);

        Label speedL = new Label("Speed (m/s):");
        
        Slider speedslider = new Slider(0, 500, 250);
        speedslider.setShowTickMarks(true);
        speedslider.setShowTickLabels(true);
        speedslider.setMajorTickUnit(100);
        speedslider.setBlockIncrement(1);

        Label angleL = new Label("Angle (°):");
        
        Slider angleslider = new Slider(0, 90, 45);
        angleslider.setShowTickMarks(true);
        angleslider.setShowTickLabels(true);
        angleslider.setMajorTickUnit(10);
        angleslider.setBlockIncrement(1);

        gp.add(typeL, 0, 0); gp.add(typeBox, 1, 0);
        gp.add(massL, 0, 1); gp.add(massslider,1,1);
        gp.add(speedL,0, 2); gp.add(speedslider,1,2);
        gp.add(angleL,0,3);  gp.add(angleslider,1,3);

        TitledPane pane = new TitledPane(title, gp);
        pane.setCollapsible(false);
        pane.setPrefWidth(250);
        pane.setMaxWidth(250);
        return pane;
    }
}
