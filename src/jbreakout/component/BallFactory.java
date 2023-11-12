package jbreakout.component;

import jbreakout.common.AbstractBallFactory;

public class BallFactory extends AbstractBallFactory<SoundBall> {

  @Override
  public SoundBall createBall() {
    return new SoundBall();
  }
}
