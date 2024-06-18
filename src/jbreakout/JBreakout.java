package jbreakout;

import jbreakout.component.DefaultBreakoutPane;
import jbreakout.screen.MainScreen;
import jglib.base.Game;
import jglib.component.GameWindow;

public class JBreakout extends Game {

  public static void main(String[] args) {
    launch(JBreakout.class);
  }

  @Override
  protected void start() {
    GameWindow gameWindow = new GameWindow("JBreakout");
    MainScreen mainScreen = new MainScreen(new DefaultBreakoutPane());

    gameWindow.switchGameScreen(mainScreen);
    gameWindow.setVisible(true);
    mainScreen.startGameLoop();
    mainScreen.playBGM();
    mainScreen.startRound();
  }
}
