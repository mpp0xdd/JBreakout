package jbreakout.component;

import java.awt.Point;
import jbreakout.common.AbstractBallFactory;
import jbreakout.common.AbstractBreakoutPane;
import jbreakout.common.AbstractBrickFactory;
import jbreakout.common.AbstractPaddleFactory;
import jbreakout.common.Velocity;

public class BreakoutPane extends AbstractBreakoutPane {

  @Override
  public int width() {
    return 480;
  }

  @Override
  public int height() {
    return 640;
  }

  @Override
  protected Velocity ballVelocity() {
    final int vx = (5 + (int) ((5 + 1) * Math.random())) * (Math.random() >= 0.5 ? 1 : -1);
    final int vy = 5 + (int) ((5 + 1) * Math.random());
    return Velocity.of(vx, vy);
  }

  @Override
  protected Point bricksPoint() {
    return new Point(4, 100);
  }

  @Override
  protected Point paddlePoint() {
    return new Point(width() / 2 - paddleFactory().width() / 2, 580);
  }

  @Override
  protected AbstractBrickFactory<?> brickFactory() {
    return new BrickFactory();
  }

  @Override
  protected AbstractBallFactory<?> ballFactory() {
    return new BallFactory();
  }

  @Override
  protected AbstractPaddleFactory<?> paddleFactory() {
    return new PaddleFactory();
  }
}
