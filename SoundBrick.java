import java.awt.Color;
import java.util.Optional;
import javax.sound.sampled.Clip;

public class SoundBrick extends Brick {
  private final Optional<Clip> eliminateClip =
      GameUtilities.loadClip(SoundBall.class.getResource("sounds/brick_eliminate.wav"));

  public SoundBrick(Color color, int width, int height, int x, int y) {
    super(color, width, height, x, y);
  }

  public void eliminate() {
    super.eliminate();
    eliminateClip.ifPresent(GameUtilities::playClip);
  }
}
