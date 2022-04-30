package animator.view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import animator.model.shapes.Shape;
import animator.model.Animator;
import animator.model.Move;

/**
 * Visual Panel class used to create a JPanel for a visual view.
 */
public class VisualPanel extends JPanel {

  private final Animator a;

  /**
   * Visual Panel Constructor.
   *
   * @param a animation model to view.
   */
  public VisualPanel(Animator a) {
    if (a == null) {
      throw new IllegalArgumentException("Animator cannot be null");
    }

    this.a = a;
  }

  /**
   * Get Animator for this panel.
   *
   * @return animator for this panel.
   */
  public Animator getAnimator() {
    return a;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    for (int i = 0; i < a.getShapes().size(); i++) {
      Shape s = a.getShapes().get(i);
      switch (s.getType()) {
        case "Rectangle":
          g2.setPaint(s.getColor());
          g2.fillRect((int) s.getX(), (int) s.getY(), (int) s.getWidth(), (int) s.getHeight());
          break;
        case "Oval":
          g2.setPaint(s.getColor());
          g2.fillOval((int) s.getX(), (int) s.getY(), (int) s.getWidth(), (int) s.getHeight());
          break;
        default:
          throw new IllegalArgumentException("Invalid shape type");
      }
    }
  }
}
