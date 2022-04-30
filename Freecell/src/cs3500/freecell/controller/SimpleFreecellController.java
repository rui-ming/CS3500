package cs3500.freecell.controller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import cs3500.freecell.model.Card;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;

/**
 * This is the class for the SimpleFreecellController which implements from the interface
 * FreecellController.
 */
public class SimpleFreecellController implements FreecellController<Card> {
  private final FreecellModel<Card> model;
  private final Readable rd;
  private final FreecellView view;
  private int currentInput;
  PileType userInputSource;
  int userInputSourceInt;
  int userInputCardIndexInt;
  PileType userInputDestination;
  int userInputDestinationInt;

  /**
   * Constructs a SimpleFreecellController with a FreecellModel, Readable and an Appendable.
   *
   * @param model upholds all the rules of the game
   * @param rd    takes in input
   * @param ap    displays the game with view
   * @throws IllegalArgumentException if model, rd, ap is null
   */
  public SimpleFreecellController(FreecellModel<Card> model, Readable rd, Appendable ap) {
    if ((model == null) || (ap == null) || (rd == null)) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.model = model;
    this.rd = rd;
    this.view = new FreecellTextView(model, ap);
    this.currentInput = 1;
    userInputSource = null;
    userInputDestination = null;
    userInputSourceInt = -1;
    userInputCardIndexInt = -1;
    userInputDestinationInt = -1;
  }

  @Override
  public void playGame(List<Card> deck, int numCascades, int numOpens, boolean shuffle)
          throws IllegalArgumentException {

    // check if deck is null or model is null
    if ((deck == null) || (model == null)) {
      throw new IllegalArgumentException("the deck is null or the model is null");
    }

    if ((numCascades < 0) || (numOpens < 0)) {
      sendMessage("Could not start game.");
      return;
    }

    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (IllegalArgumentException e) {
      sendMessage("Could not start game.");
      return;
    }

    Scanner scan = new Scanner(this.rd);

    if (!scan.hasNext()) {
      throw new IllegalStateException("No moves left in readable\n");
    }

    sendToBoard();
    while (scan.hasNext()) {

      String line = scan.next();

        if (line.equals("q") || line.equals("Q")) {
          // quit game
          sendMessage("Game quit prematurely.\n");
          return;
        }

        // check whether the currentInput is 1
        if (currentInput == 1) {
          convertUserSource(line);
        } else if (currentInput == 2) {
          convertUserCardIndex(line);
        } else if (currentInput == 3) {
          convertUserDestination(line);
          try {
            // try moving
            model.move(userInputSource, userInputSourceInt, userInputCardIndexInt,
                    userInputDestination, userInputDestinationInt);

            // catch when game ends
            if (model.isGameOver()) {
              sendMessage("Game over.\n");
            } else {
              this.currentInput = 1;
              userInputSource = null;
              userInputDestination = null;
              userInputSourceInt = -1;
              userInputCardIndexInt = -1;
              userInputDestinationInt = -1;
            }

          } catch (IllegalArgumentException ioe) {
            sendMessage("Invalid MOVE\n");
            this.currentInput = 1;
            userInputSource = null;
            userInputDestination = null;
            userInputSourceInt = -1;
            userInputCardIndexInt = -1;
            userInputDestinationInt = -1;
          }

        }

    }
  }

  /**
   * Updates the userInputDestination and userInputDestinationInt based on user input.
   *
   * @param str user input from scanner
   */
  private void convertUserDestination(String str) {
    // check that the userInputSource is null to make sure that there is no double input
    if ((userInputDestination == null) && (userInputDestinationInt == -1)) {
      // check that user input has length of 2
      if (str.length() >= 2) {
        // check that the first character is either F, C or O
        if (str.charAt(0) == 'F') {
          userInputDestination = PileType.FOUNDATION;
          userInputDestinationInt = Integer.parseInt(str.substring(1)) - 1;
        } else if (str.charAt(0) == 'C') {
          userInputDestination = PileType.CASCADE;
          userInputDestinationInt = Integer.parseInt(str.substring(1)) - 1;
        } else if (str.charAt(0) == 'O') {
          userInputDestination = PileType.OPEN;
          userInputDestinationInt = Integer.parseInt(str.substring(1)) - 1;
        } else {
          sendMessage("Invalid Input for Destination Pile\n");
        }
      } else {
        sendMessage("Invalid Input for Destination Pile\n");
      }
      currentInput++;
    }
  }

  /**
   * Updates userInputCardIndexInt based on user input.
   *
   * @param str user input from scanner
   */
  private void convertUserCardIndex(String str) {
    // check that the userInputSource is null to make sure that there is no double input
    if ((userInputCardIndexInt == -1) && (isNumeric(str))) {
      userInputCardIndexInt = Integer.parseInt(str) - 1;
    } else {
      sendMessage("Invalid Input for Card Index\n");
    }
    currentInput++;
  }

  /**
   * Checks if the given string contains a number.
   * HELP from: https://www.baeldung.com/java-check-string-number
   *
   * @param str user input from the scanner
   * @return a boolean that tells whether the string is a number.
   */
  private boolean isNumeric(String str) {
    if (str == null) {
      return false;
    }
    try {
      double d = Double.parseDouble(str);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }

  /**
   * Updates the userSource and userSourceInt based on user input.
   *
   * @param str user input from scanner
   */
  private void convertUserSource(String str) {
    // check that the userInputSource is null to make sure that there is no double input
    if ((userInputSource == null) && (userInputSourceInt == -1)) {
      // check that user input has length of 2
      if (str.length() >= 2) {
        // check that the first character is either F, C or O
        if (str.charAt(0) == 'F') {
          userInputSource = PileType.FOUNDATION;
          userInputSourceInt = Integer.parseInt(str.substring(1)) - 1;
        } else if (str.charAt(0) == 'C') {
          userInputSource = PileType.CASCADE;
          userInputSourceInt = Integer.parseInt(str.substring(1)) - 1;
        } else if (str.charAt(0) == 'O') {
          userInputSource = PileType.OPEN;
          userInputSourceInt = Integer.parseInt(str.substring(1)) - 1;
        } else {
          sendMessage("Invalid Input for Source Pile\n");
        }
      } else {
        sendMessage("Invalid Input for Source Pile\n");
      }
      currentInput++;
    }
  }

  /**
   * Send the message from the user to a game of freecell, throws an IllegalStateException if
   * transmission of the message failed.
   *
   * @param message string to be rendered
   * @throws IllegalStateException if IOException is caught
   */
  private void sendMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    } catch (IOException ioe) {
      throw new IllegalStateException("transmission failed");
    }
  }

  /**
   * Send the message from the user to a game of freecell, throws an IllegalStateException if
   * transmission of the message failed.
   *
   * @throws IllegalStateException if IOException is caught
   */
  private void sendToBoard() throws IllegalStateException {
    try {
      this.view.renderBoard();
    } catch (IOException ioe) {
      throw new IllegalStateException("transmission failed");
    }

  }

}
