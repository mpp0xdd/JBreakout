package jbreakout.common;

public interface Brick extends Bounceable, Rectangular, Drawable {

  int score();

  void eliminate();

  void repair();
}
