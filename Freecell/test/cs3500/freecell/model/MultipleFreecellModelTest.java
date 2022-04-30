package cs3500.freecell.model;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for game multiple freecell model.
 */
public class MultipleFreecellModelTest {

  private FreecellModel<Card> game1;
  private List<Card> testDeck;
  private java.util.List<Card> regularDeck;
  FreecellModel<Card> game3;


  @Before
  public void setUp() {
    game1 = new MultipleFreecellModel();
    regularDeck = game1.getDeck();

    game3 = new MultipleFreecellModel();

    testDeck = new ArrayList<Card>();
    testDeck = game1.getDeck();
    Collections.reverse(testDeck);
  }

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
  public void testInvalidBuild() {
    this.game1.startGame(this.regularDeck, 4, 20, false);
    // invalid because cards do not alternate or descend
    this.game1.move(PileType.CASCADE, 0, 9, PileType.CASCADE, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBuild2() {
    this.game1.startGame(this.testDeck, 4, 1, false);
    FreecellView view = new FreecellTextView(game1);
    this.game1.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    this.game1.move(PileType.CASCADE, 1, 12, PileType.FOUNDATION, 1);
    this.game1.move(PileType.CASCADE, 1, 11, PileType.FOUNDATION, 1);
    this.game1.move(PileType.CASCADE, 1, 10, PileType.FOUNDATION, 1);
    // System.out.print(view.toString());
    // invalid because 1 + 1 = 2 and there are no empty cascade piles so
    // not enough intermediate piles
    this.game1.move(PileType.CASCADE, 2, 10, PileType.CASCADE, 1);
  }

  @Test
  public void testCascadeEmptyMulti() {
    this.game1.startGame(this.testDeck, 52, 6, false);
    FreecellView view = new FreecellTextView(game1);
    this.game1.move(PileType.CASCADE, 51, 0, PileType.FOUNDATION, 0);
    this.game1.move(PileType.CASCADE, 44, 0, PileType.CASCADE, 43);
    this.game1.move(PileType.CASCADE, 49, 0, PileType.CASCADE, 43);
    // 3 empty cascade piles, moved 3 diamonds 2 spades ace hearts after 4 clubs
    this.game1.move(PileType.CASCADE, 43, 0, PileType.CASCADE, 38);
    //System.out.print(view.toString());

    assertEquals(view.toString(),
            "F1: A♦\n"
                    + "F2:\n" + "F3:\n" + "F4:\n"
                    + "O1:\n" + "O2:\n" + "O3:\n" + "O4:\n" + "O5:\n" + "O6:\n"
                    + "C1: K♠\n" + "C2: K♥\n" + "C3: K♣\n" + "C4: K♦\n"
                    + "C5: Q♠\n" + "C6: Q♥\n" + "C7: Q♣\n" + "C8: Q♦\n"
                    + "C9: J♠\n" + "C10: J♥\n" + "C11: J♣\n" + "C12: J♦\n"
                    + "C13: 10♠\n" + "C14: 10♥\n" + "C15: 10♣\n" + "C16: 10♦\n"
                    + "C17: 9♠\n" + "C18: 9♥\n" + "C19: 9♣\n" + "C20: 9♦\n"
                    + "C21: 8♠\n" + "C22: 8♥\n" + "C23: 8♣\n" + "C24: 8♦\n"
                    + "C25: 7♠\n" + "C26: 7♥\n" + "C27: 7♣\n" + "C28: 7♦\n"
                    + "C29: 6♠\n" + "C30: 6♥\n" + "C31: 6♣\n" + "C32: 6♦\n"
                    + "C33: 5♠\n" + "C34: 5♥\n" + "C35: 5♣\n" + "C36: 5♦\n"
                    + "C37: 4♠\n" + "C38: 4♥\n"
                    + "C39: 4♣, 3♦, 2♠, A♥\n"
                    + "C40: 4♦\n"
                    + "C41: 3♠\n" + "C42: 3♥\n" + "C43: 3♣\n"
                    + "C44:\n" + "C45:\n"
                    + "C46: 2♥\n" + "C47: 2♣\n" + "C48: 2♦\n"
                    + "C49: A♠\n"
                    + "C50:\n"
                    + "C51: A♣\n"
                    + "C52:");
  }

  @Test
  public void testMoveMulti() {

    // move from cascade to cascade
    game3.startGame(this.testDeck, 4, 10, false);
    FreecellView view2 = new FreecellTextView(game3);
    // moves ace of spades and 2 spades to foundation pile 1
    game3.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 0, 11, PileType.FOUNDATION, 0);
    // moves ace hearts to foundation pile 2
    game3.move(PileType.CASCADE, 1, 12, PileType.FOUNDATION, 1);
    // moves 2 hearts and ace clubs to cascade pile 1
    game3.move(PileType.CASCADE, 1, 11, PileType.CASCADE, 0);
    game3.move(PileType.CASCADE, 2, 12, PileType.CASCADE, 0);

    // get rid of the ace - 3 of diamonds to foundation pile 3
    game3.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 3, 11, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 3, 10, PileType.FOUNDATION, 2);

    // move three cards 3 clubs 2 hearts ace club cascade pile 4
    game3.move(PileType.CASCADE, 0, 10, PileType.CASCADE, 3);
    System.out.print(view2.toString());

    assertEquals(view2.toString(),
            "F1: A♠, 2♠\n"
                    + "F2: A♥\n"
                    + "F3: A♦, 2♦, 3♦\n"
                    + "F4:\n"
                    + "O1:\n" + "O2:\n" + "O3:\n" + "O4:\n" + "O5:\n" + "O6:\n"
                    + "O7:\n" + "O8:\n" + "O9:\n" + "O10:\n"
                    + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠\n"
                    + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥\n"
                    + "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
                    + "C4: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♠, 2♥, A♣");
  }

  @Test
  public void testMoveSingle() {
    this.game1.startGame(this.testDeck, 8, 4, false);
    FreecellView view1 = new FreecellTextView(game1);
    System.out.print(view1.toString());
    this.game1.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 5);

    assertEquals(view1.toString(),
            "F1:\n" + "F2:\n" + "F3:\n" + "F4:\n"
                    + "O1:\n" + "O2:\n" + "O3:\n" + "O4:\n"
                    + "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n"
                    + "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n"
                    + "C3: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n"
                    + "C4: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n"
                    + "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n"
                    + "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, A♠\n"
                    + "C7: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n"
                    + "C8: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦");

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

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCardIndex() {
    this.game1.startGame(this.testDeck, 52, 6, false);
    this.game1.move(PileType.CASCADE, 52, 0, PileType.FOUNDATION, 0);
  }


}