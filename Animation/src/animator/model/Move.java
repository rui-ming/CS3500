package animator.model;

import java.awt.Color;

import animator.model.shapes.Shape;

/**
 * Class representing movement in an animation. Shows where the shape moves to.
 */
public class Move {

  private final Shape shape;
  private final int fromTick;
  private final int toTick;
  private final int newX;
  private final int newY;
  private final int newWidth;
  private final int newHeight;
  private final Color newColor;
  private boolean active;

  /**
   * Move Constructor, takes in the shape to move, the start and end tick, and the new parameters
   * for after the move has completed.
   *
   * @param shape     Shape to move.
   * @param fromTick  When to start the animation.
   * @param toTick    When to end the animation.
   * @param newX      New X position.
   * @param newY      New Y position.
   * @param newWidth  New Width of this shape.
   * @param newHeight New Height of this shape.
   * @param newColor  New Color of this shape.
   * @throws IllegalArgumentException if parameters are invalid
   */
  public Move(Shape shape, int fromTick, int toTick, int newX, int newY,
              int newWidth, int newHeight, Color newColor) {
    if (fromTick >= toTick || fromTick < 0 || newWidth < 1 || newHeight < 1
            || newColor == null) {
      throw new IllegalArgumentException("Illegal arguments");
    }

    this.shape = shape;
    this.fromTick = fromTick;
    this.toTick = toTick;
    this.newX = newX;
    this.newY = newY;
    this.newWidth = newWidth;
    this.newHeight = newHeight;
    this.newColor = newColor;
    this.active = false;
  }

  /**
   * Get the from tick of this Move.
   *
   * @return from tick field.
   */
  public int getFromTick() {
    return fromTick;
  }

  /**
   * Get the to tick of this Move.
   *
   * @return to tick field.
   */
  public int getToTick() {
    return toTick;
  }

  /**
   * Get the Shape to move.
   *
   * @return shape field.
   */
  public Shape getShape() {
    return shape;
  }

  /**
   * Get the new X of this Move.
   *
   * @return new X field.
   */
  public int getNewX() {
    return newX;
  }

  /**
   * Get the new Y of this Move.
   *
   * @return new Y field.
   */
  public int getNewY() {
    return newY;
  }

  /**
   * Get the new width of this Move.
   *
   * @return new width field.
   */
  public int getNewWidth() {
    return newWidth;
  }

  /**
   * Get the new height of this Move.
   *
   * @return new height field.
   */
  public int getNewHeight() {
    return newHeight;
  }

  /**
   * Get the new color of this Move.
   *
   * @return new color field.
   */
  public Color getNewColor() {
    return newColor;
  }

  /**
   * Get the started boolean of this Move.
   *
   * @return started field.
   */
  public boolean getActive() {
    return active;
  }

  /**
   * Start this move, set the active boolean to true.
   */
  public void start() {
    active = true;
  }

  /**
   * End this move, set the active boolean to false.
   */
  public void end() {
    active = false;
  }
}
