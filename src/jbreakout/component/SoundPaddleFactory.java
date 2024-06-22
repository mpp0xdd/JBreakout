package jbreakout.component;

import java.awt.Point;
import jbreakout.common.factory.AbstractPaddleFactory;

public class SoundPaddleFactory implements AbstractPaddleFactory<SoundPaddle> {

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
