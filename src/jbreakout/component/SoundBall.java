package jbreakout.component;

import java.util.Optional;
import javax.sound.sampled.Clip;
import jbreakout.resource.SoundFactory;
import jglib.util.GameUtilities;

public class SoundBall extends Ball {

  private final Optional<Clip> bounceClip = SoundFactory.ballBounceClip();

  public SoundBall(int a, int vx, int vy, int x, int y) {
    super(a, vx, vy, x, y);
  }

  public SoundBall(int a) {
    super(a);
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
