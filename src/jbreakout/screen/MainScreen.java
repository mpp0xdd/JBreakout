package jbreakout.screen;

import static jbreakout.common.Constants.GAME_LOOP_INTERVAL;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import jbreakout.common.BreakoutPane;
import jbreakout.resource.SoundFactory;
import jglib.component.GameScreen;
import jglib.util.GameUtilities;

public class MainScreen extends GameScreen {

  private final BreakoutPane breakoutPane;

  public MainScreen(BreakoutPane breakoutPane) {
    this.breakoutPane = breakoutPane;
    setScreenSize(breakoutPane.width(), breakoutPane.height());
    setGameLoopInterval(GAME_LOOP_INTERVAL);

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
