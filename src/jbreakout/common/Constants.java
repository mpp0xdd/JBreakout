package jbreakout.common;

import static java.awt.Color.GREEN;
import static java.awt.Color.ORANGE;
import static java.awt.Color.RED;
import static java.awt.Color.YELLOW;
import java.awt.Color;
import java.awt.Font;
import java.util.function.IntSupplier;
import java.util.function.ToIntFunction;

public final class Constants {

  private Constants() {
    // restrict instantiation
  }

  public static final int SCREEN_WIDTH = 480;
  public static final int SCREEN_HEIGHT = 640;

  public static final long GAME_LOOP_INTERVAL = 16;
  public static final long BALL_RELOCATION_INTERVAL = 3000;

  public static final int PLAYER_MAX_TURNS = 3;
  public static final int GAME_ROUNDS = 2;

  public static final int CURRENT_ROUND_DRAWING_AREA_X = 10;
  public static final int CURRENT_ROUND_DRAWING_AREA_Y = 0;
  public static final int CURRENT_TURN_DRAWING_AREA_X = SCREEN_WIDTH - CURRENT_ROUND_DRAWING_AREA_X;
  public static final int CURRENT_TURN_DRAWING_AREA_Y = CURRENT_ROUND_DRAWING_AREA_Y;
  public static final Color COLOR_OF_DRAWING_STRING = Color.WHITE;
  public static final Font FONT_OF_DRAWING_STRING = new Font(Font.SANS_SERIF, Font.BOLD, 43);

  public static final int NUM_OF_BRICK_ROWS = 8;
  public static final int BRICK_HEIGHT = 10;
  public static final int BRICKS_X = 4;
  public static final int BRICKS_Y = 100;
  public static final int BRICKS_MARGIN = 4;
  public static final ToIntFunction<Brick> BRICK_TO_SCORE =
      brick -> {
        Color brickColor = brick.color();
        if (brickColor.equals(YELLOW)) {
          return 1;
        } else if (brickColor.equals(GREEN)) {
          return 3;
        } else if (brickColor.equals(ORANGE)) {
          return 5;
        } else if (brickColor.equals(RED)) {
          return 7;
        } else {
          return 1;
        }
      };

  public static final int BALL_SIZE = 10;
  public static final IntSupplier RANDOM_BALL_X =
      () -> (int) ((SCREEN_WIDTH - BALL_SIZE) * Math.random());
  public static final int BALL_INIT_Y =
      BRICKS_Y + NUM_OF_BRICK_ROWS * (BRICK_HEIGHT + BRICKS_MARGIN);
  public static final IntSupplier RANDOM_BALL_VX =
      () -> (5 + (int) ((5 + 1) * Math.random())) * (Math.random() >= 0.5 ? 1 : -1);
  public static final IntSupplier RANDOM_BALL_VY = () -> 5 + (int) ((5 + 1) * Math.random());

  public static final int PADDLE_WIDTH = 60;
  public static final int PADDLE_HEIGHT = 10;
  public static final int PADDLE_INIT_X = SCREEN_WIDTH / 2 - PADDLE_WIDTH / 2;
  public static final int PADDLE_INIT_Y = 580;
}
