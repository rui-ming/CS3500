package cs3500.freecell.model;

import java.util.Objects;

/**
 * This is the class for a card constructed with a number from
 * A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K and a suit from ♦, ♣, ♥, ♠.
 */
public class Card {

  private final Values value;
  private final Suits suit;

  /**
   * Constructs a card with a number and the suit.
   *
   * @param value the number of the card
   * @param suit  the suit of the card
   * @throws IllegalArgumentException if value or suit is null
   */
  public Card(Values value, Suits suit) {
    if ((value == null) || (suit == null)) {
      throw new IllegalArgumentException("Value or Suit cannot be null");
    }
    this.value = value;
    this.suit = suit;
  }

  /**
   * Returns the color of the card.
   *
   * @return the enum color of the card
   */
  public Color returnColor() {
    if ((this.suit.equals(Suits.DIAMONDS)) || (this.suit.equals(Suits.HEARTS))) {
      return Color.RED;
    } else {
      return Color.BLACK;
    }
  }

  /**
   * Returns the number.
   *
   * @return the number of the card
   */
  public int returnStringValue() {
    return this.value.returnValue();
  }

  /**
   * Returns the suit of the card.
   *
   * @return the suit of the card
   */
  public String returnSuit() {
    return this.suit.toString();
  }

  @Override
  public String toString() {
    return this.value.toString() + this.suit.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof Card)) {
      return false;
    } else {
      Card that = (Card) (other);
      return (this.value == that.value) && (this.suit == that.suit);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, suit);
  }
}

