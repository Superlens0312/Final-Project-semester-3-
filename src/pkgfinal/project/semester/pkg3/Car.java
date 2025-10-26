/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project.semester.pkg3;

/**
 *
 * @author Lenny Manset
 */
public class Car {
    private String type;
    private double mass;  // in Kg
    private double speed; // in m/s
    private double angle; // in degrees

    public Car(String type, double mass, double speed, double angle) {
        this.type = type;
        this.mass = mass;
        this.speed = speed;
        this.angle = angle;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }   
}
