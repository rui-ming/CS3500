import java.io.InputStreamReader;

import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.Card;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.FreecellModelCreator;

public class main {
  public static void main(String[] args) {

    FreecellModel<Card> model =  FreecellModelCreator.create(FreecellModelCreator.GameType.SINGLEMOVE);
    FreecellController controller =
            new SimpleFreecellController(model, new InputStreamReader(System.in), System.out);
    controller.playGame(model.getDeck(), 8, 4, false);

  }
}
