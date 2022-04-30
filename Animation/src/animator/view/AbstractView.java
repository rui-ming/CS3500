package animator.view;

import javax.swing.JFrame;

import animator.model.Animator;
import animator.model.Move;
import animator.model.shapes.Shape;

/**
 * Abstract view class. Implements many of the methods of the Animation viewer that are used in
 * the extensions of this class and would be duplicated otherwise. Can get/set different fields
 * as well as run the animation and update the shapes based on where in the animation we are.
 */
abstract class AbstractView extends JFrame implements AnimationViewer {

  private final Animator a;
  private long tickRate;
  private long tickCount;
  private int canvasWidth;
  private int canvasHeight;

  /**
   * Abstract view constructor, used in extensions of abstract view.
   *
   * @param a        animator to view.
   * @param tickRate tickrate of animation.
   */
  protected AbstractView(Animator a, long tickRate) {
    super("Animation");
    if (a == null || tickRate < 0) {
      throw new IllegalArgumentException("animator cannot be null and tickRate must be positive");
    } else {
      setCanvasWidth(a.getWidth());
      setCanvasHeight(a.getHeight());
      setLocation(200, 200);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.a = a;
      this.tickRate = tickRate;
      this.tickCount = 0;
    }
  }

  @Override
  public String toString() {
    StringBuilder view = new StringBuilder();

    for (Move m : getAnimator().getMoves()) {
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
      view.append("\n");
    }

    return view.toString();
  }

  @Override
  public int getCanvasWidth() {
    return canvasWidth;
  }

  @Override
  public void setCanvasWidth(int canvasWidth) {
    this.canvasWidth = canvasWidth;
    setSize(canvasWidth, canvasHeight);
  }

  @Override
  public int getCanvasHeight() {
    return canvasHeight;
  }

  @Override
  public void setCanvasHeight(int canvasHeight) {
    this.canvasHeight = canvasHeight;
    setSize(canvasWidth, canvasHeight);
  }

  @Override
  public Animator getAnimator() {
    return a;
  }

  @Override
  public long getTickCount() {
    return tickCount;
  }

  @Override
  public void setTickCount(long newCount) {
    tickCount = newCount;
  }

  @Override
  public long getTickRate() {
    return tickRate;
  }

  @Override
  public void upTickRate() {
    tickRate += 10;
  }

  @Override
  public void lowerTickRate() {
    if (tickRate - 10 < 0) {
      tickRate = 0;
    } else {
      tickRate -= 10;
    }
  }

  @Override
  public void run() throws InterruptedException {
    while (!checkAnimationCompleted()) {
      for (int i = 0; i < a.getMoves().size(); i++) {
        Move curr = a.getMoves().get(i);
        if (curr.getFromTick() <= tickCount && curr.getToTick() >= tickCount && !curr.getActive()) {
          curr.start();
          a.addToAnimate(curr);
        }
        if (curr.getToTick() < tickCount) {
          curr.end();
        }
      }
      renderView();
      tick();
    }
    sendToFile();
  }

  @Override
  public void tick() throws InterruptedException {
    Thread.sleep(tickRate);
    tickCount++;
  }

  @Override
  public abstract void renderView();

  @Override
  public abstract void sendToFile();

  @Override
  public boolean checkAnimationCompleted() {
    boolean allDone = true;
    for (int i = 0; i < a.getMoves().size(); i++) {
      allDone = allDone && (a.getMoves().get(i).getToTick() < tickCount);
    }
    return allDone;
  }

  @Override
  public void updateShapes() {
    for (int i = 0; i < a.getMoves().size(); i++) {
      Move curr = a.getMoves().get(i);
      if (curr.getFromTick() <= tickCount && curr.getToTick() >= tickCount) {
        double totalTime = curr.getToTick() - curr.getFromTick();
        double currX = (curr.getShape().getX()
                * (curr.getToTick() - tickCount) / totalTime)
                + (curr.getNewX() * (tickCount - curr.getFromTick()) / totalTime);
        double currY = (curr.getShape().getY()
                * (curr.getToTick() - tickCount) / totalTime)
                + (curr.getNewY() * (tickCount - curr.getFromTick()) / totalTime);
        double currWidth = (curr.getShape().getWidth()
                * (curr.getToTick() - tickCount) / totalTime)
                + (curr.getNewWidth() * (tickCount - curr.getFromTick()) / totalTime);
        double currHeight = (curr.getShape().getHeight()
                * (curr.getToTick() - tickCount) / totalTime)
                + (curr.getNewHeight() * (tickCount - curr.getFromTick()) / totalTime);

        curr.getShape().setX(currX);
        curr.getShape().setY(currY);
        curr.getShape().setWidth(currWidth);
        curr.getShape().setHeight(currHeight);
        curr.getShape().setColor(curr.getNewColor());
      }
    }
  }
}
