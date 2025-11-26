/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenny Manset
 * Holds the results of a single collision for reporting and UI display.
 */
public class CollisionManager {
    private double momentumBefore;
    private double momentumAfter;
    
    private  double totalKeBefore;
    private  double totalKeAfter;

    private  double energyLossPercent;
    private  double averageImpactForce; // Newtons (approx)

    private  double car1DamagePercent;
    private  double car2DamagePercent;
    
    //Might remove 
    private double car1survivalPercent;
    private double car2survivalPercent;

    //--- Constructor ---
    public CollisionManager(double momentumBefore, double momentumAfter, double totalKeBefore, double totalKeAfter, double energyLossPercent, double averageImpactForce, double car1DamagePercent, double car2DamagePercent) {
        this.momentumBefore = momentumBefore;
        this.momentumAfter = momentumAfter;
        this.totalKeBefore = totalKeBefore;
        this.totalKeAfter = totalKeAfter;
        this.energyLossPercent = energyLossPercent;
        this.averageImpactForce = averageImpactForce;
        this.car1DamagePercent = car1DamagePercent;
        this.car2DamagePercent = car2DamagePercent;
        
    }

    //--- Getters and Setters ---
    public double getMomentumBefore() {
        return momentumBefore;
    }

    public void setMomentumBefore(double momentumBefore) {
        this.momentumBefore = momentumBefore;
    }

    public double getMomentumAfter() {
        return momentumAfter;
    }

    public void setMomentumAfter(double momentumAfter) {
        this.momentumAfter = momentumAfter;
    }

    public double getTotalKeBefore() {
        return totalKeBefore;
    }

    public void setTotalKeBefore(double totalKeBefore) {
        this.totalKeBefore = totalKeBefore;
    }

    public double getTotalKeAfter() {
        return totalKeAfter;
    }

    public void setTotalKeAfter(double totalKeAfter) {
        this.totalKeAfter = totalKeAfter;
    }

    public double getEnergyLossPercent() {
        return energyLossPercent;
    }

    public void setEnergyLossPercent(double energyLossPercent) {
        this.energyLossPercent = energyLossPercent;
    }

    public double getAverageImpactForce() {
        return averageImpactForce;
    }

    public void setAverageImpactForce(double averageImpactForce) {
        this.averageImpactForce = averageImpactForce;
    }

    public double getCar1DamagePercent() {
        return car1DamagePercent;
    }

    public void setCar1DamagePercent(double car1DamagePercent) {
        this.car1DamagePercent = car1DamagePercent;
    }

    public double getCar2DamagePercent() {
        return car2DamagePercent;
    }

    public void setCar2DamagePercent(double car2DamagePercent) {
        this.car2DamagePercent = car2DamagePercent;
    }

    public double getCar1survivalPercent() {
        return car1survivalPercent;
    }

    public void setCar1survivalPercent(double car1survivalPercent) {
        this.car1survivalPercent = car1survivalPercent;
    }

    public double getCar2survivalPercent() {
        return car2survivalPercent;
    }

    public void setCar2survivalPercent(double car2survivalPercent) {
        this.car2survivalPercent = car2survivalPercent;
    }
}
