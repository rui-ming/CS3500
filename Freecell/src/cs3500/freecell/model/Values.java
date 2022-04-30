package cs3500.freecell.model;

/**
 * This represents a value for a card.
 */
public enum Values {
  ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
  NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

  private final int v;

  /**
   * Constructs a value for a card.
   *
   * @param v integer represents the value of a card
   * @throws IllegalArgumentException if the value is >13 or <0
   */
  Values(int v) {
    if ((v <= 0) || (v > 13)) {
      throw new IllegalArgumentException("Value cannot be greater than 13 or less than 0");
    }
    this.v = v;
  }

  /**
   * Returns an integer value of the value.
   *
   * @return returns an integer value of the value of the enum.
   */
  public int returnValue() {
    return this.v;
  }

  @Override
  public String toString() {
    switch (this.v) {
      case 1:
        return "A";
      case 11:
        return "J";
      case 12:
        return "Q";
      case 13:
        return "K";
      default:
        return Integer.toString(this.v);
    }
  }
}
