package jbreakout.common.factory;

import java.awt.Point;
import jbreakout.common.Paddle;

public interface AbstractPaddleFactory<T extends Paddle> {

  public abstract T createPaddle(Point point);

  public abstract int width();

  public abstract int height();
}
