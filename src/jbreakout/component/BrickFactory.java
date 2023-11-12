package jbreakout.component;

import static java.awt.Color.GREEN;
import static java.awt.Color.ORANGE;
import static java.awt.Color.RED;
import static java.awt.Color.YELLOW;
import java.awt.Color;
import jbreakout.common.AbstractBrickFactory;

public class BrickFactory extends AbstractBrickFactory<SoundBrick> {

  @Override
  protected SoundBrick newBrick(Color color, int x, int y) {
    return new SoundBrick(color, width(), height(), x, y);
  }

  @Override
  protected SoundBrick[] newBricks() {
    return new SoundBrick[rows() * columns()];
  }

  @Override
  protected int rows() {
    return 8;
  }

  @Override
  protected int columns() {
    return 14;
  }

  @Override
  protected Color[] colors() {
    return new Color[] {RED, RED, ORANGE, ORANGE, GREEN, GREEN, YELLOW, YELLOW};
  }

  @Override
  protected int width() {
    return 30;
  }

  @Override
  protected int height() {
    return 10;
  }

  @Override
  protected int margin() {
    return 4;
  }
}
