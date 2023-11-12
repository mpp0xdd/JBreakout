package jbreakout.common;

import java.awt.Color;
import java.awt.Graphics;

public interface Ball {

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

  void draw(Graphics g);

  void bounceX();

  void bounceY();

  void bounce();

  void move();

  int acceleration();

  void accelerate();
}
