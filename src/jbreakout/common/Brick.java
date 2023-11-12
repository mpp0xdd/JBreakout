package jbreakout.common;

import java.awt.Color;
import java.awt.Graphics;

public interface Brick {

  Color color();

  int width();

  void eliminate();

  void repair();

  void draw(Graphics g);

  Ball rebound(Ball ball);
}
