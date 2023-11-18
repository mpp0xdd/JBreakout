package jbreakout.common;

import static jbreakout.common.Constants.BALL_RELOCATION_INTERVAL;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

public abstract class AbstractBreakoutPane implements Drawable {

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

  public void movePaddle(int x) {
    int halfWidthOfPaddle = paddle.width() / 2;
    if (halfWidthOfPaddle <= x && x <= width() - halfWidthOfPaddle) {
      paddle.setX(x - halfWidthOfPaddle);
    }
  }

  protected Brick[] bricks() {
    return bricks;
  }

  protected Ball ball() {
    return ball;
  }

  protected Paddle paddle() {
    return paddle;
  }

  protected void relocateBall() {
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

  public abstract void update();

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
}
