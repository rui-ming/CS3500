package animator.viewtest;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;

import animator.model.Animator;
import animator.model.Move;
import animator.model.shapes.Oval;
import animator.model.shapes.Rectangle;
import animator.model.shapes.Shape;
import animator.model.SimpleAnimator;
import animator.view.AnimationViewer;
import animator.view.TextView;
import animator.view.VisualView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testing class for Simple View.
 */
public class TextViewTest {
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
    AnimationViewer av = new TextView(a, 1000);
    assertEquals("motion R 1 50.0 50.0 100.0 200.0 255 0 0     "
            + "10 55 55 100 200 255 0 0\n"
            + "motion O 5 25.0 25.0 50.0 100.0 255 0 0     "
            + "15 55 55 100 200 255 0 0\n", a.toString());
  }

  @Test
  public void testToString2() {
    Animator a = new SimpleAnimator(moveList, shapeList, width, height);
    AnimationViewer av = new TextView(a, 1000);
    a.getShapes().get(0).setX(10);
    assertEquals("motion R 1 10.0 50.0 100.0 200.0 255 0 0     "
            + "10 55 55 100 200 255 0 0\n"
            + "motion O 5 25.0 25.0 50.0 100.0 255 0 0     "
            + "15 55 55 100 200 255 0 0\n", a.toString());
  }

  @Test
  public void testChangeShape() {
    rect.setX(10);
    oval.setX(100);
    assertEquals(10, rect.getX(), .1);
    assertEquals(100, oval.getX(), .1);
  }

  @Test
  public void testStartMove() {
    move1.start();
    move2.start();
    assertTrue(move1.getActive());
    assertTrue(move2.getActive());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegTickRateFailText() {
    Animator a = new SimpleAnimator(moveList, shapeList, width, height);
    AnimationViewer av = new TextView(a, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalVisualViewConstructor() {
    Animator a = new SimpleAnimator(moveList, shapeList, width, height);
    AnimationViewer av = new VisualView(null, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalVisualViewConstructor2() {
    Animator a = new SimpleAnimator(moveList, shapeList, width, height);
    AnimationViewer av = new VisualView(a, -1);
  }

  @Test
  public void testWorkingConstructor() {
    Animator a = new SimpleAnimator(moveList, shapeList, width, height);
    AnimationViewer av = new VisualView(a, 100);
    assertEquals(av.getAnimator(), a);
    assertEquals(av.getTickRate(), 100);
  }

  @Test
  public void testCheckAnimationComplete() throws InterruptedException {
    Animator a = new SimpleAnimator(moveList, shapeList, width, height);
    AnimationViewer av = new VisualView(a, 100);
    assertFalse(av.checkAnimationCompleted());
    av.run();
    assertTrue(av.checkAnimationCompleted());
  }
}