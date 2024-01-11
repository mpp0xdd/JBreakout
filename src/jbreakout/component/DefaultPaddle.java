package jbreakout.component;

import java.awt.Color;
import java.awt.Point;
import jbreakout.common.Ball;
import jbreakout.common.Paddle;

class DefaultPaddle extends DefaultBrick implements Paddle {

  public DefaultPaddle(Point point, int width, int height) {
    super(Color.BLUE, width, height, point.x, point.y);
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
