package jbreakout.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import jbreakout.common.Ball;

public class Brick implements jbreakout.common.Brick {

  public static void repair(jbreakout.common.Brick[] bricks) {
    for (jbreakout.common.Brick brick : bricks) {
      brick.repair();
    }
  }

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
  public Color color() {
    return color;
  }

  @Override
  public int width() {
    return width;
  }

  @Override
  public void eliminate() {
    eliminated = true;
  }

  @Override
  public void repair() {
    eliminated = false;
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

    Rectangle thisRect = new Rectangle(x, y, width, height);

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
}
