import java.awt.Graphics;
import java.awt.Color;


public class Brick {
  public static final int WIDTH  = 30;
  public static final int HEIGHT = 10;

  public static Brick[] lay(int rows, int columns, Color[] colors, int x, int y, int margin) {
    if(colors.length != rows) {
      throw (new IllegalArgumentException("レンガの色情報が正しくありません"));
    }

    Brick[] result = new Brick[rows * columns];
    for(int i = 0; i < rows; i++) {
      int brickY = i * HEIGHT + y;
      brickY += i * margin;
      for(int j = 0; j < columns; j++) {
        int brickX = j * WIDTH + x;
        brickX += j * margin;
        result[i * columns + j] = new Brick(colors[i], brickX, brickY);
      }
    }

    return result;
  }

  public final Color color;
  public final int x;
  public final int y;

  public Brick(Color color, int x, int y) {
    this.color = color;
    this.x = x;
    this.y = y;
  }

  public void draw(Graphics g) {
    g.setColor(color);
    g.fillRect(x, y, WIDTH, HEIGHT);
  }
}
