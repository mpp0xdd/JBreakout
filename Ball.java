import java.awt.Color;
import java.awt.Graphics;


public class Ball {
  public static final int SIZE = 10;

  private Color color;
  private int x;
  private int y;
  private int vx;
  private int vy;

  public Ball(Color color, int x, int y, int vx, int vy) {
    this.color = color;
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void draw(Graphics g) {
    g.setColor(color);
    g.fillOval(x, y, SIZE, SIZE);
  }

  public void bounceX() {
    vx = -vx;
  }

  public void bounceY() {
    vy = -vy;
  }

  public void bounce() {
    bounceX();
    bounceY();
  }

  public void move() {
    x += vx;
    y += vy;
  }
}
