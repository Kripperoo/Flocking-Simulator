Source file need to compile: 
Bird.java  
                         
CartesianCoordinate.java
                          
DynamicBirds.java
                       
Flock.java
                       
LineSegment.java
                        
Simulator.java
                          
Velocity.java
                          


Entry point used to run final Program: 

Simulator.java

This project is a proof-of-concept for the implementation of algorithms to simulate the flocking behaviour of birds. In this project not only do I simulate their flocking behaviour but also designed my own algorithm to periodically vary the birds’ flocking patterns. The marker was particularly impressed and it scored 80%. 

Due to this implementation being a first year software project, I did not realise at the time that there still existed many areas that could have been better optimised.For instance, I could have added boundary conditions to limit the birds to the window range. I also could have added collision detection to prevent the birds from overlapping. 

Nevertheless, I have succeeded in implementing the flocking algorithm using three vectors to control individual parts of bird behaviour: cohesion, separation, and alignment (Flock.java). I also used a trigonometric function to vary these vectors periodically, so the the birds can flock and separate periodically.

To run this project you need Canvas.class, which is included in the file. 

Compile and run this project by typing the following two command codes in the terminal:


javac *.java
           
java Simulator


I hope you enjoy this project, and I am looking forward to hearing from you.
