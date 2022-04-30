package animator.io;

import java.awt.Color;
import java.util.ArrayList;

import animator.model.Animator;
import animator.model.Move;
import animator.model.SimpleAnimator;
import animator.model.shapes.Oval;
import animator.model.shapes.Rectangle;
import animator.model.shapes.Shape;

/**
 * Model Builder implementation of TweenModelBuilder, allows us to create a model based on
 * the information given.
 */
public class ModelBuilder implements TweenModelBuilder<Animator> {

  private final ArrayList<Shape> shapes;
  private final ArrayList<Move> moves;
  private final int width;
  private final int height;

  /**
   * Constructor for a Model Builder.
   *
   * @param shapes list of shapes.
   * @param moves  list of moves.
   * @param width  canvas width.
   * @param height canvas height.
   */
  public ModelBuilder(ArrayList<Shape> shapes, ArrayList<Move> moves, int width,
                      int height) {
    this.shapes = shapes;
    this.moves = moves;
    this.width = width;
    this.height = height;
  }

  @Override
  public TweenModelBuilder<Animator> setBounds(int width, int height) {
    return new ModelBuilder(shapes, moves, width, height);
  }

  @Override
  public TweenModelBuilder<Animator> addOval(String name, float cx, float cy, float xRadius,
                                             float yRadius, float red, float green, float blue,
                                             int startOfLife, int endOfLife) {
    shapes.add(new Oval(cx, cy, 2 * xRadius, 2 * yRadius, new Color(red, green, blue), name));
    return new ModelBuilder(shapes, moves, width, height);
  }

  @Override
  public TweenModelBuilder<Animator> addRectangle(String name, float lx, float ly, float width,
                                                  float height, float red, float green, float blue,
                                                  int startOfLife, int endOfLife) {
    shapes.add(new Rectangle(lx, ly, width, height, new Color(red, green, blue), name));
    return new ModelBuilder(shapes, moves, this.width, this.height);
  }

  @Override
  public TweenModelBuilder<Animator> addMove(String name, float moveFromX, float moveFromY,
                                             float moveToX, float moveToY, int startTime,
                                             int endTime) {
    Shape s = null;
    for (int i = 0; i < shapes.size(); i++) {
      if (name.equals(shapes.get(i).toString())) {
        s = shapes.get(i);
      }
    }
    if (s != null) {
      moves.add(new Move(s, startTime, endTime, (int) moveToX, (int) moveToY,
              (int) s.getWidth(), (int) s.getHeight(), s.getColor()));
    }
    return new ModelBuilder(shapes, moves, this.width, this.height);
  }

  @Override
  public TweenModelBuilder<Animator> addColorChange(String name, float oldR, float oldG,
                                                    float oldB, float newR, float newG,
                                                    float newB, int startTime, int endTime) {
    Shape s = null;
    for (int i = 0; i < shapes.size(); i++) {
      if (name.equals(shapes.get(i).toString())) {
        s = shapes.get(i);
      }
    }
    if (s != null) {
      moves.add(new Move(s, startTime, endTime, (int) s.getX(), (int) s.getY(),
              (int) s.getWidth(), (int) s.getHeight(), new Color(newR, newG, newB)));
    }
    return new ModelBuilder(shapes, moves, this.width, this.height);
  }

  @Override
  public TweenModelBuilder<Animator> addScaleToChange(String name, float fromSx, float fromSy,
                                                      float toSx, float toSy, int startTime,
                                                      int endTime) {
    Shape s = null;
    for (int i = 0; i < shapes.size(); i++) {
      if (name.equals(shapes.get(i).toString())) {
        s = shapes.get(i);
      }
    }
    if (s != null) {
      moves.add(new Move(s, startTime, endTime, (int) s.getX(), (int) s.getY(),
              (int) toSx, (int) toSy, s.getColor()));
    }
    return new ModelBuilder(shapes, moves, this.width, this.height);
  }

  @Override
  public Animator build() {
    return new SimpleAnimator(moves, shapes, width, height);
  }
}
