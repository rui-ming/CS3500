package cs3500.freecell.model;

/**
 * This represents a Simple Freecell Model class which can only move one card at a time.
 */
public class SimpleFreecellModel extends AbstractFreecell {

  @Override
  public void move(PileType source, int pileNumber, int cardIndex,
                   PileType destination, int destPileNumber) {
    // checks whether the game has started
    if (this.cascade == null) {
      throw new IllegalArgumentException("Game has not started");
    }

    // checks that source destination is valid
    if (!(source.equals(PileType.OPEN) || source.equals(PileType.FOUNDATION)
            || source.equals(PileType.CASCADE))) {
      throw new IllegalArgumentException("Invalid Source type");
    }

    if (source.equals(PileType.FOUNDATION)) {
      throw new IllegalArgumentException("moving from foundation deck is invalid");
    }

    switch (source) {
      // moving from cascade
      case CASCADE:
        // check whether the pileNumber is valid, and cardIndex is at the end of the pile
        if ((pileNumber < 0) || (pileNumber >= cascade.size())
                || (cardIndex != this.cascade.get(pileNumber).size() - 1)) {
          throw new IllegalArgumentException(
                  "Invalid move, must only move card at the end of cascade pile");
        } else {
          moveFromCascade(pileNumber, cardIndex, destination, destPileNumber);
        }
        break;

      // moving from open
      case OPEN:
        moveFromOpen(pileNumber, cardIndex, destination, destPileNumber);
        break;

      default:
        // throw illegal move
        throw new IllegalArgumentException("Invalid move, must be moving from a piletype");

    }
  }

  /**
   * Moves a card from a cascade pile to destination PileType.
   *
   * @param pileNumber     pile number from which it is being moved
   * @param cardIndex      the index of where the card is located in pile
   * @param destination    the pile type it is being moved to
   * @param destPileNumber the destination number it is being moved to
   * @return a boolean whether it has the same suit
   */
  private void moveFromCascade(int pileNumber, int cardIndex,
                               PileType destination, int destPileNumber) {
    // cascade card I am moving
    Card cas = getCascadeCardAt(pileNumber, cardIndex);

    // check destination: can move to cascade, open, or foundation
    if (destination.equals(PileType.CASCADE)) {
      // cascade to cascade
      // must be alternating color, must be decreasing in value
      // extracts color of card being moved
      if (((cas.returnColor()).equals(
              // extracts color of last card in cascade destination pile
              getCascadeCardAt(destPileNumber, this.cascade.get(destPileNumber).size() - 1)
                      .returnColor()))
              || ((cas.returnStringValue())
              // extracts the value of the last card in cascade destination pile
              != (getCascadeCardAt(destPileNumber, this.cascade.get(destPileNumber).size() - 1)
              .returnStringValue() - 1))) {
        // means they are not alternating color or not decreasing in value
        throw new IllegalArgumentException("Invalid, must alternate color and decrease in value");
      } else {
        // adds card to destination cascade pile
        this.cascade.get(destPileNumber).add(cas);
        // removes card from source cascade pile
        this.cascade.get(pileNumber).remove(cardIndex);
      }
    } else if (destination.equals(PileType.FOUNDATION)) {

      // moving from cascade to foundation
      // must be increasing in order, same suit
      // first check if foundation pile is empty
      if (getNumCardsInFoundationPile(destPileNumber) == 0) {
        // check if the card moved is an ace
        if (cas.returnStringValue() == 1) {
          // if ace, then adds last cascade card in pile to foundation pile
          this.foundation.get(destPileNumber).add(cas);
          // after moving card, remove it from the cascade pile
          this.cascade.get(pileNumber).remove(cardIndex);
        } else {
          throw new IllegalArgumentException("First Card in Foundation Pile must be Ace");
        }
      } else {
        // if foundation pile is not empty - check for same suit + ascending value by 1
        // gets suit of source cascade pile
        if ((cas.returnSuit().equals(
                // gets suit of destination foundation pile (last card)
                getFoundationCardAt(destPileNumber,
                        getNumCardsInFoundationPile(destPileNumber) - 1).returnSuit()))
                // gets the value of the source cascade pile card
                && (cas.returnStringValue()
                // gets value of destination foundation card
                == getFoundationCardAt(destPileNumber,
                getNumCardsInFoundationPile(destPileNumber) - 1).returnStringValue() + 1)) {
          // if same suit + ascending value by 1 , then add card
          this.foundation.get(destPileNumber).add(cas);
          // after adding remove card from cascade pile
          this.cascade.get(pileNumber).remove(cardIndex);
        }
      }
    } else if (destination.equals(PileType.OPEN)) {

      // moving from cacade to open
      // must be open, or not card in the deck
      // check if open pile is empty
      if ((destPileNumber >= 0) && (destPileNumber < open.size())) {
        if (open.get(destPileNumber).size() != 0) {
          throw new IllegalArgumentException("Invalid move, open pile must be empty");
        } else {
          // add to last cascade card in pile to open pile
          this.open.get(destPileNumber).add(cas);
          // after moving card, remove it from the cascade pile
          this.cascade.get(pileNumber).remove(cardIndex);
        }
      }
    } else {
      // return illegal move
      throw new IllegalArgumentException("Invalid move");
    }
  }

  /**
   * Moves a card from a open pile to destination PileType.
   *
   * @param pileNumber     pile number from which it is being moved
   * @param cardIndex      the index of where the card is located in pile
   * @param destination    the pile type it is being moved to
   * @param destPileNumber the destination number it is being moved to
   * @return a boolean whether it has the same suit
   */
  private void moveFromOpen(int pileNumber, int cardIndex,
                            PileType destination, int destPileNumber) {
    // card I am moving
    Card op = getOpenCardAt(pileNumber);

    // can be open, cascade, foundation
    if (destination.equals(PileType.CASCADE)) {
      // moving from open to cascade
      // check alternating color, decrease in value
      // extracts color of open card being moved
      if (((op.returnColor()).equals(
              // extracts color of last card in cascade destination pile
              getCascadeCardAt(destPileNumber,
                      getNumCardsInCascadePile(destPileNumber) - 1).returnColor())) ||
              // extracts value of card being moved
              // open card has to be one value less than cascade card
              ((op.returnStringValue()) !=
                      // extracts the value of the last card in cascade destination pile
                      (getCascadeCardAt(destPileNumber,
                              getNumCardsInCascadePile(destPileNumber) - 1).
                              returnStringValue() - 1))) {
        // means they are not alternating color or not decreasing in value
        throw new IllegalArgumentException("Invalid, must alternate color and decrease in value");
      } else {
        // adds card to destination cascade pile
        this.cascade.get(destPileNumber).add(op);
        // removes card from source open pile
        this.open.get(pileNumber).remove(cardIndex);
      }
    } else if (destination.equals(PileType.FOUNDATION)) {

      // moving from open to foundation
      // check increasing in order, same suit
      // must be increasing in order, same suit
      // first check if foundation pile is empty
      if (getNumCardsInFoundationPile(destPileNumber) == 0) {
        // check if the open card moved is an ace
        if (op.returnStringValue() == 1) {
          // if ace, then adds open source card in pile to foundation pile
          this.foundation.get(destPileNumber).add(op);
          // after moving card, remove it from the open pile
          this.open.get(pileNumber).remove(cardIndex);
        } else {
          throw new IllegalArgumentException("First Card in Foundation Pile must be Ace");
        }
      } else {
        // if foundation pile is not empty - check for same suit + ascending value by 1
        // gets suit of source open pile
        if ((op.returnSuit().equals(
                // gets suit of destination foundation pile (last card)
                getFoundationCardAt(destPileNumber,
                        getNumCardsInFoundationPile(destPileNumber) - 1).returnSuit()))
                // gets the value of the source open pile card
                && ((op.returnStringValue())
                // gets value of destination foundation card
                == (getFoundationCardAt(destPileNumber,
                getNumCardsInFoundationPile(destPileNumber) - 1).returnStringValue() + 1))) {
          // if same suit + ascending value by 1 , then add card
          this.foundation.get(destPileNumber).add(op);
          // after adding remove card from open pile
          this.open.get(pileNumber).remove(cardIndex);
        }
      }
    } else if (destination.equals(PileType.OPEN)) {

      // moving from open to open
      // check that other open pile is empty
      if (getNumCardsInOpenPile(destPileNumber) != 0) {
        throw new IllegalArgumentException("Open Pile Must be empty to hold a card");
      } else {
        // if open pile is empty, then add card
        this.open.get(destPileNumber).add(op);
        // remove card from open source pile after adding
        this.open.get(pileNumber).remove(cardIndex);
      }
    } else {
      // return illegal move
      throw new IllegalArgumentException("must move from open to either cascade, " +
              "foundation or open");

    }

  }
}