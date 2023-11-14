package jbreakout.common;

import java.awt.Color;

public interface Ball extends Drawable {

  int size();

  Color color();

  void setVX(int vx);

  void setVY(int vy);

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
