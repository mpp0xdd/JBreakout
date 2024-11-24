package jbreakout.common;

import jglib.util.spec.Drawable;
import jglib.util.spec.Rectangular;

public interface Paddle extends Bounceable<Ball>, Rectangular, Drawable {
  void setX(int x);
}
