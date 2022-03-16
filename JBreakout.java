public class JBreakout {
  public static void main(String[] args) {
    GameWindow gameWindow = new JBreakoutWindow();
    GameScreen mainScreen = new MainScreen();
    gameWindow.add(mainScreen);
    gameWindow.pack();
    gameWindow.setResizable(false);
    gameWindow.setVisible(true);
    GameUtilities.sleep(3000);
    mainScreen.startGameLoop();
  }
}
