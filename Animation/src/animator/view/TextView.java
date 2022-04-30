package animator.view;

import javax.swing.JPanel;

import animator.model.Animator;

/**
 * Simple Text View of the Animation. As moves are added to the toAnimate list they will
 * appear on the panel with a description of the shape before and after the move.
 */
public class TextView extends AbstractView {

  /**
   * Constructor for a text view.
   *
   * @param a        Animator model we are viewing.
   * @param tickRate tick rate for this animation
   */
  public TextView(Animator a, long tickRate) {
    super(a, tickRate);
  }

  @Override
  public void renderView() {
    if (!this.isShowing()) {
      JPanel panel = new TextPanel(getAnimator());
      this.add(panel);
      this.setVisible(true);
    } else {
      this.repaint();
    }
  }

  @Override
  public void sendToFile() {
    System.out.print("All Done");
  }
}
