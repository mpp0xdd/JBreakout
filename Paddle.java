import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Paddle {
  public static final int WIDTH  = 60;
  public static final int HEIGHT = 10;

  private final Color color;
  private int x;
  private final int y;

  public Paddle(Color color, int x, int y) {
    this.color = color;
    this.x = x;
    this.y = y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void draw(Graphics g) {
    g.setColor(color);
    g.fillRect(x, y, WIDTH, HEIGHT);
  }

  public Ball rebound(Ball ball) {
    Rectangle thisRect = new Rectangle(x, y, WIDTH, HEIGHT);
    Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), Ball.SIZE, Ball.SIZE);

    if(thisRect.intersects(ballRect)) {
      ball.bounceY();
      return ball;
    }

    return null;
  }
}
