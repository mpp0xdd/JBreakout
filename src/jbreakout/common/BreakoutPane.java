package jbreakout.common;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import jbreakout.common.factory.BallFactory;
import jbreakout.common.factory.BrickFactory;
import jbreakout.common.factory.PaddleFactory;

public abstract class BreakoutPane implements Drawable, Rectangular {

  private final BrickFactory<?> brickFactory;
  private final BallFactory<?> ballFactory;
  private final PaddleFactory<?> paddleFactory;

  private Brick[] bricks;
  private Ball ball;
  private Paddle paddle;

  public BreakoutPane(
      BrickFactory<?> brickFactory, BallFactory<?> ballFactory, PaddleFactory<?> paddleFactory) {
    this.brickFactory = Objects.requireNonNull(brickFactory);
    this.ballFactory = Objects.requireNonNull(ballFactory);
    this.paddleFactory = Objects.requireNonNull(paddleFactory);
    initializeComponent();
  }

  public abstract void update();

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
  public final int x() {
    return 0;
  }

  @Override
  public final int y() {
    return 0;
  }

  protected final BrickFactory<?> brickFactory() {
    return brickFactory;
  }

  protected final BallFactory<?> ballFactory() {
    return ballFactory;
  }

  protected final PaddleFactory<?> paddleFactory() {
    return paddleFactory;
  }

  protected final Brick[] bricks() {
    return bricks;
  }

  protected final Ball ball() {
    return ball;
  }

  protected final Paddle paddle() {
    return paddle;
  }

  protected void relocateBall() {
    this.ball = ballFactory().createBall(randomBallVelocity(), randomBallPoint());
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

  protected abstract Velocity randomBallVelocity();

  protected abstract Point bricksPoint();

  protected abstract Point randomBallPoint();

  protected abstract Point paddlePoint();

  private void initializeComponent() {
    this.bricks = brickFactory().createBricks(bricksPoint());
    this.ball = ballFactory().createBall(randomBallVelocity(), randomBallPoint());
    this.paddle = paddleFactory().createPaddle(paddlePoint());
  }
}
