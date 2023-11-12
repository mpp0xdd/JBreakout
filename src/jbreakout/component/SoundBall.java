package jbreakout.component;

import java.util.Optional;
import javax.sound.sampled.Clip;
import jbreakout.resource.SoundFactory;
import jglib.util.GameUtilities;

public class SoundBall extends Ball {

  private final Optional<Clip> bounceClip = SoundFactory.ballBounceClip();

  public SoundBall(int vx, int vy, int x, int y) {
    super(vx, vy, x, y);
  }

  public SoundBall() {
    super();
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
