package animator.modeltest;

import org.junit.Test;
import org.junit.Before;

import java.awt.Color;

import animator.model.shapes.Shape;
import animator.model.shapes.Oval;
import animator.model.shapes.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for Shapes.
 */
public class ShapeTest {

  private Shape rect;
  private Shape oval;

  @Before
  public void setup() {
    rect = new Rectangle(50, 51, 100, 200, Color.red, "R");
    oval = new Oval(25, 26, 50, 100, Color.red, "O");
  }

  @Test
  public void testGetMethods() {
    assertEquals(50.0, rect.getX(), 0.01);
    assertEquals(51.0, rect.getY(), 0.01);
    assertEquals(100.0, rect.getWidth(), 0.01);
    assertEquals(200.0, rect.getHeight(), 0.01);
    assertEquals(Color.red, rect.getColor());
    assertEquals("R", rect.toString());
  }

  @Test
  public void testSetMethods() {
    oval.setX(50);
    oval.setY(51);
    oval.setWidth(100);
    oval.setHeight(200);
    oval.setColor(Color.blue);

    assertEquals(50.0, oval.getX(), 0.01);
    assertEquals(51.0, oval.getY(), 0.01);
    assertEquals(100.0, oval.getWidth(), 0.01);
    assertEquals(200.0, oval.getHeight(), 0.01);
    assertEquals(Color.blue, oval.getColor());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRectXConstructorFail() {
    Shape rect2 = new Rectangle(-1, 20, 30, 40, Color.red, "R2");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRectYConstructorFail() {
    Shape rect2 = new Rectangle(1, -20, 30, 40, Color.red, "R2");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRectWidthConstructorFail() {
    Shape rect2 = new Rectangle(1, 20, -30, 40, Color.red, "R2");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRectHeightConstructorFail() {
    Shape rect2 = new Rectangle(1, 20, 30, -40, Color.red, "R2");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRectColorConstructorFail() {
    Shape rect2 = new Rectangle(1, 20, 30, 40, null, "R2");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRectNameConstructorFail() {
    Shape rect2 = new Rectangle(1, 20, 30, 40, Color.red, null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOvalXConstructorFail() {
    Shape oval2 = new Oval(-1, 20, 30, 40, Color.red, "R2");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOvalYConstructorFail() {
    Shape oval2 = new Oval(1, -20, 30, 40, Color.red, "R2");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOvalWidthConstructorFail() {
    Shape oval2 = new Oval(1, 20, -30, 40, Color.red, "R2");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOvalHeightConstructorFail() {
    Shape oval2 = new Oval(1, 20, 30, -40, Color.red, "R2");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOvalColorConstructorFail() {
    Shape oval2 = new Oval(1, 20, 30, 40, null, "R2");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOvalNameConstructorFail() {
    Shape oval2 = new Oval(1, 20, 30, 40, Color.red, null);
  }
}
