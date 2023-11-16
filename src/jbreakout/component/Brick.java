package jbreakout.component;

import static java.awt.Color.GREEN;
import static java.awt.Color.ORANGE;
import static java.awt.Color.RED;
import static java.awt.Color.YELLOW;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import jbreakout.common.Ball;

class Brick implements jbreakout.common.Brick {

  protected Color color;
  protected int width;
  protected int height;
  protected int x;
  protected int y;
  protected boolean eliminated = false;

  public Brick(Color color, int width, int height, int x, int y) {
    this.color = color;
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;
  }

  @Override
  public int score() {
    if (color.equals(YELLOW)) {
      return 1;
    } else if (color.equals(GREEN)) {
      return 3;
    } else if (color.equals(ORANGE)) {
      return 5;
    } else if (color.equals(RED)) {
      return 7;
    } else {
      return 1;
    }
  }

  @Override
  public int x() {
    return x;
  }

  @Override
  public int y() {
    return y;
  }

  @Override
  public int width() {
    return width;
  }

  @Override
  public int height() {
    return height;
  }

  @Override
  public void eliminate() {
    eliminated = true;
  }

  @Override
  public void draw(Graphics g) {
    if (eliminated) {
      return;
    }

    g.setColor(color);
    g.fillRect(x, y, width, height);
  }

  @Override
  public Ball rebound(Ball ball) {
    if (eliminated) {
      return null;
    }

    Rectangle thisRect = asRectangle();

    Line2D.Double topLineOfBall =
        new Line2D.Double(ball.getX(), ball.getY(), ball.getX() + ball.size(), ball.getY());

    Line2D.Double bottomLineOfBall =
        new Line2D.Double(
            ball.getX(),
            ball.getY() + ball.size(),
            ball.getX() + ball.size(),
            ball.getY() + ball.size());

    Line2D.Double leftLineOfBall =
        new Line2D.Double(ball.getX(), ball.getY(), ball.getX(), ball.getY() + ball.size());

    Line2D.Double rightLineOfBall =
        new Line2D.Double(
            ball.getX() + ball.size(),
            ball.getY(),
            ball.getX() + ball.size(),
            ball.getY() + ball.size());

    if (thisRect.intersectsLine(topLineOfBall) && thisRect.intersectsLine(bottomLineOfBall)) {
      ball.bounceX();
      return ball;
    }

    if (thisRect.intersectsLine(leftLineOfBall) && thisRect.intersectsLine(rightLineOfBall)) {
      ball.bounceY();
      return ball;
    }

    if (thisRect.intersectsLine(topLineOfBall) || thisRect.intersectsLine(bottomLineOfBall)) {
      ball.bounce();
      return ball;
    }

    return null;
  }

  @Override
  public Rectangle asRectangle() {
    return new Rectangle(x, y, width, height);
  }
}
