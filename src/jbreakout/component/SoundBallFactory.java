package jbreakout.component;

import java.awt.Point;
import jbreakout.common.Velocity;
import jbreakout.common.factory.BallFactory;

public class SoundBallFactory implements BallFactory<SoundBall> {

  @Override
  public SoundBall createBall(Velocity velocity, Point point) {
    return new SoundBall(velocity, point, size());
  }

  @Override
  public int size() {
    return 10;
  }
}
