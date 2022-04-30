package animator.view;

import animator.model.Animator;

/**
 * Interface for an Animation view, more specific view seen in implmentations.
 */
public interface AnimationViewer {

  /**
   * Write the view as a String.
   * Example:
   * motion R 1  200 200 50 100 255 0  0    10  200 200 50 100 255 0  0
   * motion R 10 200 200 50 100 255 0  0    50  300 300 50 100 255 0  0
   * motion R 50 300 300 50 100 255 0  0    51  300 300 50 100 255 0  0
   * motion R 51 300 300 50 100 255 0  0    70  300 300 25 100 255 0  0
   * motion R 70 300 300 25 100 255 0  0    100 200 200 25 100 255 0  0
   *
   * @return String representing all moves in this animation.
   */
  public String toString();

  /**
   * Get the Animator for this view.
   *
   * @return the Animator model.
   */
  public Animator getAnimator();

  /**
   * Get the current tick for this view.
   *
   * @return the tick count.
   */
  public long getTickCount();

  /**
   * Set the current tick for this view.
   *
   * @param newCount count to set to.
   */
  public void setTickCount(long newCount);

  /**
   * Get the tick rate for this view.
   *
   * @return the tick rate
   */
  public long getTickRate();

  /**
   * Up the tick rate for this view by 1.
   */
  public void upTickRate();

  /**
   * Lower the tick rate for this view by 1.
   */
  public void lowerTickRate();

  /**
   * Tick method that increments the tick counter at the given tick rate.
   */
  public void tick() throws InterruptedException;

  /**
   * Animate the list of moves that have been given the go ahead.
   */
  public void renderView();

  /**
   * Send the animation to a file or print all done to System.out.
   */
  public void sendToFile();

  /**
   * Runs the animation. Add moves to the list toAnimate at the appropriate time.
   */
  public void run() throws InterruptedException;

  /**
   * Determines if this animation is completed.
   *
   * @return true if the tick count has passed all moves toTick, false otherwise.
   */
  public boolean checkAnimationCompleted();

  /**
   * Updates the fields of each shape based on the current tick and the move.
   */
  public void updateShapes();

  /**
   * Gets the canvas width.
   *
   * @return canvas width.
   */
  public int getCanvasWidth();

  /**
   * Set the canvas width.
   *
   * @param canvasWidth width to set to.
   */
  public void setCanvasWidth(int canvasWidth);

  /**
   * Gets the canvas Height.
   *
   * @return canvas height.
   */
  public int getCanvasHeight();

  /**
   * Set the canvas height.
   *
   * @param canvasHeight height to set to.
   */
  public void setCanvasHeight(int canvasHeight);
}
