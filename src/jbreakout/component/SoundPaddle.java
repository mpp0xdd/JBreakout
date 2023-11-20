package jbreakout.component;

import java.awt.Point;
import jbreakout.common.Ball;
import jbreakout.resource.SoundFactory;
import jglib.util.GameUtilities;

class SoundPaddle extends Paddle {

  public SoundPaddle(Point point, int width, int height) {
    super(point, width, height);
  }

  @Override
  public Ball rebound(Ball ball) {
    if (super.rebound(ball) == ball) {
      SoundFactory.paddleReboundClip().ifPresent(GameUtilities::playClip);
      return ball;
    }

    return null;
  }
}
