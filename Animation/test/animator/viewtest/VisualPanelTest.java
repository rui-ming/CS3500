package animator.viewtest;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;

import animator.model.Animator;
import animator.model.Move;
import animator.model.SimpleAnimator;
import animator.model.shapes.Oval;
import animator.model.shapes.Rectangle;
import animator.model.shapes.Shape;
import animator.view.VisualPanel;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for visual panel class.
 */
public class VisualPanelTest {
  private animator.model.shapes.Shape rect;
  private animator.model.shapes.Shape oval;
  private Move move1;
  private Move move2;
  private final ArrayList<Move> moveList = new ArrayList<>();
  private final ArrayList<Shape> shapeList = new ArrayList<>();
  private final int width = 500;
  private final int height = 500;

  Animator a;
  VisualPanel vp;

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

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorParam() {
    vp = new VisualPanel(null);
  }

  @Test
  public void getAnimator() {
    a = new SimpleAnimator(moveList, shapeList, width, height);
    vp = new VisualPanel(a);
    assertEquals(vp.getAnimator(), a);
  }
}