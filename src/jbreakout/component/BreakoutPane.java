package jbreakout.component;

import static jbreakout.common.Constants.COLOR_OF_DRAWING_STRING;
import static jbreakout.common.Constants.CURRENT_ROUND_DRAWING_AREA_X;
import static jbreakout.common.Constants.CURRENT_ROUND_DRAWING_AREA_Y;
import static jbreakout.common.Constants.CURRENT_TURN_DRAWING_AREA_X;
import static jbreakout.common.Constants.CURRENT_TURN_DRAWING_AREA_Y;
import static jbreakout.common.Constants.FONT_OF_DRAWING_STRING;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import jbreakout.common.AbstractBreakoutPane;
import jbreakout.common.Brick;
import jbreakout.common.Velocity;
import jbreakout.factory.AbstractBallFactory;
import jbreakout.factory.AbstractBrickFactory;
import jbreakout.factory.AbstractPaddleFactory;
import jglib.util.GameUtilities;

public class BreakoutPane extends AbstractBreakoutPane {

  private boolean isGameOver = false;
  private int bricksEliminated = 0;
  private int round = 1;
  private int score = 0;
  private int turn = 1;
  private int totalScore = 0;

  public BreakoutPane(Point point) {
    super(point);
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
      // fallClip.ifPresent(GameUtilities::playClip);
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

    g.setColor(COLOR_OF_DRAWING_STRING);
    g.setFont(FONT_OF_DRAWING_STRING);
    GameUtilities.drawString(
        g,
        CURRENT_ROUND_DRAWING_AREA_X,
        CURRENT_ROUND_DRAWING_AREA_Y,
        String.valueOf(round),
        String.format(" %03d", score));
    GameUtilities.drawStringFromTopRight(
        g,
        CURRENT_TURN_DRAWING_AREA_X,
        CURRENT_TURN_DRAWING_AREA_Y,
        String.format("%d     ", turn),
        String.format(" %03d", totalScore));

    for (Brick brick : bricks()) {
      brick.draw(g);
    }

    ball().draw(g);

    paddle().draw(g);

    if (isGameOver) {
      g.setColor(COLOR_OF_DRAWING_STRING);
      g.setFont(FONT_OF_DRAWING_STRING);
      GameUtilities.drawStringAfterCentering(g, width() / 2, height() / 2, "Game Over!");
    }
  }

  @Override
  public int maxTurns() {
    return 3;
  }

  @Override
  public int maxRounds() {
    return 2;
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
  protected long roundInterval() {
    return 3000L;
  }

  @Override
  protected Velocity ballVelocity() {
    final int vx = (5 + (int) ((5 + 1) * Math.random())) * (Math.random() >= 0.5 ? 1 : -1);
    final int vy = 5 + (int) ((5 + 1) * Math.random());
    return Velocity.of(vx, vy);
  }

  @Override
  protected Point bricksPoint() {
    return new Point(4, 100);
  }

  @Override
  protected Point paddlePoint() {
    return new Point(width() / 2 - paddleFactory().width() / 2, 580);
  }

  @Override
  protected AbstractBrickFactory<?> brickFactory() {
    return new SoundBrickFactory();
  }

  @Override
  protected AbstractBallFactory<?> ballFactory() {
    return new SoundBallFactory();
  }

  @Override
  protected AbstractPaddleFactory<?> paddleFactory() {
    return new SoundPaddleFactory();
  }
}
