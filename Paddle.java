import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Paddle extends Brick {
  public Paddle(Color color, int width, int height, int x, int y) {
    super(color, width, height, x, y);
  }

  public void setX(int x) {
    this.x = x;
  }
}
