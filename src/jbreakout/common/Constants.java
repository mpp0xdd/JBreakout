package jbreakout.common;

import java.awt.Color;
import java.awt.Font;

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
}
