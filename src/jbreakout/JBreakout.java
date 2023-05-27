package jbreakout;

import jbreakout.screen.MainScreen;
import jglib.component.GameWindow;

public class JBreakout {
  public static void main(String[] args) {
    GameWindow gameWindow = new GameWindow("JBreakout");
    MainScreen mainScreen = new MainScreen();

    gameWindow.switchGameScreen(mainScreen);
    gameWindow.setVisible(true);
    mainScreen.startGameLoop();
    mainScreen.playBGM();
    mainScreen.activateBallRelocationTimer();
  }
}
