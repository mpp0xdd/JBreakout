package jbreakout.screen;

import static jbreakout.common.Constants.GAME_LOOP_INTERVAL;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import jbreakout.common.AbstractBreakoutPane;
import jbreakout.component.BreakoutPane;
import jbreakout.resource.SoundFactory;
import jglib.component.GameScreen;
import jglib.util.GameUtilities;

public class MainScreen extends GameScreen {

  private final AbstractBreakoutPane breakoutPane = new BreakoutPane();

  public MainScreen() {
    breakoutPane.initializeComponent();
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
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    breakoutPane.draw(g);
  }

  @Override
  protected void runGameLoop() {
    breakoutPane.update();
    repaint();
  }
}
