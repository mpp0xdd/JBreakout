package jbreakout.screen;

import static jbreakout.common.Constants.BALL_INIT_Y;
import static jbreakout.common.Constants.BALL_RELOCATION_INTERVAL;
import static jbreakout.common.Constants.BRICKS_MARGIN;
import static jbreakout.common.Constants.BRICKS_X;
import static jbreakout.common.Constants.BRICKS_Y;
import static jbreakout.common.Constants.BRICK_HEIGHT;
import static jbreakout.common.Constants.BRICK_TO_SCORE;
import static jbreakout.common.Constants.BRICK_WIDTH;
import static jbreakout.common.Constants.COLORS_OF_BRICKS;
import static jbreakout.common.Constants.COLOR_OF_DRAWING_STRING;
import static jbreakout.common.Constants.CURRENT_ROUND_DRAWING_AREA_X;
import static jbreakout.common.Constants.CURRENT_ROUND_DRAWING_AREA_Y;
import static jbreakout.common.Constants.CURRENT_TURN_DRAWING_AREA_X;
import static jbreakout.common.Constants.CURRENT_TURN_DRAWING_AREA_Y;
import static jbreakout.common.Constants.FONT_OF_DRAWING_STRING;
import static jbreakout.common.Constants.GAME_LOOP_INTERVAL;
import static jbreakout.common.Constants.GAME_ROUNDS;
import static jbreakout.common.Constants.NUM_OF_BRICK_COLUMNS;
import static jbreakout.common.Constants.NUM_OF_BRICK_ROWS;
import static jbreakout.common.Constants.PADDLE_HEIGHT;
import static jbreakout.common.Constants.PADDLE_INIT_X;
import static jbreakout.common.Constants.PADDLE_INIT_Y;
import static jbreakout.common.Constants.PADDLE_WIDTH;
import static jbreakout.common.Constants.PLAYER_MAX_TURNS;
import static jbreakout.common.Constants.RANDOM_BALL_VX;
import static jbreakout.common.Constants.RANDOM_BALL_VY;
import static jbreakout.common.Constants.RANDOM_BALL_X;
import static jbreakout.common.Constants.SCREEN_HEIGHT;
import static jbreakout.common.Constants.SCREEN_WIDTH;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.Clip;
import jbreakout.common.Ball;
import jbreakout.common.Brick;
import jbreakout.common.Paddle;
import jbreakout.component.SoundBall;
import jbreakout.component.SoundPaddle;
import jbreakout.resource.SoundFactory;
import jglib.component.GameScreen;
import jglib.util.GameUtilities;

public class MainScreen extends GameScreen {

  private final Optional<Clip> bgmClip = SoundFactory.mainScreenBgmClip();
  private final Optional<Clip> fallClip = SoundFactory.ballFallClip();
  private boolean isGameOver = false;
  private int currentNumOfBricksEliminated = 0;
  private int currentRound = 1;
  private int currentScore = 0;
  private int currentTurn = 1;
  private int currentTotalScore = 0;

  private Brick[] bricks =
      jbreakout.component.Brick.lay(
          NUM_OF_BRICK_ROWS,
          NUM_OF_BRICK_COLUMNS,
          COLORS_OF_BRICKS,
          BRICK_WIDTH,
          BRICK_HEIGHT,
          BRICKS_X,
          BRICKS_Y,
          BRICKS_MARGIN);

  private Ball ball = new SoundBall();

  private Paddle paddle =
      new SoundPaddle(PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_INIT_X, PADDLE_INIT_Y);

  public MainScreen() {
    super(SCREEN_WIDTH, SCREEN_HEIGHT);
    setGameLoopInterval(GAME_LOOP_INTERVAL);

    addMouseMotionListener(
        new MouseMotionAdapter() {
          @Override
          public void mouseMoved(MouseEvent e) {
            int x = e.getX();
            int halfWidthOfPaddle = paddle.width() / 2;
            if (halfWidthOfPaddle <= x && x <= SCREEN_WIDTH - halfWidthOfPaddle) {
              paddle.setX(x - halfWidthOfPaddle);
            }
          }
        });
  }

  public void playBGM() {
    bgmClip.ifPresent(GameUtilities::repeatClip);
  }

  public void activateBallRelocationTimer() {
    ball.setVisible(false);
    ball.setVX(RANDOM_BALL_VX.getAsInt());
    ball.setVY(RANDOM_BALL_VY.getAsInt());
    ball.setX(RANDOM_BALL_X.getAsInt());
    ball.setY(BALL_INIT_Y);

    Timer ballRelocationTimer = new Timer();
    ballRelocationTimer.schedule(
        new TimerTask() {
          @Override
          public void run() {
            ball.setVisible(true);
            ballRelocationTimer.cancel();
          }
        },
        BALL_RELOCATION_INTERVAL);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(Color.BLACK);
    g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

    g.setColor(COLOR_OF_DRAWING_STRING);
    g.setFont(FONT_OF_DRAWING_STRING);
    GameUtilities.drawString(
        g,
        CURRENT_ROUND_DRAWING_AREA_X,
        CURRENT_ROUND_DRAWING_AREA_Y,
        String.valueOf(currentRound),
        String.format(" %03d", currentScore));
    GameUtilities.drawStringFromTopRight(
        g,
        CURRENT_TURN_DRAWING_AREA_X,
        CURRENT_TURN_DRAWING_AREA_Y,
        String.format("%d     ", currentTurn),
        String.format(" %03d", currentTotalScore));

    for (Brick brick : bricks) {
      brick.draw(g);
    }

    ball.draw(g);

    paddle.draw(g);

    if (isGameOver) {
      g.setColor(COLOR_OF_DRAWING_STRING);
      g.setFont(FONT_OF_DRAWING_STRING);
      GameUtilities.drawStringAfterCentering(g, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, "Game Over!");
    }
  }

  @Override
  protected void runGameLoop() {
    if (!ball.isVisible()) {
      repaint();
      return;
    }

    ball.move();
    if (ball.getX() < 0) {
      ball.bounceX();
      ball.setX(0);
    }
    if (ball.getX() + ball.size() > SCREEN_WIDTH) {
      ball.bounceX();
      ball.setX(SCREEN_WIDTH - ball.size());
    }
    if (ball.getY() < 0) {
      ball.bounceY();
      ball.setY(0);
    }
    // ボールをパドルで取りそこなったとき
    if (ball.getY() > SCREEN_HEIGHT) {
      fallClip.ifPresent(GameUtilities::playClip);
      ball.setVisible(false);
      if (currentTurn == PLAYER_MAX_TURNS) {
        stopGameLoop();
        isGameOver = true;
      } else {
        currentTurn++;
        activateBallRelocationTimer();
      }
      repaint();
      return;
    }

    paddle.rebound(ball);

    for (Brick brick : bricks) {
      if (brick.rebound(ball) != ball) {
        continue;
      }

      currentScore += BRICK_TO_SCORE.applyAsInt(brick);
      brick.eliminate();
      currentNumOfBricksEliminated++;
      if (currentNumOfBricksEliminated == bricks.length) {
        currentTotalScore += currentScore;
        if (currentRound == GAME_ROUNDS) {
          stopGameLoop();
          isGameOver = true;
        } else {
          currentRound++;
          activateBallRelocationTimer();
          jbreakout.component.Brick.repair(bricks);
          currentScore = 0;
          currentNumOfBricksEliminated = 0;
        }
      }
      break;
    }

    repaint();
  }
}
