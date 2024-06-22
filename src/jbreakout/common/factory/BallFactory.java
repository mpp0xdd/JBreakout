package jbreakout.common.factory;

import java.awt.Point;
import jbreakout.common.Ball;
import jbreakout.common.Velocity;

public interface BallFactory<T extends Ball> {

  public abstract T createBall(Velocity velocity, Point point);

  public abstract int size();
}
