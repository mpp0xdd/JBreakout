package jbreakout.component;

import java.awt.Point;
import jbreakout.common.AbstractBallFactory;
import jbreakout.common.Velocity;

public class BallFactory extends AbstractBallFactory<SoundBall> {

  @Override
  public SoundBall createBall(Velocity velocity, Point location) {
    return new SoundBall(velocity, location);
  }
}
