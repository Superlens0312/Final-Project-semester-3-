/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenny Manset
 * Performs basic Newtonian physics for head-on car collisions in 1D(moving left and right).
 */
public class PhysicsEngine {
     /**
     * Computes collision metrics for a single head-on collision in 1D.
     *
     * Assumptions:
     * - Car 1 moves to the right (+speed)
     * - Car 2 moves to the left (-speed)
     * - Perfectly inelastic collision (cars stick together after impact)
     * @param car1 user choice from combo-box
     * @param car2 user choice from combo-box
     * @return pBefore,pAfter,keBefore,keAfter,energyLossPercent,avgImpactForce,car1Damage,car2Damage
     */
    public static CollisionManager computeCollision(Car car1, Car car2) {

        double m1 = car1.getMass();
        double m2 = car2.getMass();

        // Directions: car1 to the right, car2 to the left
        double v1 = car1.getSpeed();
        double v2 = -car2.getSpeed();

        // Momentum (kg·m/s)
        double pBefore = m1 * v1 + m2 * v2;

        // Kinetic energy (J)
        double ke1Before = 0.5 * m1 * v1 * v1;
        double ke2Before = 0.5 * m2 * v2 * v2;
        double keBefore = ke1Before + ke2Before;

        // Perfectly inelastic: they stick & move together with vFinal
        double totalMass = m1 + m2;
        double vFinal = pBefore / totalMass;

        double pAfter = totalMass * vFinal;
        double keAfter = 0.5 * totalMass * vFinal * vFinal;

        double energyLoss = keBefore - keAfter;
        double energyLossPercent;
        
        if(keBefore > 0) {
            energyLossPercent = (energyLoss / keBefore) * 100;
        } else {
            energyLossPercent = 0.0;
        }
        
        // Approximate impact force using impulse over contact time
        // Use average of individual impulses for a rough estimate
        double contactTime = 0.2; // seconds (assumed)
        double impulse1 = m1 * Math.abs(vFinal - v1);
        double impulse2 = m2 * Math.abs(vFinal - v2);
        double avgImpulse = (impulse1 + impulse2) / 2.0;
        double avgImpactForce = avgImpulse / contactTime; // Newtons

        // Simple "damage" model based on energy loss and mass ratio.
        // This is not realistic but gives something to display.
        double baseDamage = Math.min(100.0, energyLoss / 1000.0); // scale energy to % roughly

        double car1Damage = baseDamage * (m2 / totalMass);
        double car2Damage = baseDamage * (m1 / totalMass);

        return new CollisionManager(pBefore,pAfter,keBefore,keAfter,energyLossPercent,avgImpactForce,car1Damage,car2Damage,contactTime);
    }      
}
