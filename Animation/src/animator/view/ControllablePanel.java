package animator.view;

import javax.swing.JButton;

import animator.model.Animator;

/**
 * Controllable panel, has buttons that can play, pause, restart, loop, speed up, or slow down
 * the animation.
 */
public class ControllablePanel extends VisualPanel {

  /**
   * Controllable Panel Constructor.
   *
   * @param a animation model to view.
   */
  public ControllablePanel(Animator a, ControllableView view) {
    super(a);

    JButton play = new JButton("play");
    JButton pause = new JButton("pause");
    JButton restart = new JButton("restart");
    JButton looping = new JButton("toggle looping");
    JButton increment = new JButton("speed up");
    JButton decrement = new JButton("slow down");

    ButtonListener playListener = new ButtonListener("play", view);
    ButtonListener pauseListener = new ButtonListener("pause", view);
    ButtonListener restartListener = new ButtonListener("restart", view);
    ButtonListener loopingListener = new ButtonListener("looping", view);
    ButtonListener incrementListener = new ButtonListener("increment", view);
    ButtonListener decrementListener = new ButtonListener("decrement", view);

    play.addActionListener(playListener);
    pause.addActionListener(pauseListener);
    restart.addActionListener(restartListener);
    looping.addActionListener(loopingListener);
    increment.addActionListener(incrementListener);
    decrement.addActionListener(decrementListener);

    add(play);
    add(pause);
    add(restart);
    add(looping);
    add(increment);
    add(decrement);
  }
}
