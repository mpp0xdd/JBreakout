package jbreakout.factories;

import java.util.Optional;
import javax.sound.sampled.Clip;
import jglib.util.GameUtilities;

public final class SoundFactory {

  private SoundFactory() {
    // restrict instantiation
  }

  private static final Optional<Clip> mainScreenBgmClip;
  private static final Optional<Clip> ballFallClip;
  private static final Optional<Clip> ballBounceClip;
  private static final Optional<Clip> brickEliminateClip;
  private static final Optional<Clip> paddleReboundClip;

  static {
    mainScreenBgmClip = GameUtilities.loadClip(SoundFactory.class.getResource("sounds/bgm.wav"));
    mainScreenBgmClip.ifPresent(clip -> GameUtilities.setVolume(clip, 0.7f));

    ballFallClip = GameUtilities.loadClip(SoundFactory.class.getResource("sounds/ball_fall.wav"));
    ballFallClip.ifPresent(clip -> GameUtilities.setVolume(clip, 0.7f));

    ballBounceClip =
        GameUtilities.loadClip(SoundFactory.class.getResource("sounds/ball_bounce.wav"));
    ballBounceClip.ifPresent(clip -> GameUtilities.setVolume(clip, 0.75f));

    brickEliminateClip =
        GameUtilities.loadClip(SoundFactory.class.getResource("sounds/brick_eliminate.wav"));
    brickEliminateClip.ifPresent(clip -> GameUtilities.setVolume(clip, 0.75f));

    paddleReboundClip =
        GameUtilities.loadClip(SoundFactory.class.getResource("sounds/paddle_rebound.wav"));
    paddleReboundClip.ifPresent(clip -> GameUtilities.setVolume(clip, 0.75f));
  }

  public static Optional<Clip> mainScreenBgmClip() {
    return mainScreenBgmClip;
  }

  public static Optional<Clip> ballFallClip() {
    return ballFallClip;
  }

  public static Optional<Clip> ballBounceClip() {
    return ballBounceClip;
  }

  public static Optional<Clip> brickEliminateClip() {
    return brickEliminateClip;
  }

  public static Optional<Clip> paddleReboundClip() {
    return paddleReboundClip;
  }
}
