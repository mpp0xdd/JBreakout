import java.awt.Color;
import static java.awt.Color.RED;
import static java.awt.Color.ORANGE;
import static java.awt.Color.GREEN;
import static java.awt.Color.YELLOW;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.util.TimerTask;
import java.util.Timer;
import java.util.function.ToIntFunction;


public class MainScreen extends GameScreen {
  private static final int SCREEN_WIDTH  = 480;
  private static final int SCREEN_HEIGHT = 640;

  private static final long GAME_START_INTERVAL = 3000;
  private static final long GAME_LOOP_INTERVAL  = 16;

  private static final int   CURRENT_ROUND_DRAWING_AREA_X = 10;
  private static final int   CURRENT_ROUND_DRAWING_AREA_Y =  0;
  private static final int   CURRENT_TURN_DRAWING_AREA_X  = SCREEN_WIDTH - CURRENT_ROUND_DRAWING_AREA_X;
  private static final int   CURRENT_TURN_DRAWING_AREA_Y  = CURRENT_ROUND_DRAWING_AREA_Y;
  private static final Color COLOR_OF_DRAWING_STRING      = Color.WHITE;
  private static final Font  FONT_OF_DRAWING_STRING       = new Font(Font.SANS_SERIF, Font.BOLD, 43);

  private static final int     NUM_OF_BRICK_ROWS    =   8;
  private static final int     NUM_OF_BRICK_COLUMNS =  14;
  private static final Color[] COLORS_OF_BRICKS     = {RED, RED, ORANGE, ORANGE, GREEN, GREEN, YELLOW, YELLOW};
  public static final int      BRICK_WIDTH          =  30;
  public static final int      BRICK_HEIGHT         =  10;
  private static final int     BRICKS_X             =   4;
  private static final int     BRICKS_Y             = 100;
  private static final int     BRICKS_MARGIN        =   4;
  private static final ToIntFunction<Brick> brickColorToPoint = brick -> {
    Color brickColor = brick.getColor();
    if(brickColor == YELLOW) {
      return 1;
    } else if(brickColor == GREEN) {
      return 3;
    } else if(brickColor == ORANGE) {
      return 5;
    } else if(brickColor == RED) {
      return 7;
    } else {
      return 1;
    }
  };

  private static final Color BALL_COLOR   = Color.WHITE;
  private static final int   BALL_SIZE    = 10;
  private static final int   BALL_INIT_X  = (int)((SCREEN_WIDTH - BALL_SIZE) * Math.random());
  private static final int   BALL_INIT_Y  = BRICKS_Y + NUM_OF_BRICK_ROWS * (BRICK_HEIGHT + BRICKS_MARGIN);
  private static final int   BALL_INIT_VX = (5 + (int)((5 + 1) * Math.random())) * (Math.random() >= 0.5 ? 1 : -1);
  private static final int   BALL_INIT_VY = 5 + (int)((5 + 1) * Math.random());

  private static final Color PADDLE_COLOR  = Color.BLUE;
  public static final int    PADDLE_WIDTH  = 60;
  public static final int    PADDLE_HEIGHT = 10;
  private static final int   PADDLE_INIT_X = SCREEN_WIDTH / 2 - PADDLE_WIDTH / 2;
  private static final int   PADDLE_INIT_Y = 600;


  private int currentRound      = 1;
  private int currentScore      = 0;
  private int currentTurn       = 1;
  private int currentTotalScore = 0;

  private Brick[] bricks = Brick.lay(NUM_OF_BRICK_ROWS, NUM_OF_BRICK_COLUMNS,
    COLORS_OF_BRICKS, BRICK_WIDTH, BRICK_HEIGHT, BRICKS_X, BRICKS_Y, BRICKS_MARGIN);

  private Ball ball = new Ball(BALL_COLOR, BALL_SIZE, BALL_INIT_X, BALL_INIT_Y,
    BALL_INIT_VX, BALL_INIT_VY);

  private Paddle paddle = new Paddle(PADDLE_COLOR, PADDLE_WIDTH, PADDLE_HEIGHT,
    PADDLE_INIT_X, PADDLE_INIT_Y);

  public MainScreen() {
    super(SCREEN_WIDTH, SCREEN_HEIGHT);

    addMouseMotionListener(
      new MouseMotionAdapter() {
        @Override
        public void mouseMoved(MouseEvent e) {
          int x = e.getX();
          int halfWidthOfPaddle = paddle.getWidth() / 2;
          if(halfWidthOfPaddle <= x && x <= SCREEN_WIDTH - halfWidthOfPaddle) {
            paddle.setX(x - halfWidthOfPaddle);
          }
        }
      }
    );
  }

  public void activateGameStartTimer() {
    if(ball.isVisible()) {
      throw (new IllegalStateException("既に開始されているゲームのゲーム開始タイマを起動することはできません。"));
    }

    Timer gameStartTimer = new Timer();
    gameStartTimer.schedule(
      new TimerTask() {
        @Override
        public void run() {
          ball.setVisible(true);
          gameStartTimer.cancel();
        }
      },
      GAME_START_INTERVAL
    );
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
  protected void runGameLoop() {
    ball.move();
    if(ball.getX() < 0 || ball.getX() + ball.getSize() > SCREEN_WIDTH) {
      ball.bounceX();
    }
    if(ball.getY() < 0 || ball.getY() + ball.getSize() > SCREEN_HEIGHT) {
      ball.bounceY();
    }

    paddle.rebound(ball);

    for(Brick brick : bricks) {
      if(brick.rebound(ball) == ball) {
        brick.eliminate();
        currentScore += brickColorToPoint.applyAsInt(brick);
        break;
      }
    }

    repaint();
    GameUtilities.sleep(GAME_LOOP_INTERVAL);
  }
}
