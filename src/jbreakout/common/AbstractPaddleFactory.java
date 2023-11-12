package jbreakout.common;

public abstract class AbstractPaddleFactory<T extends Paddle> {
  public abstract T createPaddle();
}
