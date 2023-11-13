package jbreakout.common;

import java.awt.Color;
import java.awt.Point;

public abstract class AbstractBrickFactory<T extends Brick> {

  public final T[] createBricks(Point point) {
    if (colors().length != rows()) {
      throw (new IllegalArgumentException("レンガの色情報が正しくありません"));
    }

    T[] result = newBricks();
    for (int i = 0; i < rows(); i++) {
      int brickY = i * height() + point.y;
      brickY += i * margin();
      for (int j = 0; j < columns(); j++) {
        int brickX = j * width() + point.x;
        brickX += j * margin();
        result[i * columns() + j] = newBrick(colors()[i], brickX, brickY);
      }
    }

    return result;
  }

  protected abstract T newBrick(Color color, int x, int y);

  protected abstract T[] newBricks();

  protected abstract int rows();

  protected abstract int columns();

  protected abstract Color[] colors();

  protected abstract int width();

  protected abstract int height();

  protected abstract int margin();
}
