package jbreakout.common;

public interface Brick extends Bounceable<Ball>, Rectangular, Drawable {

  int score();

  void eliminate();
}
