import java.awt.Color;
import java.awt.Graphics;


public class Ball {
  public static final int SIZE = 10;

  private Color color;
  private int x;
  private int y;
  private int vx;
  private int vy;
  private boolean visible = false;

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

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public void draw(Graphics g) {
    if(!visible) {
      return;
    }
    g.setColor(color);
    g.fillOval(x, y, SIZE, SIZE);
  }

  public void bounceX() {
    if(!visible) {
      return;
    }
    vx = -vx;
  }

  public void bounceY() {
    if(!visible) {
      return;
    }
    vy = -vy;
  }

  public void bounce() {
    if(!visible) {
      return;
    }
    bounceX();
    bounceY();
  }

  public void move() {
    if(!visible) {
      return;
    }
    x += vx;
    y += vy;
  }
}
