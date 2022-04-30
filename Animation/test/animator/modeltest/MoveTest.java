package animator.modeltest;

import org.junit.Test;
import org.junit.Before;

import java.awt.Color;

import animator.model.Move;
import animator.model.shapes.Oval;
import animator.model.shapes.Rectangle;
import animator.model.shapes.Shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testing class for Moves.
 */
public class MoveTest {

  private Shape rect;
  private Shape oval;

  @Before
  public void setup() {
    rect = new Rectangle(50, 50, 100, 200, Color.red, "R");
    oval = new Oval(25, 25, 50, 100, Color.red, "O");
  }

  @Test
  public void testGetMethods() {
    Move move1 = new Move(rect, 1, 10, 55, 55, 100,
            200, Color.red);
    assertEquals(1, move1.getFromTick());
    assertEquals(10, move1.getToTick());
    assertEquals(55, move1.getNewX());
    assertEquals(55, move1.getNewY());
    assertEquals(100, move1.getNewWidth());
    assertEquals(200, move1.getNewHeight());
    assertEquals(Color.red, move1.getNewColor());
  }

  @Test
  public void testStart() {
    Move move2 = new Move(oval, 5, 15, 55, 55, 100,
            200, Color.red);
    assertFalse(move2.getActive());
    move2.start();
    assertTrue(move2.getActive());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFromTickConstructorFail() {
    Move move3 = new Move(rect, -1, 10, 55, 55, 100, 200, Color.red);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testToTickConstructorFail() {
    Move move3 = new Move(rect, 1, -10, 55, 55, 100, 200, Color.red);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNewWidthConstructorFail() {
    Move move3 = new Move(rect, -1, 10, 55, 55, -100, 200, Color.red);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNewHeightConstructorFail() {
    Move move3 = new Move(rect, -1, 10, 55, 55, 100, -200, Color.red);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorConstructorFail() {
    Move move3 = new Move(rect, -1, 10, 55, 55, 100, 200, null);
  }
}
