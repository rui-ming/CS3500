package animator.model.shapes;

import java.awt.Color;

/**
 * Example of extending Abstract shape, represents an oval.
 */
public class Oval extends AbstractShape {

  private final String name;
  private final double defaultX;
  private final double defaultY;
  private final double defaultWidth;
  private final double defaultHeight;
  private final Color defaultColor;

  /**
   * Constructor for a oval.
   *
   * @param x      X coordinate.
   * @param y      Y coordinate.
   * @param width  Width of the rectangle perfectly surrounding this oval.
   * @param height Height of the rectangle perfectly surrounding this oval.
   * @param color  Color of the oval.
   * @throws IllegalArgumentException if parameters are null or less than 0
   */
  public Oval(double x, double y, double width, double height, Color color, String name) {
    if (x < 0 || y < 0 || width < 0 || height < 0 || color == null || name == null) {
      throw new IllegalArgumentException("Illegal parameters");
    }
    this.defaultX = x;
    this.defaultY = y;
    this.defaultWidth = width;
    this.defaultHeight = height;
    this.defaultColor = color;
    reset();
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public String getType() {
    return "Oval";
  }

  @Override
  public void reset() {
    this.setX(defaultX);
    this.setY(defaultY);
    this.setColor(defaultColor);
    this.setWidth(defaultWidth);
    this.setHeight(defaultHeight);
  }
}
