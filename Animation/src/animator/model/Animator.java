package animator.model;

import java.util.ArrayList;

import animator.model.shapes.Shape;

/**
 * Interface for an Animator Model, represents an Animation as a list of shapes and a list of moves.
 */
public interface Animator {

  /**
   * Get the list of moves for this animation.
   *
   * @return list of moves.
   */
  public ArrayList<Move> getMoves();

  /**
   * Add a move to this animation if it doesn't overlap with an existing move.
   *
   * @param m move to add.
   */
  public void addMove(Move m);

  /**
   * Remove a move from this animation.
   *
   * @param m move to remove.
   */
  public void removeMove(Move m);

  /**
   * Get the list of Moves to animate.
   */
  public ArrayList<Move> getToAnimate();

  /**
   * Add a move to the list of moves that should be currently animated.
   *
   * @param m move to add.
   */
  public void addToAnimate(Move m);

  /**
   * Get the list of shapes for this animation.
   *
   * @return list of shapes.
   */
  public ArrayList<Shape> getShapes();

  /**
   * Add a shape to this animation if it has a unique name.
   *
   * @param s shape to add.
   */
  public void addShape(Shape s);

  /**
   * Remove a shape from this animation.
   *
   * @param s shape to remove.
   */
  public void removeShape(Shape s);

  /**
   * Return the canvas width.
   *
   * @return width of the canvas for this animation.
   */
  public int getWidth();

  /**
   * Set the canvas width.
   *
   * @param width width to set to.
   */
  public void setWidth(int width);

  /**
   * Return the canvas height.
   *
   * @return height of the canvas for this animation.
   */
  public int getHeight();

  /**
   * Set the canvas height.
   *
   * @param height height to set to.
   */
  public void setHeight(int height);

  /**
   * Get the current state.
   *
   * @return current AnimatorState of this animation.
   */
  public AnimatorState getState();

  /**
   * Set the current state.
   *
   * @param state new state of this animation.
   */
  public void setState(AnimatorState state);

  /**
   * Reset the animation by clearing the toAnimate list.
   */
  public void reset();
}
