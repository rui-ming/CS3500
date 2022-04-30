package animator.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

import javax.swing.JPanel;

import animator.model.Animator;
import animator.model.Move;
import animator.model.shapes.Shape;

/**
 * Panel used to represent a Text View.
 */
public class TextPanel extends JPanel {

  private final Animator a;

  /**
   * Constructor for a text panel.
   *
   * @param a animator to view.
   */
  public TextPanel(Animator a) {
    if (a == null) {
      throw new IllegalArgumentException("Animator cannot be null");
    }

    this.a = a;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    int x = 10;
    int y = 10;
    g2.setFont(new Font("TimesRoman", Font.PLAIN, 10));
    for (int i = 0; i < a.getShapes().size(); i++) {
      StringBuilder view = new StringBuilder();
      Shape s = a.getShapes().get(i);
      view.append(s.getType()).append(" ").append(s);
      g2.drawString(view.toString(), x, y);
      y += 10;
    }
    for (int i = 0; i < a.getToAnimate().size(); i++) {
      StringBuilder view = new StringBuilder();

      Move m = a.getToAnimate().get(i);
      Shape s = m.getShape();
      view.append("motion ");
      view.append(s.toString()).append(" ").append(m.getFromTick()).append(" ").append(s.getX())
              .append(" ").append(s.getY()).append(" ").append(s.getWidth()).append(" ")
              .append(s.getHeight()).append(" ").append(s.getColor().getRed())
              .append(" ").append(s.getColor().getGreen()).append(" ")
              .append(s.getColor().getBlue()).append("     ");
      view.append(m.getToTick()).append(" ").append(m.getNewX()).append(" ")
              .append(m.getNewY()).append(" ").append(m.getNewWidth()).append(" ")
              .append(m.getNewHeight()).append(" ").append(m.getNewColor().getRed())
              .append(" ").append(m.getNewColor().getGreen()).append(" ")
              .append(m.getNewColor().getBlue());
      g2.drawString(view.toString(), x, y);
      y += 10;
    }
  }
}
