package jbreakout.component;

import static jbreakout.common.Constants.PADDLE_INIT_X;
import static jbreakout.common.Constants.PADDLE_INIT_Y;
import jbreakout.common.AbstractPaddleFactory;

public class PaddleFactory extends AbstractPaddleFactory<SoundPaddle> {

  @Override
  public SoundPaddle createPaddle() {
    return new SoundPaddle(PADDLE_INIT_X, PADDLE_INIT_Y);
  }
}
