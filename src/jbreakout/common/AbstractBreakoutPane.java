package jbreakout.common;

import static jbreakout.common.Constants.BALL_RELOCATION_INTERVAL;
import static jbreakout.common.Constants.COLOR_OF_DRAWING_STRING;
import static jbreakout.common.Constants.CURRENT_ROUND_DRAWING_AREA_X;
import static jbreakout.common.Constants.CURRENT_ROUND_DRAWING_AREA_Y;
import static jbreakout.common.Constants.CURRENT_TURN_DRAWING_AREA_X;
import static jbreakout.common.Constants.CURRENT_TURN_DRAWING_AREA_Y;
import static jbreakout.common.Constants.FONT_OF_DRAWING_STRING;
import static jbreakout.common.Constants.GAME_ROUNDS;
import static jbreakout.common.Constants.PLAYER_MAX_TURNS;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;
import jglib.util.GameUtilities;

public abstract class AbstractBreakoutPane implements Drawable {
  private boolean isGameOver = false;
  private int currentNumOfBricksEliminated = 0;
  private int currentRound = 1;
  private int currentScore = 0;
  private int currentTurn = 1;
  private int currentTotalScore = 0;

  private Brick[] bricks;
  private Ball ball;
  private Paddle paddle;

  public void initializeComponent() {
    this.bricks = brickFactory().createBricks(bricksPoint());
    this.ball = ballFactory().createBall(ballVelocity(), ballPoint());
    this.paddle = paddleFactory().createPaddle(paddlePoint());
  }

  public void startRound() {
    this.bricks = brickFactory().createBricks(bricksPoint());
    relocateBall();
  }

  public void update() {
    if (!ball.isVisible()) {
      return;
    }

    ball.move();
    if (ball.getX() < 0) {
      ball.bounceX();
      ball.setX(0);
    }
    if (ball.getX() + ball.size() > width()) {
      ball.bounceX();
      ball.setX(width() - ball.size());
    }
    if (ball.getY() < 0) {
      ball.bounceY();
      ball.setY(0);
    }
    // ボールをパドルで取りそこなったとき
    if (ball.getY() > height()) {
      // fallClip.ifPresent(GameUtilities::playClip);
      ball.setVisible(false);
      if (currentTurn == PLAYER_MAX_TURNS) {
        isGameOver = true;
      } else {
        currentTurn++;
        relocateBall();
      }
      return;
    }

    paddle.rebound(ball);

    for (Brick brick : bricks) {
      if (brick.rebound(ball) != ball) {
        continue;
      }

      currentScore += brick.score();
      brick.eliminate();
      currentNumOfBricksEliminated++;
      if (currentNumOfBricksEliminated == bricks.length) {
        currentTotalScore += currentScore;
        if (currentRound == GAME_ROUNDS) {
          isGameOver = true;
        } else {
          currentRound++;
          currentScore = 0;
          currentNumOfBricksEliminated = 0;
          startRound();
        }
      }
      break;
    }
  }

  public void movePaddle(int x) {
    int halfWidthOfPaddle = paddle.width() / 2;
    if (halfWidthOfPaddle <= x && x <= width() - halfWidthOfPaddle) {
      paddle.setX(x - halfWidthOfPaddle);
    }
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, width(), height());

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
      GameUtilities.drawStringAfterCentering(g, width() / 2, height() / 2, "Game Over!");
    }
  }

  public abstract int width();

  public abstract int height();

  protected abstract Velocity ballVelocity();

  protected abstract Point bricksPoint();

  protected abstract Point paddlePoint();

  protected abstract AbstractBrickFactory<?> brickFactory();

  protected abstract AbstractBallFactory<?> ballFactory();

  protected abstract AbstractPaddleFactory<?> paddleFactory();

  private Point ballPoint() {
    final int x = (int) ((width() - ballFactory().size()) * Math.random());

    final int bricksY = bricksPoint().y;
    final int numOfBrickRows = brickFactory().rows();
    final int brickHeight = brickFactory().height();
    final int bricksMargin = brickFactory().margin();
    final int y = bricksY + numOfBrickRows * (brickHeight + bricksMargin);

    return new Point(x, y);
  }

  private void relocateBall() {
    this.ball = ballFactory().createBall(ballVelocity(), ballPoint());
    Timer roundTimer = new Timer();
    roundTimer.schedule(
        new TimerTask() {
          @Override
          public void run() {
            ball.setVisible(true);
            roundTimer.cancel();
          }
        },
        BALL_RELOCATION_INTERVAL);
  }
}
