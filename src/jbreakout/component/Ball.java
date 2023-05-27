package jbreakout.component;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
  private Color color;
  private int size;
  private int a;
  private int vx;
  private int vy;
  private int x;
  private int y;
  private boolean isAccelerating = false;
  private boolean visible = false;

  public Ball(Color color, int size, int a, int vx, int vy, int x, int y) {
    this.color = color;
    this.size = size;
    this.a = a;
    this.vx = vx;
    this.vy = vy;
    this.x = x;
    this.y = y;
  }

  public Ball(Color color, int size, int a) {
    this(color, size, a, 0, 0, 0, 0);
  }

  public int getSize() {
    return size;
  }

  public void accelerate() {
    isAccelerating = true;
  }

  public void setVX(int vx) {
    this.vx = vx;
  }

  public void setVY(int vy) {
    this.vy = vy;
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

  public void draw(Graphics g) {
    if (!visible) {
      return;
    }

    g.setColor(color);
    g.fillOval(x, y, size, size);

    if (isAccelerating) {
      for (int i = 0; i < a; i++) {
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
    vx = -vx;
    vy = -vy;
  }

  public void move() {
    x += vx;
    y += vy;
  }
}
