package jbreakout.common;

import jglib.util.spec.Drawable;

public interface Ball extends Drawable {

  int size();

  int getX();

  void setX(int x);

  int getY();

  void setY(int y);

  boolean isVisible();

  void setVisible(boolean visible);

  void bounceX();

  void bounceY();

  void bounce();

  void move();

  int acceleration();

  void accelerate();
}
