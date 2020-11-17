Project: foxes-and-rabbits-v2
Authors: Michael Kölling and David J. Barnes

This project is part of the material for chapter 10 of the book

   Objects First with Java - A Practical Introduction using BlueJ
   Sixth edition
   David J. Barnes and Michael Kölling
   Pearson Education, 2016

A predator-prey simulation involving foxes and rabbits in
an enclosed rectangular field.

This is the first version of the simulation. This version
does not use inheritance.

How to start:
    Create a Simulator object.
    Then call one of:
        + simulateOneStep - for a single step.
        + simulate - and supply a number (say 10) for that many steps.
        + runLongSimulation - for a simulation of 500 steps.

What I changed:
First I moved the field age from Fox and Rabbit into the animal superclass.
I then moved the canBreed method from Fox and Rabbit into the animal superclass.
After that I moved the method incrementAgefrom Fox and Rabbit into the animal superclass.
I then figured out how to move the breed method from Fox and Rabbit into the animal superclass.
I finally then created a MountainLion subclass that serves as another predator in the simulation.
At the end I also added in some tests for the Simluation class, I'm not sure if I used testing correctly.
