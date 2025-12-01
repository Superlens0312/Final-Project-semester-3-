package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Car model.
 */
public class CarTest {

    // Small tolerance for floating-point comparison
    private static final double EPS = 1e-6;

    @Test
    public void testConstructorAndGetters() {
        // given
        String type   = "Sedan";
        double mass   = 1500.0;   // kg
        double speed  = 20.0;     // m/s
        double angle  = 15.0;     // degrees

        // when
        Car car = new Car(type, mass, speed, angle);

        // then
        assertEquals(type, car.getType());
        assertEquals(mass,  car.getMass(),  EPS);
        assertEquals(speed, car.getSpeed(), EPS);
        assertEquals(angle, car.getAngle(), EPS); 
    }

    @Test
    public void testSetters() {
        Car car = new Car("Truck", 2000.0, 10.0, 0.0);

        car.setType("Motorcycle");
        car.setMass(250.0);
        car.setSpeed(30.0);
        car.setAngle(45.0);  

        assertEquals("Motorcycle", car.getType());
        assertEquals(250.0, car.getMass(),  EPS);
        assertEquals(30.0,  car.getSpeed(), EPS);
        assertEquals(45.0,  car.getAngle(), EPS);
    }
}