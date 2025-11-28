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
    private Label Car1WeightNum;

    @FXML
    private Label Car2WeightNum;

    @FXML
    private Label car1AngleNum;
    
    @FXML
    private Label car1SpeedNum;
    
    @FXML
    private Label car2AngleNum;

    @FXML
    private Label car2SpeedNum;
    
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
     
    private TranslateTransition car1Animation;
    private TranslateTransition car2Animation;
    private boolean isStoppedOnce = false; // to check first stop
    private double car1StartX, car2StartX; // to remember positions
    
   
    public void initialize(URL url, ResourceBundle rb) {
        
        explosionImage.setImage(new Image(getClass().getResource("/images/explosion2.gif").toExternalForm()));
        explosionImage.setVisible(false);
        
        setupImageCache();
        setupComboBoxes();
        setupListeners();
        setupAnimations();
        setupSliderLabels();
    }
    
    private void setupImageCache() {
        imageCache.put("Sedan", new Image("/images/Car_R.png"));
        imageCache.put("Truck", new Image("/images/Truck_L.png"));
        imageCache.put("Motorcycle", new Image("/images/Bike_L.png"));
    }
    
    private void setupComboBoxes() {
        car1Choice.getItems().addAll("Sedan", "Truck", "Motorcycle");
        car2Choice.getItems().addAll("Sedan", "Truck", "Motorcycle");
    }

    private void setupListeners() {
        car1Choice.valueProperty().addListener((obs, oldVal, newVal) -> updateCar1Config(newVal));
        car2Choice.valueProperty().addListener((obs, oldVal, newVal) -> updateCar2Config(newVal));
    }

    private void setupAnimations() {    
    car1StartX = car1Image.getTranslateX();
    car2StartX = car2Image.getTranslateX();
    
    car1Animation = new TranslateTransition(Duration.seconds(2), car1Image);
    car2Animation = new TranslateTransition(Duration.seconds(2), car2Image);
    }
    
    private void setupSliderLabels() {
        // Car 1
        car1Speed.valueProperty().addListener((obs, oldVal, newVal) -> 
            car1SpeedNum.setText(String.format("%.1f km/h",newVal.doubleValue())
        ));
        car1angle.valueProperty().addListener((obs, oldVal, newVal) -> 
            car1AngleNum.setText(String.format("%.0f°", newVal.doubleValue()))
        );
        car1weight.valueProperty().addListener((obs, oldVal, newVal) -> 
            Car1WeightNum.setText(String.format("%.0f kg", newVal.doubleValue()))
        );

        // Car 2
        car2speed.valueProperty().addListener((obs, oldVal, newVal) -> 
            car2SpeedNum.setText(String.format("%.1f km/h", newVal.doubleValue()))
        );
        car2angle.valueProperty().addListener((obs, oldVal, newVal) -> 
            car2AngleNum.setText(String.format("%.0f °", newVal.doubleValue()))
        );
        car2weight.valueProperty().addListener((obs, oldVal, newVal) -> 
            Car2WeightNum.setText(String.format("%.0f kg", newVal.doubleValue()))
        );
    }
    
    private void updateCar1Config(String type) {
        if (type == null) return;

        car1Image.setImage(imageCache.get(type));

        switch (type) {
            case "Sedan" -> setupWeightSlider(car1weight, 1000, 2000, 250);
            case "Truck" -> setupWeightSlider(car1weight, 2500, 4000, 375);
            case "Motorcycle" -> setupWeightSlider(car1weight, 100, 800, 175);
        }
    }

    private void updateCar2Config(String type) {
        if (type == null) return;

        car2Image.setImage(imageCache.get(type));

        switch (type) {
            case "Sedan" -> setupWeightSlider(car2weight, 1000, 2000, 250);
            case "Truck" -> setupWeightSlider(car2weight, 2500, 4000, 375);
            case "Motorcycle" -> setupWeightSlider(car2weight, 100, 800, 175);
        }
    }

    private void setupWeightSlider(Slider slider, double min, double max, double tick) {
        slider.setMin(min);
        slider.setMax(max);
        slider.setMajorTickUnit(tick);
    } 

    @FXML
    void resetBtn(ActionEvent event) {
        car1Animation.stop();
        car1Image.setTranslateX(car1StartX);
        car2Image.setTranslateX(car2StartX);
        winnerName.setText("");      // clear text
        forceResult.setText("");
        energyResult.setText("");
        durationResult.setText("");
        explosionImage.setVisible(false);
    }

    @FXML
    void startBtn(ActionEvent event) {
        resetPositions();
        
        car1Animation.setByX(150);
        car1Animation.play();

        car2Animation.setByX(-150); // opposite direction
        car2Animation.play();
        
        car1Animation.setOnFinished(e -> {
        explosionImage.setVisible(true);
        // Optional: place it in the middle between the cars
        explosionImage.setTranslateX((car1Image.getTranslateX() + car2Image.getTranslateX()) / 2);
    });

        isStoppedOnce = false; // reset stop state
    }

    @FXML
    void stopBtn(ActionEvent event) {
        if (!isStoppedOnce) {
            // first stop: pause current animations
            car1Animation.pause();
            car2Animation.pause();
            isStoppedOnce = true;
        } else {
            // second stop: move cars toward each other
            TranslateTransition car1Crash = new TranslateTransition(Duration.seconds(1), car1Image);
            TranslateTransition car2Crash = new TranslateTransition(Duration.seconds(1), car2Image);
            
            // when the last animation finishes, show explosion
            car2Crash.setOnFinished(e -> explosionImage.setVisible(true));

            car1Crash.setByX(125);  // move right
            car2Crash.setByX(-125); // move left
            car1Crash.play();
            car2Crash.play();

            isStoppedOnce = false; // reset
        }
    }
    
    private void resetPositions() {
    car1Animation.stop();
    car2Animation.stop();
    car1Image.setTranslateX(car1StartX);
    car2Image.setTranslateX(car2StartX);
    explosionImage.setVisible(false); // hide explosion
}
    
    @FXML
    void carChosen(ActionEvent event){   
    }
}
