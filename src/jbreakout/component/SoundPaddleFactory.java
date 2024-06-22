package jbreakout.component;

import java.awt.Point;
import jbreakout.common.factory.PaddleFactory;

public class SoundPaddleFactory implements PaddleFactory<SoundPaddle> {

  @Override
  public SoundPaddle createPaddle(Point point) {
    return new SoundPaddle(point, width(), height());
  }

  @Override
  public int width() {
    return 60;
  }

  @Override
  public int height() {
    return 10;
  }
}
