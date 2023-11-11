package jbreakout.component;

import java.awt.Color;
import jbreakout.common.Ball;

public class Paddle extends Brick {
  public Paddle(Color color, int width, int height, int x, int y) {
    super(color, width, height, x, y);
  }

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
