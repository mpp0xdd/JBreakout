package jbreakout.component;

import java.awt.Point;
import java.util.Optional;
import javax.sound.sampled.Clip;
import jbreakout.common.Velocity;
import jbreakout.resource.SoundFactory;
import jglib.util.GameUtilities;

class SoundBall extends Ball {

  private final Optional<Clip> bounceClip = SoundFactory.ballBounceClip();

  public SoundBall(Velocity velocity, Point location) {
    super(velocity, location);
  }

  @Override
  public void bounceX() {
    super.bounceX();
    bounceClip.ifPresent(GameUtilities::playClip);
  }

  @Override
  public void bounceY() {
    super.bounceY();
    bounceClip.ifPresent(GameUtilities::playClip);
  }

  @Override
  public void bounce() {
    super.bounce();
    bounceClip.ifPresent(GameUtilities::playClip);
  }
}
