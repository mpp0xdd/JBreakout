package jbreakout.component;

import java.awt.Color;
import jbreakout.common.Ball;
import jbreakout.common.Constants;

public class Paddle extends Brick implements jbreakout.common.Paddle {
  public Paddle(int x, int y) {
    super(Color.BLUE, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, x, y);
  }

  @Override
  public void setX(int x) {
    this.x = x;
  }

  @Override
  public Ball rebound(Ball ball) {
    if (super.rebound(ball) == ball) {
      ball.accelerate();
      return ball;
    }

    return null;
  }
}
