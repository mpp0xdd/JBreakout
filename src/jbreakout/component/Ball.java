package jbreakout.component;

import java.awt.Color;
import java.awt.Graphics;
import jbreakout.common.Constants;

public class Ball implements jbreakout.common.Ball {
  private int a;
  private int vx;
  private int vy;
  private int x;
  private int y;
  private boolean isAccelerating = false;
  private boolean visible = false;

  public Ball(int a, int vx, int vy, int x, int y) {
    this.a = a;
    this.vx = vx;
    this.vy = vy;
    this.x = x;
    this.y = y;
  }

  public Ball(int a) {
    this(a, 0, 0, 0, 0);
  }

  @Override
  public int size() {
    return Constants.BALL_SIZE;
  }

  @Override
  public Color color() {
    return Color.WHITE;
  }

  @Override
  public void setVX(int vx) {
    this.vx = vx;
  }

  @Override
  public void setVY(int vy) {
    this.vy = vy;
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public void setX(int x) {
    this.x = x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public void setY(int y) {
    this.y = y;
  }

  @Override
  public boolean isVisible() {
    return visible;
  }

  @Override
  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  @Override
  public void draw(Graphics g) {
    if (!visible) {
      return;
    }

    g.setColor(color());
    g.fillOval(x, y, size(), size());

    if (isAccelerating) {
      for (int i = 0; i < a; i++) {
        move();
        g.fillOval(x, y, size(), size());
      }
      isAccelerating = false;
    }
  }

  @Override
  public void bounceX() {
    vx = -vx;
  }

  @Override
  public void bounceY() {
    vy = -vy;
  }

  @Override
  public void bounce() {
    vx = -vx;
    vy = -vy;
  }

  @Override
  public void move() {
    x += vx;
    y += vy;
  }

  @Override
  public void accelerate() {
    isAccelerating = true;
  }
}
