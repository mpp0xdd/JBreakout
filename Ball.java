import java.awt.Color;
import java.awt.Graphics;


public class Ball {
  public static final int SIZE = 10;

  private Color color;
  private int x;
  private int y;

  public Ball(Color color, int x, int y) {
    this.color = color;
    this.x = x;
    this.y = y;
  }

  public void draw(Graphics g) {
    g.setColor(color);
    g.fillOval(x, y, SIZE, SIZE);
  }
}
