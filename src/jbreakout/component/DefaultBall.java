package jbreakout.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import jbreakout.common.Ball;
import jbreakout.common.Velocity;

class DefaultBall implements Ball {

  private Velocity velocity;
  private final Point location;
  private final int size;
  private boolean isAccelerating = false;
  private boolean visible = false;

  public DefaultBall(Velocity velocity, Point location, int size) {
    this.velocity = velocity;
    this.location = location.getLocation();
    this.size = size;
  }

  @Override
  public int size() {
    return size;
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
    this.velocity = velocity.negateX();
  }

  @Override
  public void bounceY() {
    this.velocity = velocity.negateY();
  }

  @Override
  public void bounce() {
    this.velocity = velocity.negate();
  }

  @Override
  public void move() {
    location.translate(velocity.x(), velocity.y());
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
