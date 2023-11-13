package jbreakout.component;

import java.awt.Point;
import jbreakout.common.AbstractPaddleFactory;

public class PaddleFactory extends AbstractPaddleFactory<SoundPaddle> {

  @Override
  public SoundPaddle createPaddle(Point point) {
    return new SoundPaddle(point);
  }
}
