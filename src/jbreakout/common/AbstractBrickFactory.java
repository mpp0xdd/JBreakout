package jbreakout.common;

import java.awt.Point;

public abstract class AbstractBrickFactory<T extends Brick> {

  public abstract T[] createBricks(Point point);

  protected abstract int rows();

  protected abstract int columns();

  protected abstract int width();

  protected abstract int height();

  protected abstract int margin();
}
