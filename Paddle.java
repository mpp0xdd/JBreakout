import java.awt.Color;
import java.awt.Graphics;


public class Paddle {
  public static final int WIDTH  = 60;
  public static final int HEIGHT = 10;

  private final Color color;
  private int x;
  private final int y;

  public Paddle(Color color, int x, int y) {
    this.color = color;
    this.x = x;
    this.y = y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void draw(Graphics g) {
    g.setColor(color);
    g.fillRect(x, y, WIDTH, HEIGHT);
  }
}
