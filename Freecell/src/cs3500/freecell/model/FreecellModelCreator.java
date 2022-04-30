package cs3500.freecell.model;

/**
 * This class represents a freecell model creator which can either return either a simplefreecell
 * model or a multi card move model depending on enum parameter type.
 */
public class FreecellModelCreator {
  /**
   * A enum gametype can either be singlemove or multimove.
   * This represents the two versions of freecell that can be played.
   */
  public enum GameType {
    SINGLEMOVE, MULTIMOVE;
  }

  /**
   * Offer a static factory method create(GameType type) that returns either a SimpleFreecellModel
   * or an object of your multi-card-move model, depending on the value of the parameter.
   * @param type an enum data type
   * @return either a simplefreecell model or a multi card move model
   */
  public static FreecellModel<Card> create(GameType type) {
    if (type == null) {
      throw new IllegalArgumentException("Game Type cannot be null");
    } else if (type.equals(GameType.SINGLEMOVE)) {
      return new SimpleFreecellModel();
    } else if (type.equals(GameType.MULTIMOVE)) {
      return new MultipleFreecellModel();
    } else {
      throw new IllegalArgumentException("Game Type is Invalid");
    }

  }

}
