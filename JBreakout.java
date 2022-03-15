public class JBreakout {
  public static void main(String[] args) {
    GameWindow gameWindow = new JBreakoutWindow();
    gameWindow.add(new MainScreen());
    gameWindow.pack();
    gameWindow.setResizable(false);
    gameWindow.setVisible(true);
  }
}
