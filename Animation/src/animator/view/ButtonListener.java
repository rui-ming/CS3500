package animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Button Listener class, reacts when a button is pressed based on the title of this listener.
 * Used in the controllable view to allow user interactivity.
 */
public class ButtonListener implements ActionListener {

  private final String title;
  private final ControllableView view;

  /**
   * Constructor for a button listener.
   *
   * @param title name of or type of button being pressed.
   * @param view Controllable view that holds this button.
   */
  public ButtonListener(String title, ControllableView view) {
    if (title != null && view != null) {
      this.title = title;
      this.view = view;
    } else {
      throw new IllegalArgumentException("Null arguments");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (title) {
      case "play":
        view.play();
        break;
      case "pause":
        view.pause();
        break;
      case "restart":
        view.reset();
        break;
      case "looping":
        view.toggleLooping();
        break;
      case "increment":
        // lower tick rate means the animation speeds up
        view.lowerTickRate();
        break;
      case "decrement":
        // higher tick rate means the animation slows down
        view.upTickRate();
        break;
      default:
        throw new IllegalArgumentException("Invalid button");
    }
  }
}
