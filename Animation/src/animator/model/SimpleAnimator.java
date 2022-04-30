package animator.model;

import java.util.ArrayList;

import animator.model.shapes.Shape;

/**
 * Simple Animator Class that implements the Animator Interface. Allows for simple animations
 * that move onTick.
 */
public class SimpleAnimator extends AbstractAnimator {

  /**
   * Contructor for a simple animation given a list of moves and shapes.
   *
   * @param moves  list of moves.
   * @param shapes list of shapes in the animation.
   * @param width  canvas width.
   * @param height canvas height.
   * @throws IllegalArgumentException if parameters are null.
   */
  public SimpleAnimator(ArrayList<Move> moves, ArrayList<Shape> shapes, int width, int height) {
    if (moves == null || shapes == null) {
      throw new IllegalArgumentException("Invalid move or shape list");
    }
    if (width < 1 || height < 1) {
      throw new IllegalArgumentException("Invalid canvas size");
    }

    for (int i = 0; i < moves.size(); i++) {
      addMove(moves.get(i));
    }

    for (int i = 0; i < shapes.size(); i++) {
      addShape(shapes.get(i));
    }

    setWidth(width);
    setHeight(height);
    setState(AnimatorState.PLAY);
  }

  @Override
  public String toString() {
    StringBuilder view = new StringBuilder();

    for (Move m : getMoves()) {
      Shape s = m.getShape();
      view.append("motion ");
      view.append(s.toString()).append(" ").append(m.getFromTick()).append(" ").append(s.getX())
              .append(" ").append(s.getY()).append(" ").append(s.getWidth()).append(" ")
              .append(s.getHeight()).append(" ").append(s.getColor().getRed())
              .append(" ").append(s.getColor().getGreen()).append(" ")
              .append(s.getColor().getBlue()).append("     ");
      view.append(m.getToTick()).append(" ").append(m.getNewX()).append(" ")
              .append(m.getNewY()).append(" ").append(m.getNewWidth()).append(" ")
              .append(m.getNewHeight()).append(" ").append(m.getNewColor().getRed())
              .append(" ").append(m.getNewColor().getGreen()).append(" ")
              .append(m.getNewColor().getBlue());
      view.append("\n");
    }

    return view.toString();
  }
}
