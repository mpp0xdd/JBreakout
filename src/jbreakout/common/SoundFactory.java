package jbreakout.common;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.sound.sampled.Clip;
import jglib.util.GameUtilities;

public final class SoundFactory {

  private SoundFactory() {
    // restrict instantiation
  }

  private static final List<Optional<Clip>> declaredSounds;

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

  static {
    ArrayList<Optional<Clip>> _declaredSounds = new ArrayList<>();
    for (Field field : SoundFactory.class.getDeclaredFields()) {
      if (!Modifier.isPrivate(field.getModifiers())) {
        continue;
      }
      if (!Modifier.isStatic(field.getModifiers())) {
        continue;
      }
      if (!Modifier.isFinal(field.getModifiers())) {
        continue;
      }
      if (!field.getType().equals(Optional.class)) {
        continue;
      }
      if (!(field.getGenericType() instanceof ParameterizedType)) {
        continue;
      }
      if (!((ParameterizedType) field.getGenericType())
          .getActualTypeArguments()[0].equals(Clip.class)) {
        continue;
      }
      try {
        @SuppressWarnings("unchecked")
        Optional<Clip> optional = (Optional<Clip>) field.get(null);
        _declaredSounds.add(optional);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    declaredSounds = Collections.unmodifiableList(_declaredSounds);
  }

  public static List<Optional<Clip>> declaredSounds() {
    return declaredSounds;
  }

  public static List<Optional<Clip>> availableSounds() {
    return declaredSounds.stream().filter(Optional::isPresent).toList();
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
