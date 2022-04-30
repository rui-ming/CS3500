package cs3500.freecell.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * This is the test for the freecellmodelcreator class.
 */
public class FreecellModelCreatorTest {
  FreecellModelCreator creator;

  @Before
  public void setUp() {
    creator = new FreecellModelCreator();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateNull() {
    creator.create(null);
  }

  //  @Test(expected = IllegalArgumentException.class)
  //  public void testCreateOther() {
  //    creator.create(Suits.DIAMONDS);
  //  }

  @Test
  public void testCreateSingle() {
    creator.create(FreecellModelCreator.GameType.SINGLEMOVE);
    assertEquals(SimpleFreecellModel.class, creator.getClass());
  }

  @Test
  public void testCreateMultiple() {
    creator.create(FreecellModelCreator.GameType.MULTIMOVE);
    assertEquals(MultipleFreecellModel.class, creator.getClass());
  }

}