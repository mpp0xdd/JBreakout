package jbreakout.common.factory;

import java.awt.Point;
import jbreakout.common.Brick;

public interface BrickFactory<T extends Brick> {

  T[] createBricks(Point point);

  int rows();

  int columns();

  int width();

  int height();

  int margin();
}
