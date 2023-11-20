package jbreakout.component;

import java.awt.Point;
import jbreakout.common.Velocity;
import jbreakout.resource.SoundFactory;
import jglib.util.GameUtilities;

class SoundBall extends Ball {

  public SoundBall(Velocity velocity, Point location, int size) {
    super(velocity, location, size);
  }

  @Override
  public void bounceX() {
    super.bounceX();
    SoundFactory.ballBounceClip().ifPresent(GameUtilities::playClip);
  }

  @Override
  public void bounceY() {
    super.bounceY();
    SoundFactory.ballBounceClip().ifPresent(GameUtilities::playClip);
  }

  @Override
  public void bounce() {
    super.bounce();
    SoundFactory.ballBounceClip().ifPresent(GameUtilities::playClip);
  }
}
