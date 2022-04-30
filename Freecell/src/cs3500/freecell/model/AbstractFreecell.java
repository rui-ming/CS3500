package cs3500.freecell.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is an abstract class that represents the same methods that are shared between different
 * types of freecell games.
 */
public abstract class AbstractFreecell implements FreecellModel<Card> {

  protected List<List<Card>> cascade;
  protected List<List<Card>> open;
  protected List<List<Card>> foundation;

  @Override
  public abstract void move(PileType source, int pileNumber, int cardIndex,
                            PileType destination, int destPileNumber);

  @Override
  public List getDeck() {
    // holds 52 cards
    List<Card> deck1 = new ArrayList<Card>();
    // for loop that creates the cards
    for (Values v : Values.values()) {
      for (Suits s : Suits.values()) {
        deck1.add(new Card(v, s));
      }
    }
    return deck1;
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle) {

    if (!(checkValidDeck(deck))) {
      throw new IllegalArgumentException("Deck invalid");
    } else {
      // shuffle deck if boolean is true
      if (shuffle) {
        Collections.shuffle(deck);
      }

      // checks for at least 4 cascade piles and at least 1 open pile
      if ((numCascadePiles < 4) || (numOpenPiles < 1)) {
        throw new IllegalArgumentException(
                "Cascade Piles must be greater than 4 and Open Piles must be greater than 1");
      }

      resetGame();

      // creates an array for the cascade piles in the 2D array cascade
      createPiles(cascade, numCascadePiles);

      // filters round robin style
      int i = 0;
      while (i < deck.size()) {
        cascade.get(i % numCascadePiles).add(deck.get(i));
        i++;
      }

      // creates the foundation piles
      createPiles(foundation, 4);

      // create open piles
      createPiles(open, numOpenPiles);
    }
  }

  /**
   * Create new Arraylist for the piles so the game resets.
   */
  private void resetGame() {
    cascade = new ArrayList<List<Card>>();
    open = new ArrayList<List<Card>>();
    foundation = new ArrayList<List<Card>>();
  }

  private void createPiles(List<List<Card>> pilety, int num) {
    for (int x = 0; x < num; x++) {
      pilety.add(new ArrayList<Card>());
    }
  }

  /**
   * Checks if the deck is valid: no more than 52 cards, and no duplicates.
   *
   * @param deck the deck of cards
   * @return a boolean determining whether the deck is valid
   */
  private boolean checkValidDeck(List<Card> deck) {
    List<Card> checkedDeck = new ArrayList<Card>();

    // checks if size is = 52
    if (deck.size() != 52) {
      return false;
    }

    if (deck == null) {
      return false;
    }

    // checks for duplicate cards
    for (Card c : deck) {
      if ((checkedDeck.contains(c)) || (c == null)) {
        return false;
      } else {
        checkedDeck.add(c);
      }
    }
    return true;
  }


  @Override
  public boolean isGameOver() {
    for (List<Card> cardPile : this.foundation) {
      if ((cardPile == null) || (cardPile.size() == 0)) {
        return false;
      }

      for (int x = 0; x < cardPile.size() - 1; x++) {
        if (((cardPile.get(x).returnStringValue() + 1) != cardPile.get(x + 1).returnStringValue())
                || cardPile.get(x).returnSuit() != cardPile.get(x + 1).returnSuit()
                || (cardPile.size() != 13)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int getNumCardsInFoundationPile(int index) {
    // throw illegal if game has not started and index is invalid
    if (this.foundation == null) {
      throw new IllegalStateException("Game has not started");
    } else if ((index < 0) || (index >= this.foundation.size())) {
      throw new IllegalArgumentException("Index is invalid");
    } else {
      return this.foundation.get(index).size();
    }
  }

  @Override
  public int getNumCascadePiles() {
    // return -1 if game has not started
    if (this.cascade == null) {
      return -1;
    } else {
      return this.cascade.size();
    }
  }

  @Override
  public int getNumCardsInCascadePile(int index) {
    // throw illegal if game has not started and index is invalid
    if (this.cascade == null) {
      throw new IllegalStateException("Game has not started");
    } else if ((index < 0) || (index >= this.cascade.size())) {
      throw new IllegalArgumentException("Index is invalid");
    } else {
      return this.cascade.get(index).size();
    }
  }

  @Override
  public int getNumCardsInOpenPile(int index) {
    if (this.open == null) {
      throw new IllegalStateException("Game has not started");
    } else if ((index < 0) || (index >= this.open.size())) {
      throw new IllegalArgumentException("Index is invalid");
    } else {
      return this.open.get(index).size();
    }
  }

  @Override
  public int getNumOpenPiles() {
    // return -1 if game has not started
    if (this.open == null) {
      return -1;
    } else {
      return this.open.size();
    }
  }

  @Override
  public Card getFoundationCardAt(int pileIndex, int cardIndex) {
    // illegal if game has not started or pileindex or cardindex is invalid
    if (this.foundation == null) {
      throw new IllegalStateException("Game has not started");
    } else if ((pileIndex < 0) || (pileIndex >= this.foundation.size())
            || (cardIndex < 0) || (cardIndex >= this.foundation.get(pileIndex).size())) {
      throw new IllegalArgumentException("Invalid pileIndex and/or cardIndex");
    } else {
      return this.foundation.get(pileIndex).get(cardIndex);
    }
  }

  @Override
  public Card getCascadeCardAt(int pileIndex, int cardIndex) {
    // illegal if game has not started or pileindex or cardindex is invalid
    if (this.cascade == null) {
      throw new IllegalStateException("Game has not started");
    } else if ((pileIndex < 0) || (pileIndex >= this.cascade.size())
            || (cardIndex < 0) || (cardIndex >= this.cascade.get(pileIndex).size())) {
      throw new IllegalArgumentException("Invalid pileIndex and/or cardIndex");
    } else {
      return this.cascade.get(pileIndex).get(cardIndex);
    }
  }

  @Override
  public Card getOpenCardAt(int pileIndex) {
    // illegal if game has not started or pileIndex is invalid
    if (this.open == null) {
      throw new IllegalStateException("Game has not started");
    } else if ((pileIndex < 0) || (pileIndex >= this.open.size())) {
      throw new IllegalArgumentException("Invalid pileIndex");
    } else if ((this.open.get(pileIndex) == null) || (this.open.get(pileIndex).size() == 0)) {
      return null;
    } else {
      return this.open.get(pileIndex).get(0);
    }
  }

}
