package jbreakout.component;

import java.awt.Point;
import java.util.Optional;
import javax.sound.sampled.Clip;
import jbreakout.common.Ball;
import jbreakout.resource.SoundFactory;
import jglib.util.GameUtilities;

class SoundPaddle extends Paddle {

  private final Optional<Clip> reboundClip = SoundFactory.paddleReboundClip();

  public SoundPaddle(Point point, int width, int height) {
    super(point, width, height);
  }

  @Override
  public Ball rebound(Ball ball) {
    if (super.rebound(ball) == ball) {
      reboundClip.ifPresent(GameUtilities::playClip);
      return ball;
    }

    return null;
  }
}
