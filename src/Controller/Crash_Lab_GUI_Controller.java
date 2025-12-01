package Controller;

import Model.Car;
import Model.CollisionManager;
import Model.PhysicsEngine;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
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
        // Reset positions and previous results
        resetPositions();
        resetResults();

        // Make sure previous animations are stopped
        car1Animation.stop();
        car2Animation.stop();

        // --- Compute current gap between cars on screen ---
        double car1Right = car1Image.getBoundsInParent().getMaxX();
        double car2Left  = car2Image.getBoundsInParent().getMinX();
        double gap = car2Left - car1Right;   // distance between front of car1 and front of car2

        if (gap < 0) {
            gap = 0; // already overlapping a bit, just in case
        }

        // --- Read speeds from sliders (use them as relative speeds) ---
        double s1 = Math.max(car1Speed.getValue(), 1.0);  // avoid 0
        double s2 = Math.max(car2speed.getValue(), 1.0);

        double sum = s1 + s2;

        // --- Determine how much each car should move ---
        // We want them to meet: d1 + d2 = gap
        // And keep the ratio d1 : d2 = s1 : s2 (faster car travels more)
        double d1 = gap * (s1 / sum);  // car 1 distance (to the right)
        double d2 = gap * (s2 / sum);  // car 2 distance (to the left)

        // ---  Use a common duration for both animations ---
        double durationSeconds = 2.0;  // we can tweak this later if needed (1.5, 2.5, etc.)

        car1Animation.setDuration(Duration.seconds(durationSeconds));
        car2Animation.setDuration(Duration.seconds(durationSeconds));

        car1Animation.setByX(d1);      // move right
        car2Animation.setByX(-d2);     // move left

        explosionImage.setVisible(false);

        // When they finish this move, they should be touching.
        car1Animation.setOnFinished(null);
        car2Animation.setOnFinished(null);
       
        car1Animation.setOnFinished(e -> {
               explosionImage.setVisible(true);
               explosionImage.setTranslateX(
                       (car1Image.getTranslateX() + car2Image.getTranslateX()) / 2
               );
               updatePhysicsResults();
            });
 
        car1Animation.play();
        car2Animation.play();
    }



    /**
     * Button that pauses and resumes the animation 
     */
    @FXML
    void stopBtn(ActionEvent event) {
            // Toggle pause/play on both animations
            Animation.Status status = car1Animation.getStatus();

            if (status == Animation.Status.RUNNING) {
                car1Animation.pause();
                car2Animation.pause();
            } else if (status == Animation.Status.PAUSED) {
                car1Animation.play();
                car2Animation.play();
            }
        }
    
    private void resetPositions() {
    car1Animation.stop();
    car2Animation.stop();
    car1Image.setTranslateX(car1StartX);
    car2Image.setTranslateX(car2StartX);
    explosionImage.setVisible(false); // hide explosion
}
    
    /**
     * Create a car object from the car class in model, and using the users input
     */
    @FXML
    void carChosen(ActionEvent event){   
    }
    
    // --- build Car objects from GUI values and update physics results ---

    private Car buildCar1FromInputs() {
        String type = car1Choice.getValue();
        if (type == null) {
            type = "Car 1";
        }

        double mass = car1weight.getValue();
        double speedKmh = car1Speed.getValue();
        double angleDeg = car1angle.getValue();

        // convert km/h -> m/s
        double speedMs = speedKmh / 3.6;

        return new Car(type, mass, speedMs, angleDeg);
    }

    private Car buildCar2FromInputs() {
        String type = car2Choice.getValue();
        if (type == null) {
            type = "Car 2";
        }

        double mass = car2weight.getValue();
        double speedKmh = car2speed.getValue();
        double angleDeg = car2angle.getValue();

        double speedMs = speedKmh / 3.6;

        return new Car(type, mass, speedMs, angleDeg);
    }

    /**
     * Method to update all labels to display current physics(kinetic energy, impact force, etc) and display winner
     */
    private void updatePhysicsResults() {
        Car c1 = buildCar1FromInputs();
        Car c2 = buildCar2FromInputs();

        CollisionManager result = PhysicsEngine.computeCollision(c1, c2);

        // Momentum & energy summary (for your report)
        energyResult.setText(String.format(
                "KE: %.0f J → %.0f J (−%.1f%%)",
                result.getTotalKeBefore(),
                result.getTotalKeAfter(),
                result.getEnergyLossPercent()
        ));

        forceResult.setText(String.format("≈ %.0f N", result.getAverageImpactForce()));
        durationResult.setText(String.format("%.2f s", result.getContactTime()));

        double d1 = result.getCar1DamagePercent();
        double d2 = result.getCar2DamagePercent();

        car1Damage.setText(String.format("%.1f %%", d1));
        car2Damage.setText(String.format("%.1f %%", d2));

        car1Survival.setText(String.format("%.1f %%", 100.0 - d1));
        car2Survival.setText(String.format("%.1f %%", 100.0 - d2));

        if (100.0 - d1 > 100.0 - d2) {
            winnerName.setText("Car 1 (" + c1.getType() + ")");
        } else if (100.0 - d2 > 100.0 - d1) {
            winnerName.setText("Car 2 (" + c2.getType() + ")");
        } else {
            winnerName.setText("Tie");
        }
    }
    
        // Convert km/h slider value + angle into an "effective" head-on speed in m/s
    private double computeEffectiveSpeedMs(double speedKmh, double angleDeg) {
        // km/h -> m/s
        double speedMs = speedKmh / 3.6;

        // only the component along the horizontal (head-on) direction
        double angleRad = Math.toRadians(angleDeg);
        double horizontalComponent = Math.cos(angleRad);
        double headOnFactor = Math.abs(horizontalComponent);

        double vEff = speedMs * headOnFactor;

        // Avoid zero or extremely tiny speeds (would make duration explode)
        return Math.max(vEff, 0.1);
    }

    // Compute how long the animation should take, in seconds
    private double computeTravelDuration(double distancePixels,
                                         double speedKmh,
                                         double angleDeg) {

        double vEff = computeEffectiveSpeedMs(speedKmh, angleDeg);

        // Arbitrary visual scaling: how many pixels represent 1 meter
        double pixelsPerMeter = 5.0;
        double distanceMeters = distancePixels / pixelsPerMeter;

        double t = distanceMeters / vEff; // seconds

        // Clamp to something reasonable for animation
        double minT = 0.3;  // very fast
        double maxT = 5.0;  // very slow
        if (t < minT) t = minT;
        if (t > maxT) t = maxT;

        return t;
    }

    /**
     * Resets all labels i results tab
     */
    private void resetResults() {
        winnerName.setText("");
        forceResult.setText("");
        energyResult.setText("");
        durationResult.setText("");

        car1Damage.setText("");
        car1Survival.setText("");
        car2Damage.setText("");
        car2Survival.setText("");
        
    }
}

