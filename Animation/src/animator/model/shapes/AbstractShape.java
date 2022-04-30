package animator.model.shapes;

import java.awt.Color;

/**
 * Abstract class for shapes, implements shapes, extend for more specific shapes.
 */
public abstract class AbstractShape implements Shape {

  private Color color;
  private double x;
  private double y;
  private double width;
  private double height;

  @Override
  public double getX() {
    return x;
  }

  @Override
  public void setX(double x) {
    this.x = x;
  }

  @Override
  public double getY() {
    return y;
  }

  @Override
  public void setY(double y) {
    this.y = y;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public void setColor(Color c) {
    this.color = c;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public void setWidth(double width) {
    this.width = width;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public void setHeight(double height) {
    this.height = height;
  }

  @Override
  public abstract String getType();

  @Override
  public abstract void reset();
}
