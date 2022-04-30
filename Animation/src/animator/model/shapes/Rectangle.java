package animator.model.shapes;

import java.awt.Color;

/**
 * Example of extending Abstract shape, represents a rectangle.
 */
public class Rectangle extends AbstractShape {

  private final String name;
  private final double defaultX;
  private final double defaultY;
  private final double defaultWidth;
  private final double defaultHeight;
  private final Color defaultColor;

  /**
   * Constructor for a rectangle.
   *
   * @param x      X coordinate.
   * @param y      Y coordinate.
   * @param width  Width of the rectangle.
   * @param height Height of the rectangle.
   * @param color  Color of the rectangle.
   * @throws IllegalArgumentException if parameters are null or less than 0
   */
  public Rectangle(double x, double y, double width, double height, Color color, String name) {
    if (width < 0 || height < 0 || color == null || name == null) {
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
    return "Rectangle";
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
