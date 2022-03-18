import java.awt.Color;
import java.awt.Graphics;


public class Ball {
  private Color color;
  private int size;
  private int x;
  private int y;
  private int vx;
  private int vy;
  private int a;
  private boolean isAccelerating = false;
  private boolean visible = false;

  public Ball(Color color, int size, int x, int y, int vx, int vy, int a) {
    this.color = color;
    this.size = size;
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    this.a = a;
  }

  public Ball(Color color, int size, int vx, int vy, int a) {
    this(color, size, 0, 0, vx, vy, a);
  }

  public int getSize() {
    return size;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public void accelerate() {
    isAccelerating = true;
  }

  public void draw(Graphics g) {
    if(!visible) {
      return;
    }

    g.setColor(color);
    g.fillOval(x, y, size, size);

    if(isAccelerating) {
      for(int i = 0; i < a; i++) {
        move();
        g.fillOval(x, y, size, size);
      }
      isAccelerating = false;
    }
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
