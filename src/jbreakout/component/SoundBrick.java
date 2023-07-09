package jbreakout.component;

import java.awt.Color;
import java.util.Optional;
import javax.sound.sampled.Clip;
import jbreakout.factories.SoundFactory;
import jglib.util.GameUtilities;

public class SoundBrick extends Brick {
  private final Optional<Clip> eliminateClip = SoundFactory.brickEliminateClip();

  public SoundBrick(Color color, int width, int height, int x, int y) {
    super(color, width, height, x, y);
  }

  @Override
  public void eliminate() {
    super.eliminate();
    eliminateClip.ifPresent(GameUtilities::playClip);
  }
}
