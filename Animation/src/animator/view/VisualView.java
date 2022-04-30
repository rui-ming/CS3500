package animator.view;

import javax.swing.JPanel;

import animator.model.Animator;

/**
 * Visual View, renders the animation using JPanel and JFrame.
 */
public class VisualView extends AbstractView {

  /**
   * Constructor for a visual view.
   *
   * @param a        animation model to view.
   * @param tickRate tick rate for this animation.
   */
  public VisualView(Animator a, long tickRate) {
    super(a, tickRate);
  }

  @Override
  public void renderView() {
    if (!this.isShowing()) {
      JPanel panel = new VisualPanel(getAnimator());
      this.add(panel);
      this.setVisible(true);
    } else {
      updateShapes();
      this.repaint();
    }
  }

  @Override
  public void sendToFile() {
    System.out.print("All Done");
  }
}