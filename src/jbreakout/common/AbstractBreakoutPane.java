package jbreakout.common;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;
import jbreakout.factory.AbstractBallFactory;
import jbreakout.factory.AbstractBrickFactory;
import jbreakout.factory.AbstractPaddleFactory;

public abstract class AbstractBreakoutPane implements Drawable, Rectangular {

  private Brick[] bricks;
  private Ball ball;
  private Paddle paddle;

  public abstract void update();

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
    final Rectangle thisRect = asRectangle();

    if (!thisRect.contains(x, y())) {
      return;
    }

    final int backup = paddle.x();
    paddle.setX(x - paddle.width() / 2);
    if (!thisRect.contains(paddle.asRectangle())) {
      paddle.setX(backup);
    }
  }

  @Override
  public Rectangle asRectangle() {
    return new Rectangle(x(), y(), width(), height());
  }

  @Override
  public int x() {
    return 0;
  }

  @Override
  public int y() {
    return 0;
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
        roundInterval());
  }

  protected abstract int maxTurns();

  protected abstract int maxRounds();

  protected abstract long roundInterval();

  protected abstract Velocity ballVelocity();

  protected abstract Point bricksPoint();

  protected abstract Point ballPoint();

  protected abstract Point paddlePoint();

  protected abstract AbstractBrickFactory<?> brickFactory();

  protected abstract AbstractBallFactory<?> ballFactory();

  protected abstract AbstractPaddleFactory<?> paddleFactory();
}
