package jbreakout.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import jbreakout.common.BreakoutPane;
import jbreakout.common.Brick;
import jbreakout.common.Velocity;
import jbreakout.common.factory.BallFactory;
import jbreakout.common.factory.BrickFactory;
import jbreakout.common.factory.PaddleFactory;
import jbreakout.resource.SoundFactory;
import jglib.util.GameUtilities;
import jglib.util.StringDrawer;

public class DefaultBreakoutPane extends BreakoutPane {

  private boolean isGameOver = false;
  private int bricksEliminated = 0;
  private int round = 1;
  private int score = 0;
  private int turn = 1;
  private int totalScore = 0;

  public DefaultBreakoutPane(
      BrickFactory<?> brickFactory, BallFactory<?> ballFactory, PaddleFactory<?> paddleFactory) {
    super(brickFactory, ballFactory, paddleFactory);
  }

  @Override
  public void update() {
    if (!ball().isVisible()) {
      return;
    }

    ball().move();
    if (ball().getX() < 0) {
      ball().bounceX();
      ball().setX(0);
    }
    if (ball().getX() + ball().size() > width()) {
      ball().bounceX();
      ball().setX(width() - ball().size());
    }
    if (ball().getY() < 0) {
      ball().bounceY();
      ball().setY(0);
    }
    // ボールをパドルで取りそこなったとき
    if (ball().getY() > height()) {
      SoundFactory.ballFallClip().ifPresent(GameUtilities::playClip);
      ball().setVisible(false);
      if (turn == maxTurns()) {
        isGameOver = true;
      } else {
        turn++;
        relocateBall();
      }
      return;
    }

    paddle().rebound(ball());

    for (Brick brick : bricks()) {
      if (brick.rebound(ball()) != ball()) {
        continue;
      }

      score += brick.score();
      brick.eliminate();
      bricksEliminated++;
      if (bricksEliminated == bricks().length) {
        totalScore += score;
        if (round == maxRounds()) {
          isGameOver = true;
        } else {
          round++;
          score = 0;
          bricksEliminated = 0;
          startRound();
        }
      }
      break;
    }
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, width(), height());

    prepareToDrawString(g);
    StringDrawer.LEFT.draw(g, 10, 0, String.valueOf(round), String.format(" %03d", score));

    StringDrawer.RIGHT.draw(
        g, width() - 10, 0, String.format("%d     ", turn), String.format(" %03d", totalScore));

    for (Brick brick : bricks()) brick.draw(g);
    ball().draw(g);
    paddle().draw(g);

    if (isGameOver) {
      prepareToDrawString(g);
      StringDrawer.CENTER.draw(g, width() / 2, height() / 2, "Game Over!");
    }
  }

  @Override
  public int width() {
    return 480;
  }

  @Override
  public int height() {
    return 640;
  }

  @Override
  protected int maxTurns() {
    return 3;
  }

  @Override
  protected int maxRounds() {
    return 2;
  }

  @Override
  protected long roundInterval() {
    return 3000L;
  }

  @Override
  protected Velocity randomBallVelocity() {
    final int vx = (5 + (int) ((5 + 1) * Math.random())) * (Math.random() >= 0.5 ? 1 : -1);
    final int vy = 5 + (int) ((5 + 1) * Math.random());
    return Velocity.of(vx, vy);
  }

  @Override
  protected Point bricksPoint() {
    return new Point(4, 100);
  }

  @Override
  protected Point randomBallPoint() {
    final int x = (int) ((width() - ballFactory().size()) * Math.random());

    final int bricksY = bricksPoint().y;
    final int numOfBrickRows = brickFactory().rows();
    final int brickHeight = brickFactory().height();
    final int bricksMargin = brickFactory().margin();
    final int y = bricksY + numOfBrickRows * (brickHeight + bricksMargin);

    return new Point(x, y);
  }

  @Override
  protected Point paddlePoint() {
    return new Point(width() / 2 - paddleFactory().width() / 2, 580);
  }

  private void prepareToDrawString(Graphics g) {
    g.setColor(Color.WHITE);
    g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 43));
  }
}
