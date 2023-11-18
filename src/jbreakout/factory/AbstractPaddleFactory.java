package jbreakout.factory;

import java.awt.Point;
import jbreakout.common.Paddle;

public abstract class AbstractPaddleFactory<T extends Paddle> {
  public abstract T createPaddle(Point point);

  public abstract int width();

  public abstract int height();
}
