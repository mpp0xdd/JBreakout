import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Line2D;


public class Brick {

  public static Brick[] lay(int rows, int columns, Color[] colors, int width, int height, int x, int y, int margin) {
    if(colors.length != rows) {
      throw (new IllegalArgumentException("レンガの色情報が正しくありません"));
    }

    Brick[] result = new Brick[rows * columns];
    for(int i = 0; i < rows; i++) {
      int brickY = i * height + y;
      brickY += i * margin;
      for(int j = 0; j < columns; j++) {
        int brickX = j * width + x;
        brickX += j * margin;
        result[i * columns + j] = new Brick(colors[i], width, height, brickX, brickY);
      }
    }

    return result;
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

  public int getWidth() {
    return width;
  }

  public void eliminate() {
    eliminated = true;
  }

  public void draw(Graphics g) {
    if(eliminated) {
      return;
    }

    g.setColor(color);
    g.fillRect(x, y, width, height);
  }

  public Ball rebound(Ball ball) {
    if(eliminated || !ball.isVisible()) {
      return null;
    }

    Rectangle thisRect = new Rectangle(x, y, width, height);

    Line2D.Double topLineOfBall = new Line2D.Double(ball.getX(), ball.getY(),
      ball.getX() + Ball.SIZE, ball.getY());

    Line2D.Double bottomLineOfBall = new Line2D.Double(ball.getX(), ball.getY() + Ball.SIZE,
      ball.getX() + Ball.SIZE, ball.getY() + Ball.SIZE);

    Line2D.Double leftLineOfBall = new Line2D.Double(ball.getX(), ball.getY(),
      ball.getX(), ball.getY() + Ball.SIZE);

    Line2D.Double rightLineOfBall = new Line2D.Double(ball.getX() + Ball.SIZE, ball.getY(),
      ball.getX() + Ball.SIZE, ball.getY() + Ball.SIZE);

    if(thisRect.intersectsLine(topLineOfBall) && thisRect.intersectsLine(bottomLineOfBall)) {
      ball.bounceX();
      return ball;
    }

    if(thisRect.intersectsLine(leftLineOfBall) && thisRect.intersectsLine(rightLineOfBall)) {
      ball.bounceY();
      return ball;
    }

    if(thisRect.intersectsLine(topLineOfBall) || thisRect.intersectsLine(bottomLineOfBall)) {
      ball.bounce();
      return ball;
    }

    return null;
  }
}
