package animator.model.shapes;

import java.awt.Color;

/**
 * Shape interface used in animations.
 */
public interface Shape {

  /**
   * Get the x coordinate for this shape.
   *
   * @return x.
   */
  public double getX();

  /**
   * Set the x coordinate for this shape.
   *
   * @param x double to be set to.
   */
  public void setX(double x);

  /**
   * Get the y coordinate for this shape.
   *
   * @return y.
   */
  public double getY();

  /**
   * Set the y coordinate for this shape.
   *
   * @param y double to be set to.
   */
  public void setY(double y);

  /**
   * Get the color for this shape.
   *
   * @return color.
   */
  public Color getColor();

  /**
   * Set the color for this shape.
   *
   * @param c Color to be set to.
   */
  public void setColor(Color c);

  /**
   * Get the width of the rectangle.
   *
   * @return width of this rectangle.
   */
  public double getWidth();

  /**
   * Set the width of the rectangle.
   *
   * @param width new width.
   */
  public void setWidth(double width);

  /**
   * Get the height of the rectangle.
   *
   * @return height of this rectangle.
   */
  public double getHeight();

  /**
   * Set the height of the rectangle.
   *
   * @param height new height.
   */
  public void setHeight(double height);

  /**
   * To String method.
   *
   * @return string representing the name of shape.
   */
  public String toString();

  /**
   * Get the type of Shape.
   *
   * @return string representing the type of the shape.
   */
  public String getType();

  /**
   * Resets the shape to default fields.
   */
  public void reset();
}
