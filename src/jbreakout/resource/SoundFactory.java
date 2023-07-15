package jbreakout.resource;

import java.util.Optional;
import javax.sound.sampled.Clip;
import jglib.util.GameUtilities;

public final class SoundFactory {

  private SoundFactory() {
    // restrict instantiation
  }

  public static Optional<Clip> mainScreenBgmClip() {
    Optional<Clip> clip = GameUtilities.loadClip(SoundFactory.class.getResource("sounds/bgm.wav"));
    clip.ifPresent(c -> GameUtilities.setVolume(c, 0.7f));
    return clip;
  }

  public static Optional<Clip> ballFallClip() {
    Optional<Clip> clip =
        GameUtilities.loadClip(SoundFactory.class.getResource("sounds/ball_fall.wav"));
    clip.ifPresent(c -> GameUtilities.setVolume(c, 0.7f));
    return clip;
  }

  public static Optional<Clip> ballBounceClip() {
    Optional<Clip> clip =
        GameUtilities.loadClip(SoundFactory.class.getResource("sounds/ball_bounce.wav"));
    clip.ifPresent(c -> GameUtilities.setVolume(c, 0.75f));
    return clip;
  }

  public static Optional<Clip> brickEliminateClip() {
    Optional<Clip> clip =
        GameUtilities.loadClip(SoundFactory.class.getResource("sounds/brick_eliminate.wav"));
    clip.ifPresent(c -> GameUtilities.setVolume(c, 0.75f));
    return clip;
  }

  public static Optional<Clip> paddleReboundClip() {
    Optional<Clip> clip =
        GameUtilities.loadClip(SoundFactory.class.getResource("sounds/paddle_rebound.wav"));
    clip.ifPresent(c -> GameUtilities.setVolume(c, 0.75f));
    return clip;
  }
}
