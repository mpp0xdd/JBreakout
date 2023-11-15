package jbreakout.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import jbreakout.common.Constants;
import jbreakout.common.Velocity;

class Ball implements jbreakout.common.Ball {
  private Velocity velocity;
  private final Point location;
  private boolean isAccelerating = false;
  private boolean visible = false;

  public Ball(Velocity velocity, Point location) {
    this.velocity = velocity;
    this.location = location.getLocation();
  }

  public Ball() {
    this(Velocity.ZERO, new Point());
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
    return location.x;
  }

  @Override
  public void setX(int x) {
    location.x = x;
  }

  @Override
  public int getY() {
    return location.y;
  }

  @Override
  public void setY(int y) {
    location.y = y;
  }

  @Override
  public void setLocation(Point location) {
    this.location.setLocation(location);
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
    g.fillOval(location.x, location.y, size(), size());

    if (isAccelerating) {
      for (int i = 0; i < acceleration(); i++) {
        move();
        g.fillOval(location.x, location.y, size(), size());
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
    location.translate(velocity.vx(), velocity.vy());
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
