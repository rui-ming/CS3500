package animator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Class that can generate a custom animation file. Makes the file contain a number of circle
 * shapes based on the parameter passed to generate file. Stores the animation in autocustom.txt.
 */
public class AutomatedGenerator {

  public static void generateFile(int numCircles) throws FileNotFoundException {
    PrintStream output;
    output = new PrintStream(new File("autocustom.txt"));
    System.setOut(output);

    ArrayList<String> circleNames = new ArrayList<>();

    for (int i = 0; i < numCircles; i++) {
      circleNames.add("circle" + i);
      double r = Math.random();
      double g = Math.random();
      double b = Math.random();
      System.out.println("oval name circle" + i + " center-x " + ((i * 50) + 25)
              + " center-y 200 x-radius 20 y-radius 20 color "
              + r + " " + g + " " + b + " from 0 to 200");
    }

    int repeats = (int) (Math.random() * numCircles) + 1;
    // repeat up to number of circles times

    for (int i = 0; i < repeats; i++) {
      // for each circle
      for (int j = 0; j < numCircles; j++) {
        // even number repeat
        if (i % 2 == 0) {
          // move even circles down
          if (j % 2 == 0) {
            System.out.println("move name " + circleNames.get(j)
                    + " moveto 0 0 " + ((j * 50) + 25) + " " + 250
                    + " from " + (i * 50) + " to " + ((i * 50) + 50));
            // move odd circles up
          } else {
            System.out.println("move name " + circleNames.get(j)
                    + " moveto 0 0 " + ((j * 50) + 25) + " " + 150
                    + " from " + (i * 50) + " to " + ((i * 50) + 50));
          }
          // odd number repeat
        } else {
          // move even circles up
          if (j % 2 == 0) {
            System.out.println("move name " + circleNames.get(j)
                    + " moveto 0 0 " + ((j * 50) + 25) + " " + 150
                    + " from " + (i * 50) + " to " + ((i * 50) + 50));
            // move odd circles down
          } else {
            System.out.println("move name " + circleNames.get(j)
                    + " moveto 0 0 " + ((j * 50) + 25) + " " + 250
                    + " from " + (i * 50) + " to " + ((i * 50) + 50));
          }
        }
      }
      // if this is the last repeat
      if (i == repeats - 1) {
        for (int j = 0; j < numCircles; j++) {
          // Line up all the circles
          System.out.println("move name " + circleNames.get(j)
                  + " moveto 0 0 " + 150 + " " + ((j * 50) + 25)
                  + " from " + ((i * 50) + 50) + " to " + ((i * 50) + 100));
          // Stack them all together to end the animation
          System.out.println("move name " + circleNames.get(j)
                  + " moveto 0 0 150 150 "
                  + " from " + ((i * 50) + 100) + " to " + ((i * 50) + 150));
        }
      }
    }
  }
}
