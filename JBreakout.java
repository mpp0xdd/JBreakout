public class JBreakout {
  public static void main(String[] args) {
    GameWindow gameWindow = new JBreakoutWindow();
    MainScreen mainScreen = new MainScreen();

    gameWindow.switchGameScreen(mainScreen);
    gameWindow.setVisible(true);
    mainScreen.startGameLoop();
    mainScreen.playBGM();
    mainScreen.activateBallRelocationTimer();
  }
}
