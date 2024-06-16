package jbreakout.resource;

import java.util.Optional;
import javax.sound.sampled.Clip;
import jglib.util.GameUtilities;

public final class SoundFactory {

  private SoundFactory() {
    // restrict instantiation
  }

  private static final Clip MAIN_SCREEN_BGM;
  private static final Clip BALL_FALL;
  private static final Clip BALL_BOUNCE;
  private static final Clip PADDLE_REBOUND;

  static {
    Optional<Clip> clip;

    clip = GameUtilities.loadClip(SoundFactory.class, "sounds/bgm.wav");
    clip.ifPresent(c -> GameUtilities.setVolume(c, 0.7f));
    MAIN_SCREEN_BGM = clip.orElse(null);

    clip = GameUtilities.loadClip(SoundFactory.class, "sounds/ball_fall.wav");
    clip.ifPresent(c -> GameUtilities.setVolume(c, 0.7f));
    BALL_FALL = clip.orElse(null);

    clip = GameUtilities.loadClip(SoundFactory.class, "sounds/ball_bounce.wav");
    clip.ifPresent(c -> GameUtilities.setVolume(c, 0.75f));
    BALL_BOUNCE = clip.orElse(null);

    clip = GameUtilities.loadClip(SoundFactory.class, "sounds/paddle_rebound.wav");
    clip.ifPresent(c -> GameUtilities.setVolume(c, 0.75f));
    PADDLE_REBOUND = clip.orElse(null);
  }

  public static Optional<Clip> mainScreenBgmClip() {
    return Optional.ofNullable(MAIN_SCREEN_BGM);
  }

  public static Optional<Clip> ballFallClip() {
    return Optional.ofNullable(BALL_FALL);
  }

  public static Optional<Clip> ballBounceClip() {
    return Optional.ofNullable(BALL_BOUNCE);
  }

  public static Optional<Clip> paddleReboundClip() {
    return Optional.ofNullable(PADDLE_REBOUND);
  }

  public static Optional<Clip> newBrickEliminateClip() {
    Optional<Clip> clip = GameUtilities.loadClip(SoundFactory.class, "sounds/brick_eliminate.wav");
    clip.ifPresent(c -> GameUtilities.setVolume(c, 0.75f));
    return clip;
  }
}
