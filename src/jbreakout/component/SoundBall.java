package jbreakout.component;

import java.awt.Color;
import java.util.Optional;
import javax.sound.sampled.Clip;
import jbreakout.resource.SoundFactory;
import jglib.util.GameUtilities;

public class SoundBall extends Ball {

  private final Optional<Clip> bounceClip = SoundFactory.ballBounceClip();

  public SoundBall(Color color, int size, int a, int vx, int vy, int x, int y) {
    super(color, size, a, vx, vy, x, y);
  }

  public SoundBall(Color color, int size, int a) {
    super(color, size, a);
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
