public class JBreakout {
  public static void main(String[] args) {
    GameWindow gameWindow = new JBreakoutWindow();
    MainScreen mainScreen = new MainScreen();

    gameWindow.switchGameScreen(mainScreen);
    gameWindow.pack();

    gameWindow.setVisible(true);
    mainScreen.startGameLoop();
    mainScreen.activateBallRelocationTimer();
  }
}
