import java.awt.Color;
import java.util.Optional;
import javax.sound.sampled.Clip;

public class SoundPaddle extends Paddle {

  private final Optional<Clip> reboundClip =
      GameUtilities.loadClip(SoundBall.class.getResource("paddle_rebound.wav"));

  public SoundPaddle(Color color, int width, int height, int x, int y) {
    super(color, width, height, x, y);
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
