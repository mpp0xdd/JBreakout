package jbreakout.common.factory;

import java.awt.Point;
import jbreakout.common.Ball;
import jbreakout.common.Velocity;

public interface BallFactory<T extends Ball> {

  T createBall(Velocity velocity, Point point);

  int size();
}
