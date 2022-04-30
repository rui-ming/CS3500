package animator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import animator.model.Animator;
import animator.model.Move;
import animator.model.shapes.Shape;

/**
 * SVG View, creates an SVG formatted file or prints to System.out based on the animation.
 * Rather than rendering the animation visual it formats it into a textual description using
 * the SVG style which can be run in a browser.
 */
public class SVGView extends AbstractView {

  StringBuilder log;
  private PrintStream output;

  /**
   * SVG View constructor.
   *
   * @param a        animation to view.
   * @param tickRate tickrate.
   * @param fileName file to print to.
   * @throws FileNotFoundException Exception thrown if file is not found.
   */
  public SVGView(Animator a, long tickRate, String fileName) throws FileNotFoundException {
    super(a, tickRate);
    if (fileName.equals("System.out")) {
      output = System.out;
    } else {
      output = new PrintStream(new File(fileName));
    }
    System.setOut(output);
  }

  @Override
  public void renderView() {
    log = new StringBuilder();
    log.append("<!DOCTYPE html>\n<html>\n<body>\n");
    log.append("<svg width=\"").append(getCanvasWidth()).append("\" height=\"")
            .append(getCanvasHeight()).append("\">\n");
    for (int i = 0; i < getAnimator().getShapes().size(); i++) {
      Shape s = getAnimator().getShapes().get(i);
      switch (s.getType()) {
        case "Rectangle":
          log.append("<rect id=\"").append(s).append("\" x=\"").append(s.getX()).append("\" y=\"")
                  .append(s.getY()).append("\" width=\"").append(s.getWidth())
                  .append("\" height=\"").append(s.getHeight()).append("\" style=\"fill:")
                  .append(s.getColor()).append("\">\n");
          break;
        case "Oval":
          log.append("<ellipse id=\"").append(s).append("\" cx=\"").append(s.getX())
                  .append("\" cy=\"").append(s.getY()).append("\" rx=\"").append(s.getWidth())
                  .append("\" ry=\"").append(s.getHeight()).append("\" style=\"fill:")
                  .append(s.getColor()).append("\">\n");
          break;
        default:
          throw new IllegalArgumentException("Invalid Shape Type");
      }
      for (int j = 0; j < getAnimator().getMoves().size(); j++) {
        Move curr = getAnimator().getMoves().get(j);
        Shape moveShape = curr.getShape();
        if (moveShape.toString().equals(s.toString())) {
          switch (s.getType()) {
            case "Rectangle":
              animateRect(s, curr);
              break;
            case "Oval":
              animateOval(s, curr);
              break;
            default:
              throw new IllegalArgumentException("Invalid Shape Type");
          }
        }
      }
      switch (s.getType()) {
        case "Rectangle":
          log.append("</rect>\n");
          break;
        case "Oval":
          log.append("</ellipse>\n");
          break;
        default:
          throw new IllegalArgumentException("Invalid Shape Type");
      }
    }
    log.append("</svg>\n");
    log.append("</body>\n</html>");
  }

  @Override
  public void sendToFile() {
    System.out.print(log);
  }

  /**
   * Add the animation svg if that rectangle field needs to be changed during this move.
   *
   * @param s    Shape being animated.
   * @param curr Move to animate.
   */
  private void animateRect(Shape s, Move curr) {
    if (s.getX() != curr.getNewX()) {
      log.append("<animate attributeName=\"x\" attributeType=\"XML\" " + "begin=\"")
              .append(curr.getFromTick()).append("s\" dur=\"")
              .append(curr.getToTick() - curr.getFromTick())
              .append("s\" fill=\"freeze\" from=\"").append(s.getX())
              .append("\" to=\"").append(curr.getNewX()).append("\" /> \n");
      s.setX(curr.getNewX());
    }
    if (s.getY() != curr.getNewY()) {
      log.append("<animate attributeName=\"y\" attributeType=\"XML\" " + "begin=\"")
              .append(curr.getFromTick()).append("s\" dur=\"")
              .append(curr.getToTick() - curr.getFromTick())
              .append("s\" fill=\"freeze\" from=\"").append(s.getY())
              .append("\" to=\"").append(curr.getNewY()).append("\" /> \n");
      s.setY(curr.getNewY());
    }
    if (s.getWidth() != curr.getNewWidth()) {
      log.append("<animate attributeName=\"width\" attributeType=\"XML\" " + "begin=\"")
              .append(curr.getFromTick()).append("s\" dur=\"")
              .append(curr.getToTick() - curr.getFromTick())
              .append("s\" fill=\"freeze\" from=\"").append(s.getWidth())
              .append("\" to=\"").append(curr.getNewWidth()).append("\" /> \n");
      s.setWidth(curr.getNewWidth());
    }
    if (s.getHeight() != curr.getNewHeight()) {
      log.append("<animate attributeName=\"height\" attributeType=\"XML\" " + "begin=\"")
              .append(curr.getFromTick()).append("s\" dur=\"")
              .append(curr.getToTick() - curr.getFromTick())
              .append("s\" fill=\"freeze\" from=\"").append(s.getHeight())
              .append("\" to=\"").append(curr.getNewHeight()).append("\" /> \n");
      s.setHeight(curr.getNewHeight());
    }
    if (s.getColor() != curr.getNewColor()) {
      log.append("<animate attributeName=\"fill\" attributeType=\"CSS\" " + "begin=\"")
              .append(curr.getFromTick()).append("s\" dur=\"")
              .append(curr.getToTick() - curr.getFromTick())
              .append("s\" fill=\"freeze\" from=\"").append(s.getColor())
              .append("\" to=\"").append(curr.getNewColor()).append("\" /> \n");
      s.setColor(curr.getNewColor());
    }
  }

  /**
   * Add the animation svg if that oval field needs to be changed during this move.
   *
   * @param s    Shape being animated.
   * @param curr Move to animate.
   */
  private void animateOval(Shape s, Move curr) {
    if (s.getX() != curr.getNewX()) {
      log.append("<animate attributeName=\"cx\" attributeType=\"XML\" " + "begin=\"")
              .append(curr.getFromTick()).append("s\" dur=\"")
              .append(curr.getToTick() - curr.getFromTick())
              .append("s\" fill=\"freeze\" from=\"").append(s.getX())
              .append("\" to=\"").append(curr.getNewX()).append("\" /> \n");
      s.setX(curr.getNewX());
    }
    if (s.getY() != curr.getNewY()) {
      log.append("<animate attributeName=\"cy\" attributeType=\"XML\" " + "begin=\"")
              .append(curr.getFromTick()).append("s\" dur=\"")
              .append(curr.getToTick() - curr.getFromTick())
              .append("s\" fill=\"freeze\" from=\"").append(s.getY())
              .append("\" to=\"").append(curr.getNewY()).append("\" /> \n");
      s.setY(curr.getNewY());
    }
    if (s.getWidth() != curr.getNewWidth()) {
      log.append("<animate attributeName=\"rx\" attributeType=\"XML\" " + "begin=\"")
              .append(curr.getFromTick()).append("s\" dur=\"")
              .append(curr.getToTick() - curr.getFromTick())
              .append("s\" fill=\"freeze\" from=\"").append(s.getWidth())
              .append("\" to=\"").append(curr.getNewWidth()).append("\" /> \n");
      s.setWidth(curr.getNewWidth());
    }
    if (s.getHeight() != curr.getNewHeight()) {
      log.append("<animate attributeName=\"ry\" attributeType=\"XML\" " + "begin=\"")
              .append(curr.getFromTick()).append("s\" dur=\"")
              .append(curr.getToTick() - curr.getFromTick())
              .append("s\" fill=\"freeze\" from=\"").append(s.getHeight())
              .append("\" to=\"").append(curr.getNewHeight()).append("\" /> \n");
      s.setHeight(curr.getNewHeight());
    }
    if (s.getColor() != curr.getNewColor()) {
      log.append("<animate attributeName=\"fill\" attributeType=\"CSS\" " + "begin=\"")
              .append(curr.getFromTick()).append("s\" dur=\"")
              .append(curr.getToTick() - curr.getFromTick())
              .append("s\" fill=\"freeze\" from=\"").append(s.getColor())
              .append("\" to=\"").append(curr.getNewColor()).append("\" /> \n");
      s.setColor(curr.getNewColor());
    }
  }
}
