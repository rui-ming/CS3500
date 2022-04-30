import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.Collections;
import java.util.List;

import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.Card;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.MultipleFreecellModel;
import cs3500.freecell.model.SimpleFreecellModel;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test cases for the freecell controller.
 */
public class FreecellControllerTest {
  // your tests go here
  private FreecellController<Card> controller;
  private Readable rd;
  private Appendable ap;
  private FreecellModel<Card> game1;
  private List<Card> regularDeck;
  private FreecellController<Card> controller1;
  private FreecellModel<Card> game2;


  @Before
  public void setUp() {
    game1 = new SimpleFreecellModel();
    game2 = new MultipleFreecellModel();
    controller = new SimpleFreecellController(game1, new StringReader(""), System.out);
    FreecellView view = new FreecellTextView(game1);
    controller1 =
            new SimpleFreecellController(game2, new StringReader(""), System.out);

  }

  @Test
  public void testControllerForMultiMoveModel() {
    ap = new StringBuffer();
    rd = new StringReader("C1 12 F1 C1 11 F1 C1 10 F1 C3 10 C1");
    controller1 = new SimpleFreecellController(game2, rd, ap);
    List<Card> testDeck = game2.getDeck();
    Collections.reverse(testDeck);
    controller1.playGame(testDeck, 4, 3, false);
    FreecellView view = new FreecellTextView(game2);
    System.out.print(view.toString());
    assertEquals("", ap.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    StringBuilder output = new StringBuilder();
    FreecellController<Card> control = new SimpleFreecellController(null,
            new StringReader("C3 13 01 q"), output);
    control.playGame(game1.getDeck(), 4, 1, false);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRD() {
    new SimpleFreecellController(game1, null, new StringBuilder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAP() {
    new SimpleFreecellController(game1, rd, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDeck() {
    // playGame will throw an IllegalArgumentException if the deck is null
    controller.playGame(regularDeck, 4, 0, false);
  }

  @Test
  public void testPlayGameInvalidParameters() {
    // playGame writes the message "Could not start game." when it is called with
    // invalid game parameters (e.g. too few cascade or open piles)
    ap = new StringBuffer();
    regularDeck = game1.getDeck();
    rd = new StringReader("C4 15 O1");
    controller = new SimpleFreecellController(game1, rd, ap);
    regularDeck = game1.getDeck();
    controller.playGame(game1.getDeck(), -2, 0, false);
    assertEquals(ap.toString(), "Could not start game.");
  }

  @Test
  public void testPlayGameInvalidParametersPT2() {
    // playGame writes the message "Could not start game." when it is called with
    // invalid game parameters (e.g. too few cascade or open piles)
    ap = new StringBuffer();
    regularDeck = game1.getDeck();
    rd = new StringReader("C4 15 O1");
    controller = new SimpleFreecellController(game1, rd, ap);
    controller.playGame(regularDeck, 4, -1, false);
    assertEquals(ap.toString(), "Could not start game.");
  }

  @Test
  public void testPlayGameIndex() {
    // playGame asks for only the card index if the source pile was entered correctly but
    // the card index was not entered correctly

    ap = new StringBuffer();
    rd = new StringReader("C4 15 O1");
    controller = new SimpleFreecellController(game1, rd, ap);
    controller.playGame(game1.getDeck(), 4, 1, false);
    assertEquals("Invalid MOVE\n", ap.toString());
  }

  @Test
  public void testQuitPlayGameSource() {
    // playGame returns when you provide an input of 'q' or 'Q' for the source pile
    ap = new StringBuffer();
    rd = new StringReader("q 3 O1");
    controller = new SimpleFreecellController(game1, rd, ap);
    controller.playGame(game1.getDeck(), 4, 1, false);
    assertEquals("Game quit prematurely.\n", ap.toString());
  }

  @Test
  public void testQuitPlayGameSourcePT2() {
    // playGame returns when you provide an input of 'q' or 'Q' for the source pile
    ap = new StringBuffer();
    rd = new StringReader("Q 3 O1");
    controller = new SimpleFreecellController(game1, rd, ap);
    controller.playGame(game1.getDeck(), 4, 1, false);
    assertEquals("Game quit prematurely.\n", ap.toString());
  }

  @Test
  public void testQuitPlayGameCardIndex() {
    // playGame returns when you provide an input of 'q' or 'Q' for the card index
    ap = new StringBuffer();
    rd = new StringReader("C3 q O1");
    controller = new SimpleFreecellController(game1, rd, ap);
    controller.playGame(game1.getDeck(), 4, 1, false);
    assertEquals("Game quit prematurely.\n", ap.toString());
  }

  @Test
  public void testQuitPlayGameCardIndexPT2() {
    // playGame returns when you provide an input of 'q' or 'Q' for the card index
    ap = new StringBuffer();
    rd = new StringReader("C3 Q O1");
    controller = new SimpleFreecellController(game1, rd, ap);
    controller.playGame(game1.getDeck(), 4, 1, false);
    assertEquals("Game quit prematurely.\n", ap.toString());
  }

  @Test
  public void testInvalidSourcePile() {
    // controller correctly rejects an invalid source pile specifier (e.g. 'xf54')
    ap = new StringBuffer();
    rd = new StringReader("K2");
    controller = new SimpleFreecellController(game1, rd, ap);
    controller.playGame(game1.getDeck(), 4, 1, false);
    assertEquals("Invalid Input for Source Pile\n", ap.toString());
  }

  @Test
  public void testInvalidCardIndex() {
    // test to verify that the controller correctly rejects an invalid card
    // index specifier (e.g. 'z4$b')
    ap = new StringBuffer();
    rd = new StringReader("C2 nh O1");
    controller = new SimpleFreecellController(game1, rd, ap);
    controller.playGame(game1.getDeck(), 4, 1, false);
    assertEquals("Invalid Input for Card Index\nInvalid MOVE\n", ap.toString());
  }

  @Test
  public void testInvalidDestinationPile() {
    // controller correctly rejects an invalid destination pile specifier (e.g. 'yx!n')
    ap = new StringBuffer();
    rd = new StringReader("C2 12 A3");
    controller = new SimpleFreecellController(game1, rd, ap);
    controller.playGame(game1.getDeck(), 4, 1, false);
    assertEquals("Invalid Input for Destination Pile\nInvalid MOVE\n", ap.toString());
  }

  @Test
  public void testInvalidCTCMove() {
    // playGame writes the message "Invalid move. Try again." (or similar message)
    // when an invalid move, as determined by the *model*, is attempted
    // test invalid move from cascade to cascade
    ap = new StringBuffer();
    rd = new StringReader("C2 12 C3");
    controller = new SimpleFreecellController(game1, rd, ap);
    controller.playGame(game1.getDeck(), 4, 1, false);
    assertEquals("Invalid MOVE\n", ap.toString());
  }

  @Test
  public void testInvalidFTOMove() {
    // playGame writes the message "Invalid move. Try again." (or similar message)
    // when an invalid move, as determined by the *model*, is attempted
    // test invalid move from cascade to cascade
    ap = new StringBuffer();
    rd = new StringReader("F1 1 O1");
    controller = new SimpleFreecellController(game1, rd, ap);
    controller.playGame(game1.getDeck(), 4, 1, false);
    assertEquals("Invalid MOVE\n", ap.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayGameCannotReadRD() {
    // playGame correctly throws an IllegalStateException if it cannot
    // read from the Readable while the game is in progress
    // test empty readable
    ap = new StringBuffer();
    rd = new StringReader("");
    controller = new SimpleFreecellController(game1, rd, ap);
    controller.playGame(game1.getDeck(), 4, 1, false);
  }

  //  @Test(expected = IllegalStateException.class)
  //  public void testPlayGameCannotReadRDPT2() {
  //    // playGame correctly throws an IllegalStateException if it cannot append to the
  //    // Appendable while the game is in progress (i.e., an IOException occurs)
  //    ap = new StringBuffer();
  //    rd = new StringReader("C1 12 O1");
  //    Collections.reverse(game1.getDeck());
  //    // rd.append("O1 1 C2");
  //    controller = new SimpleFreecellController(game1, rd, ap);
  //    controller.playGame(game1.getDeck(), 4, 1, false);
  //  }

  @Test
  public void testStartNewGame() {
    //playGame works correctly by starting a new game and completing it successfully
    ap = new StringBuffer();
    rd = new StringReader("Q");
    controller = new SimpleFreecellController(game1, rd, ap);
    controller.playGame(game1.getDeck(), 4, 1, false);
    rd = new StringReader("C1 13 O1");
    controller = new SimpleFreecellController(game1, rd, ap);
    controller.playGame(game1.getDeck(), 4, 1, false);
    assertEquals(ap.toString(), "Game quit prematurely.\n");
  }

  @Test
  public void testStartNewGame2() {
    //playGame works correctly by starting a new game and completing it successfully,
    // including invalid inputs
    ap = new StringBuffer();
    rd = new StringReader("C3 7 O1 Q");
    controller = new SimpleFreecellController(game1, rd, ap);
    controller.playGame(game1.getDeck(), 8, 4, false);
    rd = new StringReader("xde");
    controller = new SimpleFreecellController(game1, rd, ap);
    controller.playGame(game1.getDeck(), 8, 4, false);
    rd = new StringReader("C2 7 C6");
    controller = new SimpleFreecellController(game1, rd, ap);
    controller.playGame(game1.getDeck(), 8, 4, false);
    assertEquals(ap.toString(),
            "Game quit prematurely.\nInvalid Input for Source Pile\nInvalid MOVE\n");
  }


}
