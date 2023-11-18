package jbreakout.component;

import java.awt.Point;
import jbreakout.common.Velocity;
import jbreakout.factory.AbstractBallFactory;

public class SoundBallFactory extends AbstractBallFactory<SoundBall> {

  @Override
  public SoundBall createBall(Velocity velocity, Point location) {
    return new SoundBall(velocity, location, size());
  }

  @Override
  public int size() {
    return 10;
  }
}
