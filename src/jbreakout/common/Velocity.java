package jbreakout.common;

import java.util.Objects;

public final class Velocity {

  public static final Velocity ZERO = of(0, 0);

  public static Velocity of(int vx, int vy) {
    return new Velocity(vx, vy);
  }

  private final int vx;
  private final int vy;

  private Velocity(int vx, int vy) {
    this.vx = vx;
    this.vy = vy;
  }

  public int vx() {
    return vx;
  }

  public int vy() {
    return vy;
  }

  public Velocity negateVX() {
    return of(-vx, vy);
  }

  public Velocity negateVY() {
    return of(vx, -vy);
  }

  public Velocity negate() {
    return of(-vx, -vy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vx, vy);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Velocity other = (Velocity) obj;
    return vx == other.vx && vy == other.vy;
  }
}
