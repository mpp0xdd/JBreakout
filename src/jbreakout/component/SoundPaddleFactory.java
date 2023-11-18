package jbreakout.component;

import java.awt.Point;
import jbreakout.factory.AbstractPaddleFactory;

public class SoundPaddleFactory extends AbstractPaddleFactory<SoundPaddle> {

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
