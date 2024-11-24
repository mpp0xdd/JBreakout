package jbreakout.common;

import jglib.util.spec.Drawable;
import jglib.util.spec.Rectangular;

public interface Brick extends Bounceable<Ball>, Rectangular, Drawable {

  int score();

  void eliminate();
}
