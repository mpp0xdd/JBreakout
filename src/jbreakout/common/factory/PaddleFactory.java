package jbreakout.common.factory;

import java.awt.Point;
import jbreakout.common.Paddle;

public interface PaddleFactory<T extends Paddle> {

  T createPaddle(Point point);

  int width();

  int height();
}
