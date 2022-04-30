package animator;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import animator.io.AnimationFileReader;
import animator.io.ModelBuilder;
import animator.io.TweenModelBuilder;
import animator.model.Animator;
import animator.view.AnimationViewer;
import animator.view.ControllableView;
import animator.view.SVGView;
import animator.view.TextView;
import animator.view.VisualView;

/**
 * Main class, runs the program by taking inputs as command line arguments and making a builder.
 */
public class Main {

  /**
   * Main function.
   *
   * @param args arguments off the command line.
   * @throws InterruptedException  Exception thrown if tick is interrupted.
   * @throws FileNotFoundException Exception thrown if File is not found.
   */
  public static void main(String[] args) throws InterruptedException, FileNotFoundException {
    String type = "";
    int ticksPerSecond = 1;
    String outFileName = "System.out";
    String inFileName = "";
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-in":
          inFileName = args[i + 1];
          break;
        case "-view":
          type = args[i + 1];
          break;
        case "-speed":
          ticksPerSecond = Integer.parseInt(args[i + 1]);
          break;
        case "-out":
          outFileName = args[i + 1];
          break;
        // generate a custom animation file to autocustom.txt with x circles
        case "-gen":
          AutomatedGenerator.generateFile(Integer.parseInt(args[i + 1]));
        default:
          // do nothing
      }
    }

    TweenModelBuilder<Animator> builder
            = new ModelBuilder(new ArrayList<>(), new ArrayList<>(), 500, 500);
    AnimationFileReader afr = new AnimationFileReader();
    Animator a = afr.readFile(inFileName, builder);
    AnimationViewer av = makeView(a, type, ticksPerSecond, outFileName);
    av.run();
  }

  /**
   * Makes a view based on the arguments.
   *
   * @param a              animation to view.
   * @param type           type of view.
   * @param ticksPerSecond ticks per second.
   * @param outFileName    file to send to if necessary.
   * @return An view for the given animation.
   * @throws FileNotFoundException throws exception if file is not found.
   */
  public static AnimationViewer makeView(Animator a, String type,
                                         int ticksPerSecond, String outFileName)
          throws FileNotFoundException {
    long tickRate = 1000L / ticksPerSecond;
    switch (type) {
      case "text":
        return new TextView(a, tickRate);
      case "visual":
        return new VisualView(a, tickRate);
      case "interactive":
        return new ControllableView(a, tickRate, false);
      case "svg":
        return new SVGView(a, tickRate, outFileName);
      default:
        throw new IllegalArgumentException("Invalid view type");
    }
  }
}
