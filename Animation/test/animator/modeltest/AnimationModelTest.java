package animator.modeltest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.ArrayList;

import animator.model.shapes.Oval;
import animator.model.shapes.Rectangle;
import animator.model.shapes.Shape;
import animator.model.Move;
import animator.model.Animator;
import animator.model.SimpleAnimator;

/**
 * Testing class for animation models.
 */
public class AnimationModelTest {
  private Shape rect;
  private Shape oval;
  private Move move1;
  private Move move2;
  private final ArrayList<Move> moveList = new ArrayList<>();
  private final ArrayList<Shape> shapeList = new ArrayList<>();
  private final int width = 500;
  private final int height = 500;

  @Before
  public void setup() {
    rect = new Rectangle(50, 50, 100, 200, Color.red, "R");
    oval = new Oval(25, 25, 50, 100, Color.red, "O");
    move1 = new Move(rect, 1, 10, 55, 55, 100, 200, Color.red);
    move2 = new Move(oval, 5, 15, 55, 55, 100, 200, Color.red);
    moveList.add(move1);
    moveList.add(move2);
    shapeList.add(rect);
    shapeList.add(oval);
  }

  @Test
  public void testToString() {
    Animator a = new SimpleAnimator(moveList, shapeList, width, height);
    assertEquals("motion R 1 50.0 50.0 100.0 200.0 255 0 0     "
            + "10 55 55 100 200 255 0 0\n"
            + "motion O 5 25.0 25.0 50.0 100.0 255 0 0     "
            + "15 55 55 100 200 255 0 0\n", a.toString());
  }

  @Test
  public void testMoveList() {
    Animator a = new SimpleAnimator(new ArrayList<>(), new ArrayList<>(), 500, 500);
    assertEquals(0, a.getMoves().size());
    a.addMove(move1);
    a.addMove(move2);
    assertEquals(2, a.getMoves().size());
  }

  @Test
  public void testShapeList() {
    Animator a = new SimpleAnimator(new ArrayList<>(), new ArrayList<>(), 500, 500);
    assertEquals(0, a.getShapes().size());
    a.addShape(rect);
    a.addShape(oval);
    assertEquals("O", a.getShapes().get(1).toString());
  }

  @Test
  public void testToAnimateList() {
    Animator a = new SimpleAnimator(new ArrayList<>(), new ArrayList<>(), 500, 500);
    assertEquals(0, a.getToAnimate().size());
    a.addToAnimate(move1);
    a.addToAnimate(move2);
    assertEquals(2, a.getToAnimate().size());
  }

  @Test
  public void testVerifyMove() {
    Animator a = new SimpleAnimator(moveList, shapeList, width, height);
    a.addMove(new Move(rect,10, 20, 55, 55, 100, 200, Color.red));
    assertEquals(3, a.getMoves().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveAdd() {
    Animator a = new SimpleAnimator(moveList, shapeList, width, height);
    a.addMove(new Move(rect,2, 11, 55, 55, 100, 200, Color.red));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShapeAdd() {
    Animator a = new SimpleAnimator(moveList, shapeList, width, height);
    a.addShape(new Rectangle(50, 50, 100, 200, Color.red, "R"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMovesConstructor() {
    Animator a = new SimpleAnimator(null, shapeList, width, height);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShapesList() {
    Animator a = new SimpleAnimator(moveList, null, width, height);
  }
}
