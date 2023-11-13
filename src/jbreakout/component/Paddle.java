package jbreakout.component;

import java.awt.Color;
import java.awt.Point;
import jbreakout.common.Ball;
import jbreakout.common.Constants;

class Paddle extends Brick implements jbreakout.common.Paddle {
  public Paddle(Point point) {
    super(Color.BLUE, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, point.x, point.y);
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
