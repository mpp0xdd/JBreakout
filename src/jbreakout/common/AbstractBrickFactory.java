package jbreakout.common;

import java.awt.Color;

public abstract class AbstractBrickFactory<T extends Brick> {

  public final T[] createBricks(int x, int y) {
    if (colors().length != rows()) {
      throw (new IllegalArgumentException("レンガの色情報が正しくありません"));
    }

    T[] result = newBricks();
    for (int i = 0; i < rows(); i++) {
      int brickY = i * height() + y;
      brickY += i * margin();
      for (int j = 0; j < columns(); j++) {
        int brickX = j * width() + x;
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
