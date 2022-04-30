package animator.viewtest;

import org.junit.Test;

import animator.view.TextPanel;

/**
 * Testing class for text panel class.
 */
public class TextPanelTest {

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    TextPanel av = new TextPanel(null);
  }

}