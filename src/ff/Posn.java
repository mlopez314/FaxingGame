package ff;

/**
 * Represents a 2D position of Integers.
 */
public class Posn {
  
  public int x;
  public int y;
  
  /**
   * Constructor for Posn.
   * 
   * @param x the x-position
   * @param y the y-position
   */
  public Posn(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  /**
   * Moves the posn by the given x and y positions.
   * 
   * @param x the given x-position
   * @param y the given y-position
   */
  public void move(int x, int y) {
    this.x += x;
    this.y += y;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Posn) {
      Posn that = (Posn)obj;
      return (this.x == that.x) && (this.y == that.y);
    } else {
      return false;
    }
  }
}