package jbreakout;

import jbreakout.common.SoundFactory;
import jbreakout.screen.MainScreen;
import jglib.component.GameWindow;

public class JBreakout {
  public static void main(String[] args) {
    GameWindow gameWindow = new GameWindow("JBreakout");
    MainScreen mainScreen = new MainScreen();

    int available = SoundFactory.availableSounds().size();
    int declared = SoundFactory.declaredSounds().size();
    System.err.printf("Available:%s, Declared:%s%n", available, declared);

    gameWindow.switchGameScreen(mainScreen);
    gameWindow.setVisible(true);
    mainScreen.startGameLoop();
    mainScreen.playBGM();
    mainScreen.activateBallRelocationTimer();
  }
}
