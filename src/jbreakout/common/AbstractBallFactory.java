package jbreakout.common;

import java.awt.Point;

public abstract class AbstractBallFactory<T extends Ball> {
  public abstract T createBall(Velocity velocity, Point location);
}
