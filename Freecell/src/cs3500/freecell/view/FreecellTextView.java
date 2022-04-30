package cs3500.freecell.view;

import java.io.IOException;

import cs3500.freecell.model.FreecellModelState;

/**
 * This represents a Freecell textview class.
 */
public class FreecellTextView implements FreecellView {
  private final FreecellModelState<?> freecellmodel;
  private final Appendable output;

  /**
   * Constructs a freecell text view class with freecellmodelstate<?> as an argument type.
   *
   * @param freecellmodel argument that represents type of FreecellModelState<?>
   * @param output the appendable string object that displays the game status
   * @throws IllegalArgumentException when no valid Appendable object was provided
   */
  public FreecellTextView(FreecellModelState<?> freecellmodel, Appendable output) {
    if (output == null) {
      throw new IllegalArgumentException("Appendable output cannot be null");
    }
    this.freecellmodel = freecellmodel;
    this.output = output;
  }

  /**
   * Constructs a freecell text view class with freecellmodelstate<?> as an argument type.
   *
   * @param freecellmodel argument that represents type of FreecellModelState<?>
   */
  public FreecellTextView(FreecellModelState<?> freecellmodel) {
    this.freecellmodel = freecellmodel;
    this.output = new StringBuilder();
  }

  @Override
  public String toString() {
    if (this.freecellmodel.getNumCascadePiles() == -1) {
      return "";
    } else {
      StringBuilder out = new StringBuilder();
      // print for foundation piles
      for (int f = 0; f < 4; f++) {
        out.append("F" + (f + 1) + ":");
        if (freecellmodel.getNumCardsInFoundationPile(f) != 0) {
          out.append(" ");
        }
        for (int g = 0; g <= freecellmodel.getNumCardsInFoundationPile(f) - 1; g++) {
          out.append(freecellmodel.getFoundationCardAt(f, g).toString());
          if (g != freecellmodel.getNumCardsInFoundationPile(f) - 1) {
            out.append(", ");
          }
        }
        out.append("\n");
      }
      // print for open piles
      for (int o = 0; o < freecellmodel.getNumOpenPiles(); o++) {
        out.append("O" + (o + 1) + ":");
        if (freecellmodel.getNumCardsInOpenPile(o) != 0) {
          out.append(" ");
        }
        if (freecellmodel.getNumCardsInOpenPile(o) == 1) {
          out.append(freecellmodel.getOpenCardAt(o).toString());
        }
        out.append("\n");
      }
      //print for cascade piles

      for (int i = 0; i <= freecellmodel.getNumCascadePiles() - 1; i++) {
        out.append("C" + (i + 1) + ":");
        if (freecellmodel.getNumCardsInCascadePile(i) != 0) {
          out.append(" ");
        }
        for (int j = 0; j <= freecellmodel.getNumCardsInCascadePile(i) - 1; j++) {
          out.append(freecellmodel.getCascadeCardAt(i, j).toString());
          if (j != freecellmodel.getNumCardsInCascadePile(i) - 1) {
            out.append(", ");
          }
        }
        if (i != freecellmodel.getNumCascadePiles() - 1) {
          out.append("\n");
        }
      }

      return out.toString();
    }
  }

  @Override
  public void renderBoard() throws IOException {
    try {
      this.output.append(this.toString());
    } catch (IOException ioe) {
      throw new IOException("renderBoard exception found");
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.output.append(message);
    } catch (IOException ioe) {
      throw new IOException("renderMessage exception found");
    }
  }

}
