package Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class PhysicsEngineTest {

    // Small tolerance for floating point comparison
    private static final double EPS = 1e-6;


    // ------------------------------------------------------------
    // TEST 1 — No movement → No KE, No force, No damage
    // ------------------------------------------------------------
    @Test
    void testNoMovementProducesZeroResults() {
        Car c1 = new Car("C1", 1000, 0.0, 0.0);
        Car c2 = new Car("C2", 1500, 0.0, 0.0);

        CollisionManager col = PhysicsEngine.computeCollision(c1, c2);

        assertEquals(0.0, col.getTotalKeBefore(), EPS);
        assertEquals(0.0, col.getTotalKeAfter(), EPS);
        assertEquals(0.0, col.getAverageImpactForce(), EPS);
        assertEquals(0.0, col.getCar1DamagePercent(), EPS);
        assertEquals(0.0, col.getCar2DamagePercent(), EPS);
        assertEquals(0.0, col.getEnergyLossPercent(), EPS);
    }


    // ------------------------------------------------------------
    // TEST 2 — Equal masses & speeds → Symmetric physics
    // ------------------------------------------------------------
    @Test
    void testHeadOnEqualMassSpeedSymmetric() {
        Car c1 = new Car("A", 1000, 20.0, 0.0);
        Car c2 = new Car("B", 1000, 20.0, 180.0);

        CollisionManager col = PhysicsEngine.computeCollision(c1, c2);

        // KE before = ½ m v² × 2 cars
        double expectedKE = 2 * (0.5 * 1000 * 20 * 20);

        assertEquals(expectedKE, col.getTotalKeBefore(), 1e-3);

        // After sticking, final momentum = 0, so vFinal = 0 → KE after = 0
        assertEquals(0.0, col.getTotalKeAfter(), EPS);

        // 100% of KE lost
        assertEquals(100.0, col.getEnergyLossPercent(), 1e-3);

        // Damage must be equal
        assertEquals(col.getCar1DamagePercent(), col.getCar2DamagePercent(), 1e-3);
    }


    // ------------------------------------------------------------
    // TEST 3 — Heavy vs light → Light must take more damage
    // ------------------------------------------------------------
    @Test
    void testHeavyVsLightDamageDistribution() {
        Car heavy = new Car("Truck", 4000, 20.0, 0.0);
        Car light = new Car("Bike", 500, 20.0, 180.0);

        CollisionManager col = PhysicsEngine.computeCollision(heavy, light);

        // Light car must take much more damage
        assertTrue(col.getCar2DamagePercent() > col.getCar1DamagePercent());
    }


    // ------------------------------------------------------------
    // TEST 4 — Energy loss percent must be between 0 and 100
    // ------------------------------------------------------------
    @Test
    void testEnergyPercentRange() {
        Car c1 = new Car("C1", 1200, 15.0, 0.0);
        Car c2 = new Car("C2", 1000, 10.0, 180.0);

        CollisionManager col = PhysicsEngine.computeCollision(c1, c2);

        assertTrue(col.getEnergyLossPercent() >= 0.0);
        assertTrue(col.getEnergyLossPercent() <= 100.0);
    }


    // ------------------------------------------------------------
    // TEST 5 — Momentum after must equal combinedMass * vFinal
    // (Physics invariant)
    // ------------------------------------------------------------
    @Test
    void testMomentumConservation() {
        Car c1 = new Car("C1", 1000, 15.0, 0.0);
        Car c2 = new Car("C2", 2000, 5.0, 180.0);

        CollisionManager col = PhysicsEngine.computeCollision(c1, c2);

        double pBefore = col.getMomentumBefore();
        double pAfter  = col.getMomentumAfter();

        // Should be equal for perfectly inelastic collision (floating tolerance)
        assertEquals(pBefore, pAfter, 1e-6);
    }


    // ------------------------------------------------------------
    // TEST 6 — Force must be positive when there's motion
    // ------------------------------------------------------------
    @Test
    void testImpactForcePositiveWhenMoving() {
        Car c1 = new Car("C1", 1000, 10.0, 0.0);
        Car c2 = new Car("C2", 1200, 8.0, 180.0);

        CollisionManager col = PhysicsEngine.computeCollision(c1, c2);

        assertTrue(col.getAverageImpactForce() > 0.0);
    }
}