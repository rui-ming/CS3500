import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs3500.freecell.model.Card;
import cs3500.freecell.model.Color;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.SimpleFreecellModel;
import cs3500.freecell.model.Suits;
import cs3500.freecell.model.Values;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * JUnit test cases for the freecell model.
 */
public class FreecellModelTest {
  // your tests go here

  private Card card1;
  private FreecellModel<Card> game1;
  private FreecellModel<Card> game2;
  private FreecellModel<Card> game3;

  private List<Card> smallDeck;
  private List<Card> bigDeck;
  private List<Card> regularDeck;
  private List<Card> shuffleDeck;
  private List<Card> testDeck;


  @Before
  public void setUp() {
    card1 = new Card(Values.ACE, Suits.HEARTS);
    smallDeck = new ArrayList<Card>();
    bigDeck = new ArrayList<Card>();
    testDeck = new ArrayList<Card>();

    game1 = new SimpleFreecellModel();
    regularDeck = game1.getDeck();

    game2 = new SimpleFreecellModel();
    shuffleDeck = game2.getDeck();

    game3 = new SimpleFreecellModel();
    testDeck = game3.getDeck();
    Collections.reverse(testDeck);

    // deck is smaller than 52 cards
    for (int i = 0; i < 30; i++) {
      smallDeck.add(card1);
    }
    // deck is greater than 52 cards
    for (int j = 0; j < 60; j++) {
      bigDeck.add(new Card(Values.ACE, Suits.DIAMONDS));
    }

  }

  @Test
  public void testGetDeck() {
    assertEquals(52, regularDeck.size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBigDecks() {
    // test for too big of a deck
    this.game1.startGame(this.bigDeck, 4, 1, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSmallDecks() {
    // test for too small of a deck
    this.game1.startGame(this.smallDeck, 4, 1, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testForDuplicates() {
    // test for duplicates
    this.regularDeck.add(card1);
    this.game1.startGame(this.regularDeck, 4, 1, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCascadePileNumbers() {
    // test for invalid cascade pile numbers
    this.game1.startGame(this.regularDeck, 3, 1, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOpenPileNumber() {
    // test for invalid open pile number
    this.game1.startGame(this.regularDeck, 4, 0, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOpenPileNumberPT2() {
    // test for invalid open pile number
    this.game1.startGame(this.regularDeck, 4, -1, true);
  }

  @Test
  public void testStartGame() {
    this.game1.startGame(this.regularDeck, 4, 1, false);

    // checks for the number of cards in foundation piles
    assertEquals(0, game1.getNumCardsInFoundationPile(0));
    assertEquals(0, game1.getNumCardsInFoundationPile(1));
    assertEquals(0, game1.getNumCardsInFoundationPile(2));
    assertEquals(0, game1.getNumCardsInFoundationPile(3));

    // number of cascade piles returned by the model is correct after a game has been started
    assertEquals(4, this.game1.getNumCascadePiles());

    // checks for number of cascade piles
    assertEquals(4, game1.getNumCascadePiles());
    // checks each cascade pile has correct number of cards
    assertEquals(13, this.game1.getNumCardsInCascadePile(0));
    assertEquals(13, this.game1.getNumCardsInCascadePile(1));
    assertEquals(13, this.game1.getNumCardsInCascadePile(2));
    assertEquals(13, this.game1.getNumCardsInCascadePile(3));

    // checks for number cards in open piles
    assertEquals(0, this.game1.getNumCardsInOpenPile(0));

    // open piles returned by the model is correct after a game has been started
    assertEquals(1, this.game1.getNumOpenPiles());

    // checking that when shuffle is true, first card is not the same as first card in regular deck
    this.game2.startGame(this.shuffleDeck, 4, 1, true);
    assertFalse(this.regularDeck.get(0).equals(game2.getCascadeCardAt(0, 0)));

    // test isGameOver returns false when the game is in progress
    assertEquals(false, this.game1.isGameOver());
  }

  // test startGame when a game is already in progress restarts the game correctly
  @Test
  public void testStartGameRestartsGameCorrectly() {
    this.game1.startGame(this.regularDeck, 4, 1, false);

    // checks for the number of cards in foundation piles
    assertEquals(0, game1.getNumCardsInFoundationPile(0));
    assertEquals(0, game1.getNumCardsInFoundationPile(1));
    assertEquals(0, game1.getNumCardsInFoundationPile(2));
    assertEquals(0, game1.getNumCardsInFoundationPile(3));

    // checks for number of cascade piles
    assertEquals(4, game1.getNumCascadePiles());
    // checks each cascade pile has correct number of cards
    assertEquals(13, this.game1.getNumCardsInCascadePile(0));
    assertEquals(13, this.game1.getNumCardsInCascadePile(1));
    assertEquals(13, this.game1.getNumCardsInCascadePile(2));
    assertEquals(13, this.game1.getNumCardsInCascadePile(3));

    // checks for number cards in open piles
    assertEquals(0, this.game1.getNumCardsInOpenPile(0));

    this.game1.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    // checks for the number of cards in foundation piles
    assertEquals(0, game1.getNumCardsInFoundationPile(0));
    assertEquals(0, game1.getNumCardsInFoundationPile(1));
    assertEquals(0, game1.getNumCardsInFoundationPile(2));
    assertEquals(0, game1.getNumCardsInFoundationPile(3));

    // checks for number of cascade piles
    assertEquals(4, game1.getNumCascadePiles());
    // checks each cascade pile has correct number of cards
    assertEquals(12, this.game1.getNumCardsInCascadePile(0));
    assertEquals(13, this.game1.getNumCardsInCascadePile(1));
    assertEquals(13, this.game1.getNumCardsInCascadePile(2));
    assertEquals(13, this.game1.getNumCardsInCascadePile(3));

    // checks for number cards in open piles
    assertEquals(1, this.game1.getNumCardsInOpenPile(0));

    // restart game
    this.game1.startGame(this.regularDeck, 4, 1, false);
    // checks for the number of cards in foundation piles
    assertEquals(0, game1.getNumCardsInFoundationPile(0));
    assertEquals(0, game1.getNumCardsInFoundationPile(1));
    assertEquals(0, game1.getNumCardsInFoundationPile(2));
    assertEquals(0, game1.getNumCardsInFoundationPile(3));

    // checks for number of cascade piles
    assertEquals(4, game1.getNumCascadePiles());
    // checks each cascade pile has correct number of cards
    assertEquals(13, this.game1.getNumCardsInCascadePile(0));
    assertEquals(13, this.game1.getNumCardsInCascadePile(1));
    assertEquals(13, this.game1.getNumCardsInCascadePile(2));
    assertEquals(13, this.game1.getNumCardsInCascadePile(3));

    // checks for number cards in open piles
    assertEquals(0, this.game1.getNumCardsInOpenPile(0));

  }

  // test move
  //  IllegalArgumentException when a card is moved to a foundation pile, and it is an invalid move
  // IllegalArgumentException when a card is moved to an open pile that already contains a card

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveToFoundation() {
    this.game1.startGame(this.regularDeck, 4, 1, false);
    this.game1.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveToOpen() {
    this.game1.startGame(this.regularDeck, 4, 1, false);
    this.game1.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    this.game1.move(PileType.CASCADE, 0, 11, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMultiMoveForSingleFreecellModel() {
    this.game1.startGame(this.testDeck, 4, 6, true);
    this.game1.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    this.game1.move(PileType.CASCADE, 1, 12, PileType.FOUNDATION, 1);
    this.game1.move(PileType.CASCADE, 1, 11, PileType.FOUNDATION, 1);
    this.game1.move(PileType.CASCADE, 1, 10, PileType.FOUNDATION, 1);
    // should not work because game1 is a simplefreecell model rather than a multi move model
    this.game1.move(PileType.CASCADE, 2, 10, PileType.CASCADE, 1);
  }

  @Test
  public void testMove() {
    this.game1.startGame(this.regularDeck, 4, 1, false);
    // moving from cascade to open
    // moved last card should be K diamond to open spot
    this.game1.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    FreecellView view1 = new FreecellTextView(game1);
    //  System.out.print(view1.toString());

    // check that it move to open pile
    assertEquals(13, this.game1.getOpenCardAt(0).returnStringValue());
    assertEquals("♦", this.game1.getOpenCardAt(0).returnSuit());
    // check that card got removed from cascade pile
    assertEquals("♦", this.game1.getCascadeCardAt(0,
            game1.getNumCardsInCascadePile(0) - 1).returnSuit());
    assertEquals(12, this.game1.getCascadeCardAt(0,
            game1.getNumCardsInCascadePile(0) - 1).returnStringValue());

    // moving from cascade to cascade
    // move Q diamond to end of 2nd cascade pile
    this.game1.move(PileType.CASCADE, 0, 11, PileType.CASCADE, 1);
    assertEquals("Q♦", this.game1.getCascadeCardAt(1, 13).toString());
    // card before must be king
    assertEquals("K♣", this.game1.getCascadeCardAt(1, 12).toString());
    // previous color must be alternating
    assertEquals(Color.RED, this.game1.getCascadeCardAt(1, 13).returnColor());
    assertEquals(Color.BLACK, this.game1.getCascadeCardAt(1, 12).returnColor());
    // check that card got removed from cascade pile
    assertEquals("J♦", this.game1.getCascadeCardAt(0,
            game1.getNumCardsInCascadePile(0) - 1).toString());

    // moving from cascade to foundation
    // moving A club to foundation pile
    this.game3.startGame(this.testDeck, 4, 1, false);
    FreecellView view2 = new FreecellTextView(game3);
    this.game3.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    assertEquals("A♠", this.game3.getFoundationCardAt(0, 0).toString());
    // check that card is not in cascade deck anymore
    assertEquals("2♠", this.game3.getCascadeCardAt(0, 11).toString());

    // moving from open to foundation
    // move A heart to open pile
    this.game3.move(PileType.CASCADE, 1, 12, PileType.OPEN, 0);
    // check it is there
    assertEquals("A♥", this.game3.getOpenCardAt(0).toString());
    // check that is it gone from cascade pile
    assertEquals("2♥", this.game3.getCascadeCardAt(1, 11).toString());
    // move A hearts from open to foundation pile 1
    this.game3.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 1);
    // check that A clubs is in foundation pile
    assertEquals("A♥", this.game3.getFoundationCardAt(1, 0).toString());
    // check that A clubs is no more in open pile
    assertEquals(0, this.game3.getNumCardsInOpenPile(0));

    // moving from open to cascade
    // move A clubs to open pile
    this.game3.move(PileType.CASCADE, 2, 12, PileType.OPEN, 0);
    // check that it is at open pile
    assertEquals("A♣", this.game3.getOpenCardAt(0).toString());
    // check that it is no longer in cascade pile
    assertEquals("2♣", this.game3.getCascadeCardAt(2, 11).toString());
    // move A clubs to cascade pile 1
    this.game3.move(PileType.OPEN, 0, 0, PileType.CASCADE, 1);
    // check that it is there
    assertEquals("A♣", this.game3.getCascadeCardAt(1, 12).toString());
    // check that it is no longer in open pile
    assertEquals(0, this.game3.getNumCardsInOpenPile(0));
    // System.out.print(view2.toString());

    // moving from open to open
    this.game2.startGame(this.testDeck, 4, 2, false);
    FreecellView view3 = new FreecellTextView(game2);
    // move A spades to open pile
    this.game2.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    System.out.print(view3.toString());
    // check that A spades is at open pile
    assertEquals("A♠", this.game2.getOpenCardAt(0).toString());

    // check that it is no longer in cascade pile
    assertEquals("2♠", this.game2.getCascadeCardAt(0, 11).toString());
    // move A spades to another open pile
    this.game2.move(PileType.OPEN, 0, 0, PileType.OPEN, 1);
    // check that A diamond is at open pile
    assertEquals("A♠", this.game2.getOpenCardAt(1).toString());
    // check that it is no longer in open pile
    assertEquals(0, this.game2.getNumCardsInOpenPile(0));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove() {
    // before game started
    this.game1.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 0);

    // after game
    this.game1.startGame(this.regularDeck, 4, 1, true);
    this.game1.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 0);
    this.game1.move(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 0);

  }

  @Test
  public void testIsGameOver() {
    this.game1.startGame(this.testDeck, 4, 1, false);
    // moving all diamonds
    this.game1.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    this.game1.move(PileType.CASCADE, 0, 11, PileType.FOUNDATION, 0);
    this.game1.move(PileType.CASCADE, 0, 10, PileType.FOUNDATION, 0);
    this.game1.move(PileType.CASCADE, 0, 9, PileType.FOUNDATION, 0);
    this.game1.move(PileType.CASCADE, 0, 8, PileType.FOUNDATION, 0);
    this.game1.move(PileType.CASCADE, 0, 7, PileType.FOUNDATION, 0);
    this.game1.move(PileType.CASCADE, 0, 6, PileType.FOUNDATION, 0);
    this.game1.move(PileType.CASCADE, 0, 5, PileType.FOUNDATION, 0);
    this.game1.move(PileType.CASCADE, 0, 4, PileType.FOUNDATION, 0);
    this.game1.move(PileType.CASCADE, 0, 3, PileType.FOUNDATION, 0);
    this.game1.move(PileType.CASCADE, 0, 2, PileType.FOUNDATION, 0);
    this.game1.move(PileType.CASCADE, 0, 1, PileType.FOUNDATION, 0);
    this.game1.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    // moving all clubs
    this.game1.move(PileType.CASCADE, 1, 12, PileType.FOUNDATION, 1);
    this.game1.move(PileType.CASCADE, 1, 11, PileType.FOUNDATION, 1);
    this.game1.move(PileType.CASCADE, 1, 10, PileType.FOUNDATION, 1);
    this.game1.move(PileType.CASCADE, 1, 9, PileType.FOUNDATION, 1);
    this.game1.move(PileType.CASCADE, 1, 8, PileType.FOUNDATION, 1);
    this.game1.move(PileType.CASCADE, 1, 7, PileType.FOUNDATION, 1);
    this.game1.move(PileType.CASCADE, 1, 6, PileType.FOUNDATION, 1);
    this.game1.move(PileType.CASCADE, 1, 5, PileType.FOUNDATION, 1);
    this.game1.move(PileType.CASCADE, 1, 4, PileType.FOUNDATION, 1);
    this.game1.move(PileType.CASCADE, 1, 3, PileType.FOUNDATION, 1);
    this.game1.move(PileType.CASCADE, 1, 2, PileType.FOUNDATION, 1);
    this.game1.move(PileType.CASCADE, 1, 1, PileType.FOUNDATION, 1);
    this.game1.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 1);
    // moving all hearts
    this.game1.move(PileType.CASCADE, 2, 12, PileType.FOUNDATION, 2);
    this.game1.move(PileType.CASCADE, 2, 11, PileType.FOUNDATION, 2);
    this.game1.move(PileType.CASCADE, 2, 10, PileType.FOUNDATION, 2);
    this.game1.move(PileType.CASCADE, 2, 9, PileType.FOUNDATION, 2);
    this.game1.move(PileType.CASCADE, 2, 8, PileType.FOUNDATION, 2);
    this.game1.move(PileType.CASCADE, 2, 7, PileType.FOUNDATION, 2);
    this.game1.move(PileType.CASCADE, 2, 6, PileType.FOUNDATION, 2);
    this.game1.move(PileType.CASCADE, 2, 5, PileType.FOUNDATION, 2);
    this.game1.move(PileType.CASCADE, 2, 4, PileType.FOUNDATION, 2);
    this.game1.move(PileType.CASCADE, 2, 3, PileType.FOUNDATION, 2);
    this.game1.move(PileType.CASCADE, 2, 2, PileType.FOUNDATION, 2);
    this.game1.move(PileType.CASCADE, 2, 1, PileType.FOUNDATION, 2);
    this.game1.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 2);
    // moving all clubs
    this.game1.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 3);
    this.game1.move(PileType.CASCADE, 3, 11, PileType.FOUNDATION, 3);
    this.game1.move(PileType.CASCADE, 3, 10, PileType.FOUNDATION, 3);
    this.game1.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 3);
    this.game1.move(PileType.CASCADE, 3, 8, PileType.FOUNDATION, 3);
    this.game1.move(PileType.CASCADE, 3, 7, PileType.FOUNDATION, 3);
    this.game1.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 3);
    this.game1.move(PileType.CASCADE, 3, 5, PileType.FOUNDATION, 3);
    this.game1.move(PileType.CASCADE, 3, 4, PileType.FOUNDATION, 3);
    this.game1.move(PileType.CASCADE, 3, 3, PileType.FOUNDATION, 3);
    this.game1.move(PileType.CASCADE, 3, 2, PileType.FOUNDATION, 3);
    this.game1.move(PileType.CASCADE, 3, 1, PileType.FOUNDATION, 3);
    this.game1.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 3);

    assertEquals(52, this.game1.getNumCardsInFoundationPile(0)
            + this.game1.getNumCardsInFoundationPile(1)
            + this.game1.getNumCardsInFoundationPile(2)
            + this.game1.getNumCardsInFoundationPile(3));

    FreecellView view = new FreecellTextView(game1);
    System.out.print(view.toString());

    assertEquals(true, game1.isGameOver());
  }

  @Test
  public void testGetNumCardsInFoundationPile() {
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(0, this.game1.getNumCardsInFoundationPile(0));
    assertEquals(0, this.game1.getNumCardsInFoundationPile(1));
    assertEquals(0, this.game1.getNumCardsInFoundationPile(2));
    assertEquals(0, this.game1.getNumCardsInFoundationPile(3));
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidIndexFoundationPile() {
    // before game has started
    // for getNumCardsInFoundationPile()
    this.game1.getNumCardsInFoundationPile(0);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidIndexCascadePile() {
    // before game has started
    // for getNumCardsInCascadePile()
    this.game1.getNumCardsInCascadePile(0);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidIndexGetNumCardsOpenPile() {
    // before game has started
    // for getNumCardsInOpenPile()
    this.game1.getNumCardsInOpenPile(0);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidIndexGetFoundationCard() {
    // before game has started
    // for getFoundationCardAt()
    this.game1.getFoundationCardAt(0, 0);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidIndexGetCascadeCard() {
    // before game has started
    // getCascadeCardAt()
    this.game1.getCascadeCardAt(0, 0);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidIndexGetOpenCard() {
    // before game has started
    // for getOpenCardAt()
    this.game1.getOpenCardAt(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIndexAfterGame() {
    // after game has started
    // for getNumCardsInFoundationPile()
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(0, this.game1.getNumCardsInFoundationPile(-1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIndexAfterGamePT2() {
    // after game has started
    // for getNumCardsInFoundationPile()
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(0, this.game1.getNumCardsInFoundationPile(6));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIndexAfterGamePT3() {
    // after game has started
    // for getNumCardsInCascadePile()
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(0, this.game1.getNumCardsInFoundationPile(-1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIndexAfterGamePT4() {
    // after game has started
    // for getNumCardsInCascadePile()
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(0, this.game1.getNumCardsInFoundationPile(6));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIndexAfterGamePT5() {
    // after game has started
    // for getNumCardsInOpenPile()
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(0, this.game1.getNumCardsInOpenPile(-1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIndexAfterGamePT6() {
    // after game has started
    // for getNumCardsInOpenPile()
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(0, this.game1.getNumCardsInOpenPile(6));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIndexAfterGamePT7() {
    // after game has started
    // for getFoundationCardAt()
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(0, this.game1.getFoundationCardAt(-1, 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIndexAfterGamePT8() {
    // after game has started
    // for getFoundationCardAt()
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(0, this.game1.getFoundationCardAt(7, 7));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIndexAfterGamePT9() {
    // after game has started
    // for getFoundationCardAt()
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(0, this.game1.getFoundationCardAt(0, 7));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIndexAfterGamePT10() {
    // after game has started
    // for getFoundationCardAt()
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(0, this.game1.getFoundationCardAt(0, -1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIndexAfterGamePT11() {
    // after game has started
    // for getCascadeCardAt()
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(0, this.game1.getCascadeCardAt(-1, 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIndexAfterGamePT12() {
    // after game has started
    // for getCascadeCardAt()
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(0, this.game1.getCascadeCardAt(7, 7));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIndexAfterGamePT13() {
    // after game has started
    // for getCascadeCardAt()
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(0, this.game1.getCascadeCardAt(0, 16));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIndexAfterGamePT14() {
    // after game has started
    // for getCascadeCardAt()
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(0, this.game1.getCascadeCardAt(0, -1));
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIndexAfterGamePT15() {
    // after game has started
    // for getOpenCardAt()
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(0, this.game1.getOpenCardAt(2));
  }

  @Test
  public void testGetNumCascadePiles() {
    // before game
    assertEquals(-1, this.game1.getNumCascadePiles());

    // after game started
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(4, this.game1.getNumCascadePiles());
  }

  @Test
  public void testGetNumCardsInCascadePile() {
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(13, this.game1.getNumCardsInCascadePile(0));
    assertEquals(13, this.game1.getNumCardsInCascadePile(1));
    assertEquals(13, this.game1.getNumCardsInCascadePile(2));
    assertEquals(13, this.game1.getNumCardsInCascadePile(3));
  }

  @Test
  public void testGetNumCardsInOpenPile() {
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(0, this.game1.getNumCardsInOpenPile(0));
  }

  @Test
  public void testGetNumOpenPiles() {
    // before game
    assertEquals(-1, this.game1.getNumOpenPiles());
    // after game
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(1, this.game1.getNumOpenPiles());
  }

  @Test
  public void testGetFoundationCardAt() {
    this.game3.startGame(this.testDeck, 4, 1, false);
    FreecellView view3 = new FreecellTextView(game3);
    this.game3.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    // System.out.print(view3.toString());
    assertEquals("A♠", this.game3.getFoundationCardAt(0, 0).toString());
  }

  @Test
  public void testGetCascadeCardAt() {
    this.game1.startGame(this.regularDeck, 4, 1, false);
    assertEquals(1, this.game1.getCascadeCardAt(0, 0).returnStringValue());
    assertEquals("♦", this.game1.getCascadeCardAt(0, 0).returnSuit());
  }

  @Test
  public void testGetOpenCardAt() {
    this.game1.startGame(this.testDeck, 4, 1, false);
    this.game1.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    FreecellView view3 = new FreecellTextView(game1);
    // System.out.print(view3.toString());
    assertEquals("A♠", this.game1.getOpenCardAt(0).toString());
  }



}
