package jbreakout.screen;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import jbreakout.common.BreakoutPane;
import jbreakout.resource.SoundFactory;
import jglib.screen.GameScreenEx;
import jglib.util.GameUtilities;
import jglib.util.model.FrameRate;

public class MainScreen extends GameScreenEx {

  private final BreakoutPane breakoutPane;

  public MainScreen(BreakoutPane breakoutPane) {
    this.breakoutPane = breakoutPane;
    setScreenSize(breakoutPane.width(), breakoutPane.height());
    setFrameRate(FrameRate.FPS60);

    addMouseMotionListener(
        new MouseMotionAdapter() {
          @Override
          public void mouseMoved(MouseEvent e) {
            int x = e.getX();
            breakoutPane.movePaddle(x);
          }
        });
  }

  public void playBGM() {
    SoundFactory.mainScreenBgmClip().ifPresent(GameUtilities::repeatClip);
  }

  public void startRound() {
    breakoutPane.startRound();
  }

  @Override
  protected void paintGameComponent(Graphics g) {
    breakoutPane.draw(g);
  }

  @Override
  protected void runGameLoop() {
    breakoutPane.update();
  }
}
