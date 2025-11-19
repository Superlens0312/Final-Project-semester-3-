/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Throw_away;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 *
 * @author Lenny Manset
 */
public class ControlsBox extends VBox {
    private Button start = new Button("START");
    private Button stop  = new Button("STOP");
    private Button reset = new Button("RESET");

    //sets up controls for animation to start, stop, and reset
    public ControlsBox() {
        super(10);
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 0, 0, 0));
        setFillWidth(true);

        start.setPrefWidth(260);
        stop.setPrefWidth(260);
        reset.setPrefWidth(260);

        getChildren().addAll(start, stop, reset);
    }
}
