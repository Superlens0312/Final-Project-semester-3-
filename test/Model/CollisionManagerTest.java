package Model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for CollisionManager – makes sure it correctly stores and
 * returns collision data.
 */
public class CollisionManagerTest {

    private static final double EPS = 1e-6;

    @Test
    public void testConstructorAndGetters() {
        double pBefore   = 10.0;
        double pAfter    = 5.0;
        double keBefore  = 1000.0;
        double keAfter   = 600.0;
        double lossPct   = 40.0;
        double force     = 5000.0;
        double car1Dmg   = 30.0;
        double car2Dmg   = 70.0;
        double contact   = 0.25;

        CollisionManager cm = new CollisionManager(
                pBefore,
                pAfter,
                keBefore,
                keAfter,
                lossPct,
                force,
                car1Dmg,
                car2Dmg,
                contact
        );

        assertEquals(pBefore,  cm.getMomentumBefore(),   EPS);
        assertEquals(pAfter,   cm.getMomentumAfter(),    EPS);
        assertEquals(keBefore, cm.getTotalKeBefore(),    EPS);
        assertEquals(keAfter,  cm.getTotalKeAfter(),     EPS);
        assertEquals(lossPct,  cm.getEnergyLossPercent(),EPS);
        assertEquals(force,    cm.getAverageImpactForce(), EPS);
        assertEquals(car1Dmg,  cm.getCar1DamagePercent(), EPS);
        assertEquals(car2Dmg,  cm.getCar2DamagePercent(), EPS);
        assertEquals(contact,  cm.getContactTime(),      EPS);
    }

    @Test
    public void testSetters() {
        CollisionManager cm = new CollisionManager(
                0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0,
                0.2
        );

        cm.setMomentumBefore(12.0);
        cm.setMomentumAfter(3.0);
        cm.setTotalKeBefore(900.0);
        cm.setTotalKeAfter(450.0);
        cm.setEnergyLossPercent(50.0);
        cm.setAverageImpactForce(8000.0);
        cm.setCar1DamagePercent(20.0);
        cm.setCar2DamagePercent(80.0);
        cm.setCar1survivalPercent(80.0);
        cm.setCar2survivalPercent(20.0);

        assertEquals(12.0, cm.getMomentumBefore(),       EPS);
        assertEquals(3.0,  cm.getMomentumAfter(),        EPS);
        assertEquals(900.0,cm.getTotalKeBefore(),        EPS);
        assertEquals(450.0,cm.getTotalKeAfter(),         EPS);
        assertEquals(50.0, cm.getEnergyLossPercent(),    EPS);
        assertEquals(8000.0,cm.getAverageImpactForce(),  EPS);
        assertEquals(20.0, cm.getCar1DamagePercent(),    EPS);
        assertEquals(80.0, cm.getCar2DamagePercent(),    EPS);
        assertEquals(80.0, cm.getCar1survivalPercent(),  EPS);
        assertEquals(20.0, cm.getCar2survivalPercent(),  EPS);
    }
}