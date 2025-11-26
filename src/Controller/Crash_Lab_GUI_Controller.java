package Controller;

import Model.Car;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 *
 * @author Lenny Manset, Mkrtich Sargsyan
 */

public class Crash_Lab_GUI_Controller implements Initializable {

    @FXML
    private ComboBox<String> car1Choice;

    @FXML
    private Label car1Damage;

    @FXML
    private ImageView car1Image;

    @FXML
    private Slider car1Speed;

    @FXML
    private Label car1Survival;

    @FXML
    private Slider car1angle;

    @FXML
    private Slider car1weight;

    @FXML
    private ComboBox<String> car2Choice;

    @FXML
    private Label car2Damage;

    @FXML
    private ImageView car2Image;

    @FXML
    private Label car2Survival;

    @FXML
    private Slider car2angle;

    @FXML
    private Slider car2speed;

    @FXML
    private Slider car2weight;

    @FXML
    private Label durationResult;

    @FXML
    private Label energyResult;

    @FXML
    private ImageView explosionImage;

    @FXML
    private Label forceResult;

    @FXML
    private Button resetButton;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private Label winnerName;
    
     private final Map<String, Image> imageCache = new HashMap<>();
    
   
    public void initialize(URL url, ResourceBundle rb) {
        
        car1Choice.getItems().addAll("Sedan", "Truck", "Motorcycle");
        imageCache.put("Sedan", new Image("/images/Car_R.png"));
        imageCache.put("Truck", new Image("/images/Truck_L.png"));
        imageCache.put("Bike", new Image("/images/Bike_L.png"));
        car2Choice.getItems().addAll("Sedan", "Truck", "Motorcycle");
        car1Choice.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String choice = car1Choice.getValue();
            if(choice.equalsIgnoreCase("Sedan")){
                car1Image.setImage(imageCache.get("Sedan"));
                car1weight.setMin(1000);
                car1weight.setMax(2000);
                car1weight.setMajorTickUnit(250);
            } else if(choice.equalsIgnoreCase("Truck")){
                car1Image.setImage(imageCache.get("Truck"));
                car1weight.setMin(2500);
                car1weight.setMax(4000);
                car1weight.setMajorTickUnit(375);
            } else if(choice.equalsIgnoreCase("Motorcycle")){
                car1Image.setImage(imageCache.get("Bike"));
                car1weight.setMin(100);
                car1weight.setMax(800);
                car1weight.setMajorTickUnit(175);
            }
        });
        car2Choice.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String choice = car2Choice.getValue();
            if(choice.equalsIgnoreCase("Sedan")){
                car2Image.setImage(imageCache.get("Sedan"));
                car2weight.setMin(1000);
                car2weight.setMax(2000);
                car2weight.setMajorTickUnit(250);
            } else if(choice.equalsIgnoreCase("Truck")){
                car2Image.setImage(imageCache.get("Truck"));
                car2weight.setMin(2500);
                car2weight.setMax(4000);
                car2weight.setMajorTickUnit(375);
            } else if(choice.equalsIgnoreCase("Motorcycle")){
                car2Image.setImage(imageCache.get("Bike"));
                car2weight.setMin(100);
                car2weight.setMax(800);
                car2weight.setMajorTickUnit(175);
            }
        });
    }
    
//    private void setupVehicleConfig() {
//    Car.put("Sedan", 
//        new Car(new Image("/images/Car_R.png"), 1000, 2000, 250));
//    Car.put("Truck", 
//        new Car(new Image("/images/Truck_L.png"), 2500, 4000, 375));
//    Car.put("Motorcycle", 
//        new Car(new Image("/images/Bike_L.png"), 100, 800, 175));
//}

    @FXML
    void carChosen(ActionEvent event) {
        }
    

    @FXML
    void resetBtn(ActionEvent event) {

    }

    @FXML
    void startBtn(ActionEvent event) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), car1Image);
        transition.setByX(125);
        transition.play();
    }

    @FXML
    void stopBtn(ActionEvent event) {

    }
}
    
//    private static class VehicleConfig {
//    Image image;
//    int minWeight;
//    int maxWeight;
//    int tick;
//    double defaultSpeed;
//    double defaultAngle;
//
//    VehicleConfig(Image image, int minWeight, int maxWeight, int tick, double defaultSpeed, double defaultAngle) {
//        this.image = image;
//        this.minWeight = minWeight;
//        this.maxWeight = maxWeight;
//        this.tick = tick;
//        this.defaultSpeed = defaultSpeed;
//        this.defaultAngle = defaultAngle;
//    }
//}

