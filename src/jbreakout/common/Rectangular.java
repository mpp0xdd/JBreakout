package jbreakout.common;

import java.awt.Rectangle;

public interface Rectangular {

  default Rectangle asRectangle() {
    return new Rectangle(x(), y(), width(), height());
  }

  int x();

  int y();

  int width();

  int height();
}
