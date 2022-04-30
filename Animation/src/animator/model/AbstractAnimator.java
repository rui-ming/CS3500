package animator.model;

import java.util.ArrayList;

import animator.model.shapes.Shape;

/**
 * Abstract class for an Animator, implements the getter, setter, and add methods for an animator.
 */
public abstract class AbstractAnimator implements Animator {

  private final ArrayList<Move> moves = new ArrayList<>();
  private final ArrayList<Move> toAnimate = new ArrayList<>();
  private ArrayList<Shape> shapes = new ArrayList<>();
  private int width;
  private int height;
  private AnimatorState state;

  @Override
  public ArrayList<Move> getMoves() {
    return new ArrayList<>(moves);
  }

  @Override
  public void addMove(Move m) {
    for (int i = 0; i < moves.size(); i++) {
      if (moves.get(i).getShape().toString().equals(m.getShape().toString())) {
        if (!(moves.get(i).getFromTick() >= m.getToTick()
                || moves.get(i).getToTick() <= m.getFromTick())) {
          throw new IllegalArgumentException("Overlapping Move added: from " + m.getFromTick()
          + " to " + m.getToTick());
        }
      }
    }
    moves.add(m);
  }

  @Override
  public void removeMove(Move m) {
    moves.remove(m);
  }

  @Override
  public ArrayList<Move> getToAnimate() {
    return new ArrayList<>(toAnimate);
  }

  @Override
  public void addToAnimate(Move m) {
    toAnimate.add(m);
  }

  @Override
  public ArrayList<Shape> getShapes() {
    return new ArrayList<>(shapes);
  }

  @Override
  public void addShape(Shape s) {
    for (int i = 0; i < shapes.size(); i++) {
      if (shapes.get(i).toString().equals(s.toString())) {
        throw new IllegalArgumentException("Duplicate named shape added.");
      }
    }

    shapes.add(s);
  }

  @Override
  public void removeShape(Shape s) {
    shapes.remove(s);
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public void setWidth(int width) {
    this.width = width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public AnimatorState getState() {
    return state;
  }

  @Override
  public void setState(AnimatorState state) {
    this.state = state;
  }

  @Override
  public void reset()  {
    this.toAnimate.clear();
    for (int i = 0; i < shapes.size(); i++) {
      shapes.get(i).reset();
    }
  }
}
