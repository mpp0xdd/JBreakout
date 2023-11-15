package jbreakout.component;

import java.awt.Color;
import java.awt.Graphics;
import jbreakout.common.Constants;
import jbreakout.common.Velocity;

class Ball implements jbreakout.common.Ball {
  private Velocity velocity;
  private int x;
  private int y;
  private boolean isAccelerating = false;
  private boolean visible = false;

  public Ball(Velocity velocity, int x, int y) {
    this.velocity = velocity;
    this.x = x;
    this.y = y;
  }

  public Ball() {
    this(Velocity.ZERO, 0, 0);
  }

  @Override
  public int size() {
    return Constants.BALL_SIZE;
  }

  @Override
  public void setVelocity(Velocity velocity) {
    this.velocity = velocity;
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
    this.velocity = velocity.negateVX();
  }

  @Override
  public void bounceY() {
    this.velocity = velocity.negateVY();
  }

  @Override
  public void bounce() {
    this.velocity = velocity.negate();
  }

  @Override
  public void move() {
    x += velocity.vx();
    y += velocity.vy();
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
