Model Design:
We designed our Model to have a list of shapes that we can add to. It represents the shapes in the animation.
We also designed a list of all moves in the model and a list of moves we are going to animate.
Both the Shape and Move class are custom classes we made to hold the relavent data.
Shapes hold color, size, and location.
Moves hold a shape to move and the new values for that shape (new location, size, etc) as well as the tick to start and end that animation.

Changes we made from last time:
We implemented checks in our addMove method of the model that ensures that all moves in the list are legal.
For a move to be legal it cannot overlap with the time from of a move on the same shape.
Also if move than one move is performed on a shape the move must flow from one to the other meaning a new move starts when the first move ends.

View Design:
We designed our view with the intention to be able to extend it to multiple types of views.
For this reason we have an interface as well as an abstract class allowing for easy use of common code.
We also designed the view class to keep track of current tick as well as the tick rate so the view can display the animation at the proper speed.
Finally the view can render the animation depending on what type of view it is.
We made a Jpanel for both a visual and textual representation of the animation.
We also made a SVGView that produces an SVG formatted file for the animation.

Changes we made from last time (Assignment 6 addition):
Fixed a bug where only moving shapes were drawn in the visual view. 
Created an interactive design that has buttons that can be clicked for different features.
Gave more descriptive JavaDoc comments for different view classes.
Created a program to automatically generate an animation based on a given number of circles.
This program can be accessed from the main function of the animator class by giving the flag "-gen" and following it with a number of circles.
The autocustom.txt example supplied in the resources folder was made using 20 circles.
We also created a manually made animation about a snowman trying to get an apple from a tree.