package cs3500.freecell.view;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import cs3500.freecell.model.Card;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.SimpleFreecellModel;

import static org.junit.Assert.assertEquals;

/**
 * This is the test for freecell test view.
 */
public class FreecellTextViewTest {
  private FreecellModel<Card> game1;
  private FreecellModel<Card> game2;
  private List<Card> regularDeck;
  private List<Card> testDeck;
  private FreecellView view;
  private FreecellView view1;
  Appendable output;

  @Before
  public void setUp() {
    game1 = new SimpleFreecellModel();
    game2 = new SimpleFreecellModel();
    regularDeck = game1.getDeck();
    testDeck = game2.getDeck();
    Collections.reverse(testDeck);
    view = new FreecellTextView(game1);
    view1 = new FreecellTextView(game2);


  }

  // view's toString is correct when at least one foundation pile or one open pile is not empty
  // toString returns an empty string after a call to startGame has thrown an exception <- fix

  @Test
  public void testToString() {
    assertEquals("", this.view.toString());

    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals("F1:\n" + "F2:\n" + "F3:\n" + "F4:\n"
            + "O1:\n"
            + "C1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
            + "C2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
            + "C3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
            + "C4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠", view.toString());

    // moving king of diamonds to open pile
    this.game1.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    assertEquals("F1:\n" + "F2:\n" + "F3:\n" + "F4:\n"
            + "O1: K♦\n"
            + "C1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n"
            + "C2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
            + "C3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
            + "C4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠", view.toString());

    // moving a card to foundation pile
    this.game2.startGame(this.testDeck, 4, 1, false);
    this.game2.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    assertEquals("F1: A♠\n"
            + "F2:\n" + "F3:\n" + "F4:\n"
            + "O1:\n"
            + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠\n"
            + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C4: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦", view1.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNull() {
    FreecellView view3 = new FreecellTextView(null, output);
  }

  // do i need to test IO exceptions?

  //  @Test
  //  public void testRenderBoard() throws IOException {
  //    try {
  //      this.game2.startGame(this.testDeck, 4, 1, false);
  //      this.game2.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 1);
  //      view1.renderMessage("INVALID MOVE");
  //      assertEquals("", view1.output.toString());
  //    } catch (IllegalArgumentException ioe) {
  //
  //    }
  //  }
  //
  //  @Test
  //  public void testRenderMessage() {
  //
  //  }
}
