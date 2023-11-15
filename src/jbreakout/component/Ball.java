package jbreakout.component;

import java.awt.Color;
import java.awt.Graphics;
import jbreakout.common.Constants;

class Ball implements jbreakout.common.Ball {
  private int vx;
  private int vy;
  private int x;
  private int y;
  private boolean isAccelerating = false;
  private boolean visible = false;

  public Ball(int vx, int vy, int x, int y) {
    this.vx = vx;
    this.vy = vy;
    this.x = x;
    this.y = y;
  }

  public Ball() {
    this(0, 0, 0, 0);
  }

  @Override
  public int size() {
    return Constants.BALL_SIZE;
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

    g.setColor(Color.WHITE);
    g.fillOval(x, y, size(), size());

    if (isAccelerating) {
      for (int i = 0; i < acceleration(); i++) {
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
  public int acceleration() {
    return 8;
  }

  @Override
  public void accelerate() {
    isAccelerating = true;
  }
}
