package jbreakout.factory;

import java.awt.Point;
import jbreakout.common.Brick;

public abstract class AbstractBrickFactory<T extends Brick> {

  public abstract T[] createBricks(Point point);

  public abstract int rows();

  public abstract int columns();

  public abstract int width();

  public abstract int height();

  public abstract int margin();
}
