package ff;

import java.awt.Graphics;

/**
 * This represents a game objects that consists of a position and
 * a draw method.
 */
public abstract class GamePiece {
  
  private Posn pos;
  
  /**
   * Constructor for GamePiece.
   * 
   * @param pos the initial position of the GamePiece
   */
  public GamePiece(Posn pos) {
    this.pos = pos;
  }
  
  /**
   * Getter method for pos.
   * 
   * @return the current position of the GamePiece
   */ 
  public Posn getPos() {
    return pos;
  }
  
  /**
   * Setter method for pos.
   * 
   * @param pos the Posn to set it to
   */
  public void setPos(Posn pos) {
    this.pos = pos;
  }
  
  /**
   * Draws the given GamePieve using Graphics.
   * 
   * @param g the Graphics component
   */
  public abstract void draw(Graphics g);
}