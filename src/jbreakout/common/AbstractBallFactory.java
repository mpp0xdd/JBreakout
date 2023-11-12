package jbreakout.common;

public abstract class AbstractBallFactory<T extends Ball> {
  public abstract T createBall();
}
