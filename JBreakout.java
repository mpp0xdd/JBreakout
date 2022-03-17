public class JBreakout {
  public static void main(String[] args) {
    GameWindow gameWindow = new JBreakoutWindow();
    MainScreen mainScreen = new MainScreen();
    gameWindow.add(mainScreen);
    gameWindow.pack();
    gameWindow.setResizable(false);
    gameWindow.setVisible(true);
    mainScreen.startGameLoop();
    mainScreen.activateGameStartTimer();

    mainScreen.joinGameLoop();
    System.err.println("ゲームセット！");
  }
}
