package jbreakout.component;

import static java.awt.Color.GREEN;
import static java.awt.Color.ORANGE;
import static java.awt.Color.RED;
import static java.awt.Color.YELLOW;
import java.awt.Color;
import java.awt.Point;
import jbreakout.factory.AbstractBrickFactory;

public class BrickFactory extends AbstractBrickFactory<SoundBrick> {

  private static final Color[] COLORS = {RED, RED, ORANGE, ORANGE, GREEN, GREEN, YELLOW, YELLOW};

  @Override
  public SoundBrick[] createBricks(Point point) {
    if (COLORS.length != rows()) {
      throw (new IllegalArgumentException("レンガの色情報が正しくありません"));
    }

    SoundBrick[] result = new SoundBrick[rows() * columns()];
    for (int i = 0; i < rows(); i++) {
      int brickY = i * height() + point.y;
      brickY += i * margin();
      for (int j = 0; j < columns(); j++) {
        int brickX = j * width() + point.x;
        brickX += j * margin();
        result[i * columns() + j] = new SoundBrick(COLORS[i], width(), height(), brickX, brickY);
      }
    }

    return result;
  }

  @Override
  public int rows() {
    return 8;
  }

  @Override
  public int columns() {
    return 14;
  }

  @Override
  public int width() {
    return 30;
  }

  @Override
  public int height() {
    return 10;
  }

  @Override
  public int margin() {
    return 4;
  }
}
