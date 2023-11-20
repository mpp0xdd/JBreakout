package jbreakout.component;

import java.awt.Color;
import java.util.Objects;
import javax.sound.sampled.Clip;
import jbreakout.resource.SoundFactory;
import jglib.util.GameUtilities;

class SoundBrick extends Brick {
  private final Clip eliminateClip = SoundFactory.newBrickEliminateClip().orElse(null);

  public SoundBrick(Color color, int width, int height, int x, int y) {
    super(color, width, height, x, y);
  }

  @Override
  public void eliminate() {
    super.eliminate();
    if (Objects.nonNull(eliminateClip)) {
      GameUtilities.playClip(eliminateClip);
    }
  }
}
