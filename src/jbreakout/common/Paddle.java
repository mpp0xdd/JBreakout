package jbreakout.common;

public interface Paddle extends Bounceable<Ball>, Rectangular, Drawable {
  void setX(int x);
}
