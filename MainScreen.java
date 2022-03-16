import java.awt.Color;
import static java.awt.Color.RED;
import static java.awt.Color.ORANGE;
import static java.awt.Color.GREEN;
import static java.awt.Color.YELLOW;
import java.awt.Font;
import java.awt.Graphics;


public class MainScreen extends GameScreen {
  private static final int SCREEN_WIDTH  = 480;
  private static final int SCREEN_HEIGHT = 640;

  private static final int   CURRENT_ROUND_DRAWING_AREA_X = 10;
  private static final int   CURRENT_ROUND_DRAWING_AREA_Y =  0;
  private static final int   CURRENT_TURN_DRAWING_AREA_X  = SCREEN_WIDTH - CURRENT_ROUND_DRAWING_AREA_X;
  private static final int   CURRENT_TURN_DRAWING_AREA_Y  = CURRENT_ROUND_DRAWING_AREA_Y;
  private static final Color COLOR_OF_DRAWING_STRING      = Color.WHITE;
  private static final Font  FONT_OF_DRAWING_STRING       = new Font(Font.SANS_SERIF, Font.BOLD, 43);

  private static final int     NUM_OF_BRICK_ROWS    =   8;
  private static final int     NUM_OF_BRICK_COLUMNS =  14;
  private static final Color[] COLORS_OF_BRICKS     = {RED, RED, ORANGE, ORANGE, GREEN, GREEN, YELLOW, YELLOW};
  private static final int     BRICKS_X             =   4;
  private static final int     BRICKS_Y             = 100;
  private static final int     BRICKS_MARGIN        =   4;

  private static final Color BALL_COLOR  = Color.WHITE;
  private static final int   BALL_INIT_X = (int)((SCREEN_WIDTH - Ball.SIZE) * Math.random());
  private static final int   BALL_INIT_Y = BRICKS_Y + NUM_OF_BRICK_ROWS * (Brick.HEIGHT + BRICKS_MARGIN);

  private static final Color PADDLE_COLOR  = Color.BLUE;
  private static final int   PADDLE_INIT_X = SCREEN_WIDTH / 2 - Paddle.WIDTH / 2;
  private static final int   PADDLE_INIT_Y = 600;


  private int currentRound      = 1;
  private int currentScore      = 0;
  private int currentTurn       = 1;
  private int currentTotalScore = 0;

  private Brick[] bricks = Brick.lay(NUM_OF_BRICK_ROWS, NUM_OF_BRICK_COLUMNS,
    COLORS_OF_BRICKS, BRICKS_X, BRICKS_Y, BRICKS_MARGIN);

  private Ball ball = new Ball(BALL_COLOR, BALL_INIT_X, BALL_INIT_Y);

  private Paddle paddle = new Paddle(PADDLE_COLOR, PADDLE_INIT_X, PADDLE_INIT_Y);

  public MainScreen() {
    super(SCREEN_WIDTH, SCREEN_HEIGHT);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(Color.BLACK);
    g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

    g.setColor(COLOR_OF_DRAWING_STRING);
    g.setFont(FONT_OF_DRAWING_STRING);
    GameUtilities.drawString(g, CURRENT_ROUND_DRAWING_AREA_X, CURRENT_ROUND_DRAWING_AREA_Y,
      String.valueOf(currentRound), String.format(" %03d", currentScore));
    GameUtilities.drawStringFromTopRight(g, CURRENT_TURN_DRAWING_AREA_X, CURRENT_TURN_DRAWING_AREA_Y,
      String.format("%d     ", currentTurn), String.format(" %03d", currentTotalScore));

    for(Brick brick : bricks) {
      brick.draw(g);
    }

    ball.draw(g);

    paddle.draw(g);
  }

  @Override
  protected void runGameLoop() {}
}
