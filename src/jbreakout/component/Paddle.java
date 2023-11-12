package jbreakout.component;

import java.awt.Color;
import jbreakout.common.Ball;

public class Paddle extends Brick implements jbreakout.common.Paddle {
  public Paddle(int width, int height, int x, int y) {
    super(Color.BLUE, width, height, x, y);
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
