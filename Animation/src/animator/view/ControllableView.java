package animator.view;

import javax.swing.JPanel;

import animator.model.Animator;
import animator.model.Move;
import animator.model.AnimatorState;

/**
 * Controllable aka Interactive view class. Allows users to play or pause the animation,
 * restart it, loop after finishing, speed up or slow down. This is all done through the use of
 * buttons that were added to the JPanel.
 */
public class ControllableView extends VisualView {

  private boolean looping;

  /**
   * Constructor for a controllable view.
   *
   * @param a        animation model to view.
   * @param tickRate tick rate for this animation.
   * @param looping
   */
  public ControllableView(Animator a, long tickRate, boolean looping) {
    super(a, tickRate);
    this.looping = looping;
  }

  /**
   * Reset this animation, set the tickCount to 0 and clear the toAnimate list.
   */
  public void reset() {
    setTickCount(0);
    getAnimator().reset();
  }

  /**
   * Toggles looping on or off by flipping the boolean.
   */
  public void toggleLooping() {
    this.looping = !this.looping;
  }

  /**
   * Return the toggle looping.
   *
   * @return toggle looping.
   */
  public boolean getToggleLooping() {
    return this.looping;
  }

  /**
   * Set the state of this animation to Play.
   */
  public void play() {
    getAnimator().setState(AnimatorState.PLAY);
  }

  /**
   * Set the state of this animation to Pause.
   */
  public void pause() {
    getAnimator().setState(AnimatorState.PAUSE);
  }



  @Override
  public void run() throws InterruptedException {
    Animator a = getAnimator();
    while (true) {
      long tickCount = getTickCount();
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
      if (a.getState() == AnimatorState.PLAY) {
        tick();
      }
      if (checkAnimationCompleted() && looping) {
        reset();
      }
    }
  }

  @Override
  public void renderView() {
    if (!this.isShowing()) {
      JPanel panel = new ControllablePanel(getAnimator(), this);
      this.add(panel);
      this.setVisible(true);
    } else {
      updateShapes();
      this.repaint();
    }
  }
}
