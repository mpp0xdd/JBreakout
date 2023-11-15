package jbreakout.common;

import java.awt.Point;

public interface Ball extends Drawable {

  int size();

  void setVelocity(Velocity velocity);

  int getX();

  void setX(int x);

  int getY();

  void setY(int y);

  void setLocation(Point location);

  boolean isVisible();

  void setVisible(boolean visible);

  void bounceX();

  void bounceY();

  void bounce();

  void move();

  int acceleration();

  void accelerate();
}
