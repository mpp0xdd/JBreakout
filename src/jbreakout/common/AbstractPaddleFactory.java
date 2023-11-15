package jbreakout.common;

import java.awt.Point;

public abstract class AbstractPaddleFactory<T extends Paddle> {
  public abstract T createPaddle(Point point);

  public abstract int width();

  public abstract int height();
}
