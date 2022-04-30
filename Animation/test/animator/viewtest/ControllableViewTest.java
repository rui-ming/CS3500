package animator.viewtest;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;

import animator.model.Animator;
import animator.model.AnimatorState;
import animator.model.Move;
import animator.model.SimpleAnimator;
import animator.model.shapes.Oval;
import animator.model.shapes.Rectangle;
import animator.model.shapes.Shape;
import animator.view.ControllableView;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for Controllable View class.
 */
public class ControllableViewTest {
  private animator.model.shapes.Shape rect;
  private animator.model.shapes.Shape oval;
  private Move move1;
  private Move move2;
  private final ArrayList<Move> moveList = new ArrayList<>();
  private final ArrayList<Shape> shapeList = new ArrayList<>();
  private final int width = 500;
  private final int height = 500;

  Animator a;
  ControllableView cv;

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
  public void reset() {
    a = new SimpleAnimator(moveList, shapeList, width, height);
    cv = new ControllableView(a, 1000, false);
    cv.reset();
    assertEquals(cv.getTickCount(), 0);
    assertEquals(a.getToAnimate().size(), 0);
  }

  @Test
  public void toggleLooping() {
    a = new SimpleAnimator(moveList, shapeList, width, height);
    cv = new ControllableView(a, 1000, false);
    cv.toggleLooping();
    assertEquals(cv.getToggleLooping(), true);
  }

  @Test
  public void play() {
    a = new SimpleAnimator(moveList, shapeList, width, height);
    cv = new ControllableView(a, 1000, false);
    cv.play();
    assertEquals(cv.getAnimator().getState(), AnimatorState.PLAY);
  }

  @Test
  public void pause() {
    a = new SimpleAnimator(moveList, shapeList, width, height);
    cv = new ControllableView(a, 1000, false);
    cv.pause();
    assertEquals(cv.getAnimator().getState(), AnimatorState.PAUSE);
  }

}