Crash Lab — JavaFX Physics Collision Simulator

Vanier College — Program Development in a Graphical Environment (Fall 2025)
Authors: Lenny Manset & Mkrtich Sargsyan


Crash Lab is an interactive 2D car-collision simulation built using JavaFX, structured with MVC, and powered by a Newtonian Physics Engine.
The application allows users to:

-> Select different vehicles

-> Adjust speed, mass, and angle

-> Run a head-on collision simulation with animations
_______________________________________________________________________________________________________________________________________________________
--- View calculated physics results ---

-> Momentum

-> Kinetic energy

-> Impact force

-> Energy loss %

-> Damage % for each vehicle
_______________________________________________________________________________________________________________________________________________________
The project includes:

Full JavaFX UI with multiple screens (Title Screen, Instructions, Simulation View)

Realistic animation timing based on user input

Physics engine using Newtonian mechanics

JUnit tests (Physics engine + collision calculations)

Structured, easy-to-read code following best practices
___________________________________________________________________________________________________________________________________________________________
How to Run the Project
1. Requirements

Java 21

JavaFX SDK installed

NetBeans or IntelliJ

2. Running the Application

Clone the repository

Open project in NetBeans

Ensure JavaFX libraries are linked in Project → Properties → Libraries → Classpath

Run Main.java

The UI will load and you can:
-> Set speed
-> Set mass
-> Adjust angles
-> Start animation
-> View results (KE, force, damage, total loss)
____________________________________________________________________________________________________________________________________________________________________
Teamwork Summary

Clear contributions of each team member.

Lenny Manset

Implemented PhysicsEngine calculations

Wrote CollisionManager core code

Mkrtich Sargsyan

Implemented car animation logic

Connected sliders, event handlers, animation flow

Both

Built JavaFX UI and FXML screens

Added tests for Car, PhysicsEngine, CollisionManager


