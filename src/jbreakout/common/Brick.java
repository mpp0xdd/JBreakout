package jbreakout.common;

import java.awt.Color;

public interface Brick extends Bounceable, Rectangular, Drawable {

  Color color();

  void eliminate();

  void repair();
}
