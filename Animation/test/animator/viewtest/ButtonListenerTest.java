package animator.viewtest;


import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import animator.model.Animator;
import animator.model.Move;
import animator.model.SimpleAnimator;
import animator.model.shapes.Oval;
import animator.model.shapes.Rectangle;
import animator.model.shapes.Shape;
import animator.view.ButtonListener;
import animator.view.ControllableView;

/**
 * Test class for Button Listener class.
 */
public class ButtonListenerTest {
  private ButtonListener bl1;
  private ButtonListener bl2;

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

    a = new SimpleAnimator(moveList, shapeList, width, height);
    cv = new ControllableView(a, 1000, false);

    bl1 = new ButtonListener("play", cv);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorParam() {
    bl2 = new ButtonListener(null, cv);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorParam2() {
    bl2 = new ButtonListener("play", null);
  }


}