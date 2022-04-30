package cs3500.freecell.model;

/**
 * Type for the suits in a game of Freecell. 
 */
public enum Suits {
  DIAMONDS("♦"), CLUBS("♣"), HEARTS("♥"), SPADES("♠");

  private final String s;

  /**
   * Constructs a suit for a card.
   * @param s String represents the suit of a card
   * @throws IllegalArgumentException if the suit is null
   */
  Suits(String s) {
    if (s == null) {
      throw new IllegalArgumentException("Suit cannot be null");
    }
    this.s = s;
  }

  @Override
  public String toString() {
    return this.s;
  }
}
